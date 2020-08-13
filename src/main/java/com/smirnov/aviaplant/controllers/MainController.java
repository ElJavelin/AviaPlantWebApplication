package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.UserData;
import com.smirnov.aviaplant.repos.EmployeeRepository;
import com.smirnov.aviaplant.repos.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserDataRepository userDataRepository;


    @GetMapping("/")
    public String showRoot(Model model) {
        return "redirect:/PersonalPage";
    }


    @GetMapping("/PersonalPage")
    public String showPersonalPage(@AuthenticationPrincipal String username, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserData userData = userDataRepository.findByUsername(authentication.getName());
        Employee employee = employeeRepository.findById(userData.getEmployee().getEmployeeID()).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });

        model.addAttribute("userData", userData);
        model.addAttribute("employee", employee);
        model.addAttribute("dialogs", userData.getDialogs());

        return "personalPage";
    }
}