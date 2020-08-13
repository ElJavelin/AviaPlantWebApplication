package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.Employee;
import com.smirnov.aviaplant.domains.Workshop;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface WorkshopRepository extends CrudRepository<Workshop, Long> {
    List<Workshop> findAll();


}