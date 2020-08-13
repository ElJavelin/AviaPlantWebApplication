package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.Product;
import com.smirnov.aviaplant.repos.EmployeeRepository;
import com.smirnov.aviaplant.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public String search(){
        return "searchPage";
    }

    @PostMapping("/search/employee/employeeID")
    public String searchEmployeeByID(@RequestParam Long searchRequest, Model model){

        try {
            List<Employee> employee = employeeRepository.findAll();
            if (employee.isEmpty()) model.addAttribute("msg", "Ничего не найдено");
            List<Employee> result = new ArrayList<>();

            int size = employee.size();
            for(int i = 0; i < size; i++ ){
                if(employee.get(i).getEmployeeID() == searchRequest){
                    result.add(employee.get(i));
                }
            }
            model.addAttribute("employee" , result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "searchPage";
    }



    @PostMapping("/search/employee/lastname")
    public String searchEmployeeByLastName(@RequestParam String searchRequest, Model model){
        try {
            List<Employee> employee = employeeRepository.findAll();
            if (employee.isEmpty()) model.addAttribute("msg", "Ничего не найдено");
            List<Employee> result = new ArrayList<>();
            int size = employee.size();
            for(int i = 0; i < size; i++ ){
                if(employee.get(i).getLastname().toLowerCase().matches(".*" + searchRequest.toLowerCase() +".*")){
                    result.add(employee.get(i));
                }
            }
            model.addAttribute("employee" , result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "searchPage";
    }

    @PostMapping("/search/employee/post")
    public String searchEmployeeByPost(@RequestParam String searchRequest, Model model){
        try {
            List<Employee> employee = employeeRepository.findAll();
            if (employee.isEmpty()) model.addAttribute("msg", "Ничего не найдено");
            List<Employee> result = new ArrayList<>();
            int size = employee.size();
            for(int i = 0; i < size; i++ ){
                if(employee.get(i).getPost().toLowerCase().matches(".*" + searchRequest.toLowerCase() +".*")){
                    result.add(employee.get(i));
                }
            }
            model.addAttribute("employee" , result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "searchPage";
    }

    @PostMapping("/search/product/productID")
    public String searchProductByID(@RequestParam Long searchRequest, Model model) {
        try {
            List<Product> product = productRepository.findAll();
            if (product.isEmpty()) model.addAttribute("msg", "Ничего не найдено");
            List<Product> result = new ArrayList<>();
            int size = product.size();
            for(int i = 0; i < size; i++ ){
                if(product.get(i).getProductID() == searchRequest){
                    result.add(product.get(i));
                }
            }
            model.addAttribute("product" , result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "searchPage";
    }

    @PostMapping("/search/product/typeOfProduct")
    public String searchProductTypeOfProduct(@RequestParam String searchRequest, Model model){
        try {
            List<Product> product = productRepository.findAll();
            if (product.isEmpty()) model.addAttribute("msg", "Ничего не найдено");
            List<Product> result = new ArrayList<>();
            int size = product.size();
            for(int i = 0; i < size; i++ ){
                if(product.get(i).getTypeOfProduct().toLowerCase().matches(".*" + searchRequest.toLowerCase() +".*")){
                    result.add(product.get(i));
                }
            }
            model.addAttribute("product" , result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "searchPage";
    }

    @PostMapping("/search/product/status")
    public String searchProductByStatus(@RequestParam String searchRequest, Model model){
        try {
            List<Product> product = productRepository.findAll();
            if (product.isEmpty()) model.addAttribute("msg", "Ничего не найдено");
            List<Product> result = new ArrayList<>();
            int size = product.size();
            for(int i = 0; i < size; i++ ){
                if(product.get(i).getStatus().toLowerCase().matches(".*" + searchRequest.toLowerCase() +".*")){
                    result.add(product.get(i));
                }
            }
            model.addAttribute("product" , result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "searchPage";
    }
}
