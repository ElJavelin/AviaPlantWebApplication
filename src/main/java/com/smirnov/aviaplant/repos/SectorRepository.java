package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.Sector;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectorRepository extends CrudRepository<Sector, Long> {

}