package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.Aircraft;
import org.springframework.data.repository.CrudRepository;

public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
}
