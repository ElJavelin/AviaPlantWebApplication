package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.Sector;
import com.smirnov.aviaplant.domains.Union;
import com.smirnov.aviaplant.domains.Workshop;
import com.smirnov.aviaplant.repos.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WorkshopController {

    @Autowired
    private WorkshopRepository workshopRepository;

    @PostMapping("/addWorkshop")
    public String addWorkshop (@RequestParam String nameOfWorkshop, @RequestParam String address, @RequestParam String telephone, Model model){
        Workshop workshop = new Workshop(nameOfWorkshop, address, telephone);
        workshopRepository.save(workshop);
        return "redirect:/showWorkshop";
    }

    @GetMapping("/showWorkshop")
    public String showWorkshop(Model model){
        Iterable<Workshop> workshops = workshopRepository.findAll();
        model.addAttribute("workshop", workshops);
        return "showWorkshop";
    }

    @GetMapping("/addWorkshop")
    public String addWorkshop(Model model){
        return "addWorkshop";
    }

    @GetMapping("/deleteWorkshop/{id}")
    public String removeWorkshop(@PathVariable(value = "id") String workshopID){
        Long x = Long.parseLong(workshopID);
        Workshop workshop = workshopRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        workshopRepository.delete(workshop);

        return "redirect:/showWorkshop";
    }

    @GetMapping("/editWorkshop/{id}")
    public String editWorkshop(@PathVariable(value = "id") String workshopID, Model model){
        Long x = Long.parseLong(workshopID);
        Optional<Workshop> workshops = workshopRepository.findById(x);
        ArrayList<Workshop> result = new ArrayList<>();
        workshops.ifPresent(result::add);
        model.addAttribute("workshop" , result);
        return "editWorkshop";
    }

    @PostMapping("/updateWorkshop")
    public String updateWorkshop (@RequestParam String workshopID, @RequestParam String nameOfWorkshop, @RequestParam String address, @RequestParam String telephone, Model model){
        Long x = Long.parseLong(workshopID);
        Optional<Workshop> workshops = workshopRepository.findById(x);
        ArrayList<Workshop> result = new ArrayList<>();
        workshops.ifPresent(result::add);
        Workshop workshop = result.get(0);
        workshop.setAddress(address);
        workshop.setNameOfWorkshop(nameOfWorkshop);
        workshop.setTelephone(telephone);
        workshopRepository.save(workshop);
        return "redirect:/showWorkshop";
    }
}
