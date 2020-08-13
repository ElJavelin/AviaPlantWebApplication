package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastname(String searchRequest);

    List<Employee> findByPost(String searchRequest);

    List<Employee> findAll();
}