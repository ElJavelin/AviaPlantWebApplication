package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.Sector;
import com.smirnov.aviaplant.domains.Union;
import com.smirnov.aviaplant.domains.Workshop;
import com.smirnov.aviaplant.repos.EmployeeRepository;
import com.smirnov.aviaplant.repos.UnionRepository;
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
public class EmployeeController {

    //private static final Logger logger = LoggerFactory.getLogger(Example.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UnionRepository unionRepository;

    @Value("${upload.path}")
    private String uploadPath;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addEmployee")
    public String addEmployee (
            @RequestParam Union union, @RequestParam String typeOfEmployee,
            @RequestParam String name, @RequestParam String patronymic,
            @RequestParam String lastname, @RequestParam java.sql.Date dob,
            @RequestParam String sex, @RequestParam String post,
            @RequestParam String education, @RequestParam String experience,
            @RequestParam java.sql.Date dateOfEntry, @RequestParam String salary,
            @RequestParam String status, @RequestParam Boolean headOfUnion,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {

        Employee employee = new Employee(union, typeOfEmployee, name ,patronymic ,lastname ,dob ,sex ,post ,education ,experience ,dateOfEntry ,salary ,status ,headOfUnion);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/Employee/" + resultFileName));
            employee.setFileName(resultFileName);
        }

        employeeRepository.save(employee);
        return "redirect:/showEmployee";
    }

    @GetMapping("/showEmployeePage/{id}")
    public String showEmployeePage(@PathVariable (value = "id") String employeeID, Model model){
        Long x = Long.parseLong(employeeID);
        Optional<Employee> employees = employeeRepository.findById(x);
        ArrayList<Employee> result = new ArrayList<>();
        employees.ifPresent(result::add);
        model.addAttribute("employee" , result);
        Union union = result.get(0).getUnion();
        model.addAttribute("union", union);
        Sector sector = union.getSector();
        model.addAttribute("sector", sector);
        Workshop workshop = sector.getWorkshop();
        model.addAttribute("workshop", workshop);

        return "showEmployeePage";
    }

    //@PreAuthorize("hasRole('ADMIN') || hasRole('ADVANCED')")
    @GetMapping("/deleteEmployee/{id}")
    public String removeEmployee(@PathVariable String id){
        Long x = Long.parseLong(id);
        Employee employee = employeeRepository.findById(x).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        employeeRepository.delete(employee);
        return "redirect:/showEmployee";
    }


    //@PreAuthorize("hasRole('ADMIN') || hasRole('ADVANCED')")
    @GetMapping("/showEmployee")
    public String showEmployee(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employee", employees);
        Iterable<Union> unions = unionRepository.findAll();
        model.addAttribute("unions", unions);
        return "showEmployee";
    }

    //@PreAuthorize("hasRole('ADMIN') || hasRole('ADVANCED')")
    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        Iterable<Union> unions = unionRepository.findAll();
        model.addAttribute("unions", unions);
        return "addEmployee";
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable(value = "id") String employeeID, Model model){
        Long x = Long.parseLong(employeeID);
        Optional<Employee> employees = employeeRepository.findById(x);
        ArrayList<Employee> result = new ArrayList<>();
        employees.ifPresent(result::add);
        model.addAttribute("employee" , result);
        Iterable<Union> unions = unionRepository.findAll();
        model.addAttribute("union", unions);
        return "editEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee (@RequestParam String employeeID,
                                  @RequestParam Union union, @RequestParam String typeOfEmployee,
                                  @RequestParam String name, @RequestParam String patronymic,
                                  @RequestParam String lastname, @RequestParam java.sql.Date dob,
                                  @RequestParam String sex, @RequestParam String post,
                                  @RequestParam String education, @RequestParam String experience,
                                  @RequestParam java.sql.Date dateOfEntry, @RequestParam String salary,
                                  @RequestParam String status, @RequestParam Boolean headOfUnion,
                                  @RequestParam("file") MultipartFile file, Model model){
        Long x = Long.parseLong(employeeID);
        Optional<Employee> employees = employeeRepository.findById(x);
        ArrayList<Employee> result = new ArrayList<>();
        employees.ifPresent(result::add);
        Employee employee = result.get(0);
        employee.setTypeOfEmployee(typeOfEmployee);
        employee.setName(name);
        employee.setPatronymic(patronymic);
        employee.setLastname(lastname);
        employee.setDob(dob);
        employee.setSex(sex);
        employee.setPost(post);
        employee.setEducation(education);
        employee.setExperience(experience);
        employee.setDateOfEntry(dateOfEntry);
        employee.setSalary(salary);
        employee.setStatus(status);
        employee.setHeadOfUnion(headOfUnion);
        employee.setUnion(union);

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath + "/Employee/" + resultFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(file.getOriginalFilename().isEmpty())) employee.setFileName(resultFileName);

        employeeRepository.save(employee);
        return "redirect:/showEmployee";
    }
}