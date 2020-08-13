package com.smirnov.aviaplant.controllers;

import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.Sector;
import com.smirnov.aviaplant.domains.Union;
import com.smirnov.aviaplant.domains.Workshop;
import com.smirnov.aviaplant.repos.EmployeeRepository;
import com.smirnov.aviaplant.repos.ProductRepository;
import com.smirnov.aviaplant.repos.UnionRepository;
import com.smirnov.aviaplant.repos.WorkshopRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class ReportController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UnionRepository unionRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @RequestMapping("/PersonalPage/downloading/employee")
    public String showFiles(Model model) throws IOException {

        List<Employee> employees = employeeRepository.findAll();
        List<Workshop> workshops = workshopRepository.findAll();
        int sumOfWorkshops = workshops.size();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");

        Workbook wb = new XSSFWorkbook();

        CellStyle headerStyle = wb.createCellStyle();

        Font dataFont = wb.createFont();
        dataFont.setColor((short) 12);
        dataFont.setFontHeightInPoints((short) 10);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor((short) 11);
        headerStyle.setFont(dataFont);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setAlignment(HorizontalAlignment.LEFT);


        for (int i = 0; i < sumOfWorkshops; i++){

            Sheet sheet = wb.createSheet(workshops.get(i).getNameOfWorkshop());
            Row row = sheet.createRow(0);

            int employeeRow = 1;

            Cell employeeIDCell = row.createCell(0);
            employeeIDCell.setCellStyle(headerStyle);
            employeeIDCell.setCellValue("Номер работника");

            Cell typeOfEmployeeCell = row.createCell(1);
            typeOfEmployeeCell.setCellStyle(headerStyle);
            typeOfEmployeeCell.setCellValue("Тип работника");

            Cell nameCell = row.createCell(2);
            nameCell.setCellStyle(headerStyle);
            nameCell.setCellValue("Имя");

            Cell patronymicCell = row.createCell(3);
            patronymicCell.setCellStyle(headerStyle);
            patronymicCell.setCellValue("Отчество");

            Cell lastnameCell = row.createCell(4);
            lastnameCell.setCellStyle(headerStyle);
            lastnameCell.setCellValue("Фамилия");

            Cell dobCell = row.createCell(5);
            dobCell.setCellStyle(headerStyle);
            dobCell.setCellValue("Дата рождения");

            Cell sexCell = row.createCell(6);
            sexCell.setCellStyle(headerStyle);
            sexCell.setCellValue("Пол");

            Cell postCell = row.createCell(7);
            postCell.setCellStyle(headerStyle);
            postCell.setCellValue("Должность");

            Cell educationCell = row.createCell(8);
            educationCell.setCellStyle(headerStyle);
            educationCell.setCellValue("Образование");

            Cell experienceCell = row.createCell(9);
            experienceCell.setCellStyle(headerStyle);
            experienceCell.setCellValue("Опыт работы");

            Cell dateOfEntryCell = row.createCell(10);
            dateOfEntryCell.setCellStyle(headerStyle);
            dateOfEntryCell.setCellValue("Дата трудоустройства");

            Cell salaryCell = row.createCell(11);
            salaryCell.setCellStyle(headerStyle);
            salaryCell.setCellValue("Зарплата");

            Cell statusCell = row.createCell(12);
            statusCell.setCellStyle(headerStyle);
            statusCell.setCellValue("Статус");

            Cell headOfUnionCell = row.createCell(13);
            headOfUnionCell.setCellStyle(headerStyle);
            headOfUnionCell.setCellValue("Является ли главой объединения");


            Cell nameOfTheUnionCell = row.createCell(14);
            nameOfTheUnionCell.setCellStyle(headerStyle);
            nameOfTheUnionCell.setCellValue("Название объединения");

            Cell nameOfSectorCell = row.createCell(15);
            nameOfSectorCell.setCellStyle(headerStyle);
            nameOfSectorCell.setCellValue("Название сектора");

            for(int p = 0; p < employees.size(); p++){

                if(employees.get(p).getWorkshopName().equals(workshops.get(i).getNameOfWorkshop())){

                    Employee employee = employees.get(p);

                    Union union = employee.getUnion();

                    Sector sector = union.getSector();

                    Row currentRow = sheet.createRow(employeeRow);

                    Cell currentEmployeeIDCell = currentRow.createCell(0);
                    currentEmployeeIDCell.setCellValue(employee.getEmployeeID());

                    Cell currentTypeOfEmployeeCell = currentRow.createCell(1);
                    currentTypeOfEmployeeCell.setCellValue(employee.getTypeOfEmployee());

                    Cell currentNameCell = currentRow.createCell(2);
                    currentNameCell.setCellValue(employee.getName());

                    Cell currentPatronymicCell = currentRow.createCell(3);
                    currentPatronymicCell.setCellValue(employee.getPatronymic());

                    Cell currentLastnameCell = currentRow.createCell(4);
                    currentLastnameCell.setCellValue(employee.getLastname());

                    Cell currentDobCell = currentRow.createCell(5);
                    currentDobCell.setCellValue(employee.getDob().toString());

                    Cell currentSexCell = currentRow.createCell(6);
                    currentSexCell.setCellValue(employee.getSex());

                    Cell currentPostCell = currentRow.createCell(7);
                    currentPostCell.setCellValue(employee.getPost());

                    Cell currentEducationCell = currentRow.createCell(8);
                    currentEducationCell.setCellValue(employee.getEducation());

                    Cell currentExperienceCell = currentRow.createCell(9);
                    currentExperienceCell.setCellValue(employee.getExperience());

                    Cell currentDateOfEntryCell = currentRow.createCell(10);
                    currentDateOfEntryCell.setCellValue(employee.getDateOfEntry().toString());

                    Cell currentSalaryCell = currentRow.createCell(11);
                    currentSalaryCell.setCellValue(employee.getSalary());

                    Cell currentStatusCell = currentRow.createCell(12);
                    currentStatusCell.setCellValue(employee.getStatus());

                    Cell currentHeadOfUnionCell = currentRow.createCell(13);
                    currentHeadOfUnionCell.setCellValue(employee.getHeadOfUnion());

                    Cell currentNameOfTheUnionCell = currentRow.createCell(14);
                    currentNameOfTheUnionCell.setCellValue(union.getNameOfTheUnion());

                    Cell currentNameOfSectorCell = currentRow.createCell(15);
                    currentNameOfSectorCell.setCellValue(sector.getNameOfSector());

                    employeeRow++;
                }
            }

            for (int k = 0 ; k < 16; k++) sheet.autoSizeColumn(k);
        }
        String currentDateTime = formatter.format(date);
        String fileName = "employeeReport " + currentDateTime + ".xls";
        String filePath = "D:/Uploads/Reports/" + fileName;


        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        wb.write(fileOutputStream);
        fileOutputStream.close();

        File folder = new File("D:/Uploads/Reports/");
        //File file = new File(fileName);
        File[] listOfFiles = folder.listFiles();
        int n = listOfFiles.length - 1;

        model.addAttribute("file", listOfFiles[n]);
        return "showFiles";
    }


    @GetMapping("/Reports/{fileName}")
    @ResponseBody
    public void downloadFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");

        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream("D:/Uploads/Reports/" + fileName);

            int length;
            byte[] buffer = new byte[1024];
            while ((length = fis.read(buffer)) > 0){
                bos.write(buffer, 0, length);
            }

            bos.close();
            response.flushBuffer();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
