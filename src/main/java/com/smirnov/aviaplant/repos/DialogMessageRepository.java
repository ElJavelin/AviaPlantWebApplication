package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.DialogMessage;
import org.springframework.data.repository.CrudRepository;

public interface DialogMessageRepository extends CrudRepository<DialogMessage, Long> {
}
