package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Sector;
import com.smirnov.aviaplant.domains.Union;
import com.smirnov.aviaplant.domains.Workshop;
import com.smirnov.aviaplant.repos.SectorRepository;
import com.smirnov.aviaplant.repos.UnionRepository;
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
public class UnionController {

    @Autowired
    private UnionRepository unionRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @PostMapping("/addUnion")
    public String addWorkshop (@RequestParam String nameOfTheUnion, @RequestParam Sector sector, Model model){
        Union union = new Union(nameOfTheUnion, sector);
        unionRepository.save(union);
        return "redirect:/showUnion";
    }


    @GetMapping("/showUnion")
    public String showUnion(Model model){
        Iterable<Union> unions = unionRepository.findAll();
        model.addAttribute("union", unions);
        return "showUnion";
    }


    @GetMapping("/addUnion")
    public String addSector(Model model){
        Iterable<Sector> sectors = sectorRepository.findAll();
        model.addAttribute("sector", sectors);
        return "addUnion";
    }

    @GetMapping("/editUnion/{id}")
    public String editUnion(@PathVariable(value = "id") String unionID, Model model){
        Long x = Long.parseLong(unionID);
        Optional<Union> unions = unionRepository.findById(x);
        ArrayList<Union> result = new ArrayList<>();
        unions.ifPresent(result::add);
        model.addAttribute("union" , result);
        Iterable<Sector> sectors = sectorRepository.findAll();
        model.addAttribute("sector", sectors);
        return "editUnion";
    }

    @PostMapping("/updateUnion")
    public String updateUnion (@RequestParam String unionID, @RequestParam Sector sector, @RequestParam String nameOfUnion, Model model){
        Long x = Long.parseLong(unionID);
        Optional<Union> unions = unionRepository.findById(x);
        ArrayList<Union> result = new ArrayList<>();
        unions.ifPresent(result::add);
        Union union = result.get(0);
        union.setNameOfTheUnion(nameOfUnion);
        union.setSector(sector);
        unionRepository.save(union);
        return "redirect:/showUnion";
    }

    @GetMapping("/deleteUnion/{id}")
    public String removeUnion(@PathVariable String id){
        Long x = Long.parseLong(id);
        Union union = unionRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        unionRepository.delete(union);
        return "redirect:/showUnion";
    }
}
