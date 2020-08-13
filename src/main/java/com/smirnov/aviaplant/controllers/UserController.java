package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Dialog;
import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.Role;
import com.smirnov.aviaplant.domains.UserData;
import com.smirnov.aviaplant.repos.EmployeeRepository;
import com.smirnov.aviaplant.repos.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/registration/{id}")
    public String registration(@PathVariable(value = "id") Long employeeID, Model model){
        Optional<Employee> employee = employeeRepository.findById(employeeID);
        ArrayList<Employee> result = new ArrayList<>();
        employee.ifPresent(result::add);
        model.addAttribute("employee", result);
        return "registration";
    }

    @GetMapping("/jregistration")
    public String jregistration(){
        return "registration";
    }

    @PostMapping("/jregistration")
    public String addJAccount(UserData userData, Map<String, Object> model){

        UserData primalUserData = new UserData(userData.getUsername(), userData.getPassword(), true);
        primalUserData.setRoles(Collections.singleton(Role.ADMIN));
        userDataRepository.save(primalUserData);

        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String addAccount(UserData userData, @RequestParam Long employeeID, Map<String, Object> model){
        UserData userFromDB = userDataRepository.findByUsername(userData.getUsername());
        if(userFromDB != null){
            model.put("message", "User exist!");
            return "registration";
        }
        userData.setRoles(Collections.singleton(Role.USER));
        userData.setActive(true);

        Employee employee = employeeRepository.findById(employeeID).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });

        userData.setEmployee(employee);

        userDataRepository.save(userData);

        return "redirect:/User";
    }

    @GetMapping("/User/{userData}")
    public String userEditForm(@PathVariable UserData userData, Model model){

        model.addAttribute("userData", userData);
        model.addAttribute("roles", Role.values());

        return "editUser";
    }

    @GetMapping("/User")
    public String userList(Model model){
        model.addAttribute("users", userDataRepository.findAll());
        return "userList";
    }

    @PostMapping("/User")
    public String saveUser(@RequestParam String username, @RequestParam String password,  @RequestParam Map<String, String> form, @RequestParam("userDataID") UserData userData){

        userData.setUsername(username);
        userData.setPassword(password);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        userData.getRoles().clear();

        for(String key : form.keySet()){
            if (roles.contains(key)){
                userData.getRoles().add(Role.valueOf(key));
            }
        }
        userDataRepository.save(userData);

        return "redirect:/User";
    }
}
