package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.*;
import com.smirnov.aviaplant.repos.DialogMessageRepository;
import com.smirnov.aviaplant.repos.DialogRepository;
import com.smirnov.aviaplant.repos.EmployeeRepository;
import com.smirnov.aviaplant.repos.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class ChatController {

    @Autowired
    private DialogRepository dialogRepository;

    @Autowired
    private DialogMessageRepository dialogMessageRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/addDialog/{recipientID}")
    public String addDialog(@AuthenticationPrincipal String username, @PathVariable(value = "recipientID") String recipientID, Model model) {

        Long x = Long.parseLong(recipientID);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserData userData = userDataRepository.findByUsername(authentication.getName());

        UserData recipient = userDataRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });


        String dialogName = userData.getEmployee().getName() + " " + userData.getEmployee().getLastname() + " - " + recipient.getEmployee().getName() + " " + recipient.getEmployee().getLastname();
        Dialog dialog = new Dialog(dialogName);
        DialogMessage systemMessage = new DialogMessage("System", "Диалог начат");
        dialog.getMessages().add(systemMessage);

        userData.getDialogs().add(dialog);
        recipient.getDialogs().add(dialog);
        dialog.getMessages().add(systemMessage);

        systemMessage.setDialog(dialog);

        dialogMessageRepository.save(systemMessage);
        dialogRepository.save(dialog);
        userDataRepository.save(userData);
        userDataRepository.save(recipient);

        return "redirect:/PersonalPage";
    }

    @GetMapping("/personalPageStartChatting/{dialogID}")
    public String startChatting(@AuthenticationPrincipal String username, @PathVariable(value = "dialogID") String dialogID, Model model){

        Long x = Long.parseLong(dialogID);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserData userData = userDataRepository.findByUsername(authentication.getName());

        Employee employee = employeeRepository.findById(userData.getEmployee().getEmployeeID()).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });

        Dialog dialog = dialogRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });

        model.addAttribute("dialog", dialog);
        model.addAttribute("messages", dialog.getMessages());
        model.addAttribute("userData", userData);
        model.addAttribute("employee", employee);
        model.addAttribute("dialogs", userData.getDialogs());

        return "chatPage";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/dialog")
    public Greeting greeting(Principal user, Message message) throws Exception {

        UserData userData = userDataRepository.findByUsername(user.getName());

        Long x = Long.parseLong(message.getDialogID());

        Dialog dialog = dialogRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });

        DialogMessage dialogMessage = new DialogMessage(userData.getUsername(),message.getName());

        dialogMessage.setDialog(dialog);

        dialogRepository.save(dialog);
        dialogMessageRepository.save(dialogMessage);

        return new Greeting( message.getSenderName() + ": " + HtmlUtils.htmlEscape(message.getName()));
    }


}
