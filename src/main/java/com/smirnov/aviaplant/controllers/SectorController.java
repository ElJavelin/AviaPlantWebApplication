package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Sector;
import com.smirnov.aviaplant.domains.Workshop;
import com.smirnov.aviaplant.repos.SectorRepository;
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
public class SectorController {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @PostMapping("/addSector")
    public String addWorkshop (@RequestParam Workshop workshop, @RequestParam String nameOfSector, Model model){
        Sector sector = new Sector(nameOfSector, workshop);
        sectorRepository.save(sector);
        return "redirect:/showSector";
    }


    @GetMapping("/showSector")
    public String showWorkshop(Model model){
        Iterable<Sector> sectors = sectorRepository.findAll();
        model.addAttribute("sector", sectors);
        return "showSector";
    }


    @GetMapping("/addSector")
    public String addSector(Model model){
        Iterable<Workshop> workshops = workshopRepository.findAll();
        model.addAttribute("workshop", workshops);
        return "addSector";
    }

    @GetMapping("/editSector/{id}")
    public String editSector(@PathVariable(value = "id") String sectorID, Model model){
        Long x = Long.parseLong(sectorID);
        Optional<Sector> sectors = sectorRepository.findById(x);
        ArrayList<Sector> result = new ArrayList<>();
        sectors.ifPresent(result::add);
        model.addAttribute("sector" , result);
        Iterable<Workshop> workshops = workshopRepository.findAll();
        model.addAttribute("workshop", workshops);
        return "editSector";
    }

    @PostMapping("/updateSector")
    public String updateSector (@RequestParam String sectorID, @RequestParam Workshop workshop, @RequestParam String nameOfSector, Model model){
        Long x = Long.parseLong(sectorID);
        Optional<Sector> sectors = sectorRepository.findById(x);
        ArrayList<Sector> result = new ArrayList<>();
        sectors.ifPresent(result::add);
        Sector sector = result.get(0);
        sector.setNameOfSector(nameOfSector);
        sector.setWorkshop(workshop);
        sectorRepository.save(sector);
        return "redirect:/showSector";
    }

    @GetMapping("/deleteSector/{id}")
    public String removeSector(@PathVariable String id){
        Long x = Long.parseLong(id);
        Sector sector = sectorRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        sectorRepository.delete(sector);
        return "redirect:/showSector";
    }
}
