package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.*;
import com.smirnov.aviaplant.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private CivilianRepository civilianRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private AttackRepository attackRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        Iterable<Sector> sectors = sectorRepository.findAll();
        model.addAttribute("sector", sectors);
        return "addProduct";
    }

    @PostMapping("/addProduct/Civilian")
    public String addCivilian (
            @RequestParam Sector sector, @RequestParam String typeOfProduct,
            @RequestParam String destinationCountry, @RequestParam String price,
            @RequestParam java.sql.Date startDate, @RequestParam java.sql.Date plannedCompletionDate,
            @RequestParam String status,
            @RequestParam String type, @RequestParam String aircraftModel,
            @RequestParam Integer crew, @RequestParam String weight,
            @RequestParam String longA, @RequestParam String height,
            @RequestParam String width, @RequestParam String cruisingSpeed,
            @RequestParam String maxSpeed, @RequestParam Integer numberOfEngines,
            @RequestParam String engineType,
            @RequestParam String cType, @RequestParam String classA,
            @RequestParam Integer numOfSeats,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {

        Product product = new Product(typeOfProduct,destinationCountry, price, startDate, plannedCompletionDate,status, sector);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setFileName(resultFileName);
        }

        Aircraft aircraft = new Aircraft(type, aircraftModel, crew, weight, longA, height, width, cruisingSpeed, maxSpeed, numberOfEngines, engineType, product);
        productRepository.save(product);
        aircraftRepository.save(aircraft);
        Civilian civilian = new Civilian(cType, classA, numOfSeats, aircraft);
        civilianRepository.save(civilian);

        return "redirect:/showProduct";
    }

    @PostMapping("/addProduct/Attack")
    public String addAttack (
            @RequestParam Sector sector, @RequestParam String typeOfProduct,
            @RequestParam String destinationCountry, @RequestParam String price,
            @RequestParam java.sql.Date startDate, @RequestParam java.sql.Date plannedCompletionDate,
            @RequestParam String status,
            @RequestParam String type, @RequestParam String aircraftModel,
            @RequestParam Integer crew, @RequestParam String weight,
            @RequestParam String longA, @RequestParam String height,
            @RequestParam String width, @RequestParam String cruisingSpeed,
            @RequestParam String maxSpeed, @RequestParam Integer numberOfEngines,
            @RequestParam String engineType,
            @RequestParam String typeA, @RequestParam String corps,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {

        Product product = new Product(typeOfProduct,destinationCountry, price, startDate, plannedCompletionDate,status, sector);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setFileName(resultFileName);
        }

        Aircraft aircraft = new Aircraft(type, aircraftModel, crew, weight, longA, height, width, cruisingSpeed, maxSpeed, numberOfEngines, engineType, product);
        productRepository.save(product);
        aircraftRepository.save(aircraft);
        Attack attack = new Attack(typeA, corps, aircraft);
        attackRepository.save(attack);

        return "redirect:/showProduct";
    }

    @PostMapping("/addProduct/Transport")
    public String addTransport (
            @RequestParam Sector sector, @RequestParam String typeOfProduct,
            @RequestParam String destinationCountry, @RequestParam String price,
            @RequestParam java.sql.Date startDate, @RequestParam java.sql.Date plannedCompletionDate,
            @RequestParam String status,
            @RequestParam String type, @RequestParam String aircraftModel,
            @RequestParam Integer crew, @RequestParam String weight,
            @RequestParam String longA, @RequestParam String height,
            @RequestParam String width, @RequestParam String cruisingSpeed,
            @RequestParam String maxSpeed, @RequestParam Integer numberOfEngines,
            @RequestParam String engineType,
            @RequestParam String typeT, @RequestParam String carryingCapacity,
            @RequestParam String innerSpace,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {

        Product product = new Product(typeOfProduct,destinationCountry, price, startDate, plannedCompletionDate,status, sector);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/Product/" + resultFileName));
            product.setFileName(resultFileName);
        }

        Aircraft aircraft = new Aircraft(type, aircraftModel, crew, weight, longA, height, width, cruisingSpeed, maxSpeed, numberOfEngines, engineType, product);
        productRepository.save(product);
        aircraftRepository.save(aircraft);
        Transport transport = new Transport(typeT, carryingCapacity, innerSpace, aircraft);
        transportRepository.save(transport);

        return "redirect:/showAircraft";
    }

    @GetMapping("/showProduct")
    public String showProduct(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("product", products);
        return "showProduct";
    }


    @GetMapping("/showAttack")
    public String showAttack(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("product", products);
        Iterable<Aircraft> aircrafts = aircraftRepository.findAll();
        model.addAttribute("aircraft", aircrafts);
        Iterable<Attack> attacks = attackRepository.findAll();
        model.addAttribute("attack", attacks);
        return "showAttack";
    }

    @GetMapping("/showAircraft")
    public String showAircraft(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("product", products);
        Iterable<Aircraft> aircrafts = aircraftRepository.findAll();
        model.addAttribute("aircraft", aircrafts);
        return "showAircraft";
    }

    @GetMapping("/showProductPage/{id}")
    public String showProductPage(@PathVariable(value = "id") String productID, Model model){
        Long x = Long.parseLong(productID);
        Optional<Product> products = productRepository.findById(x);
        ArrayList<Product> result = new ArrayList<>();
        products.ifPresent(result::add);
        model.addAttribute("product" , result);

        Aircraft aircraft = result.get(0).getAircraft();
        model.addAttribute("aircraft", aircraft);

        if(aircraft.getType().equals("Civilian")){
            Civilian civilian = aircraft.getCivilian();
            model.addAttribute("civilian", civilian);
        }else if(aircraft.getType().equals("Transport")){
            Transport transport = aircraft.getTransport();
            model.addAttribute("transport", transport);
        }else {
            Attack attack = aircraft.getAttack();
            model.addAttribute("attack", attack);
        }

        return "showProductPage";
    }

    @GetMapping("/deleteProduct/{id}")
    public String removeProduct(@PathVariable String id){
        Long x = Long.parseLong(id);
        Product product = productRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        productRepository.delete(product);
        return "redirect:/showProduct";
    }

}

