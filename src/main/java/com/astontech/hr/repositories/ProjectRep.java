package com.astontech.hr.repositories;

import com.astontech.hr.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRep extends CrudRepository<Project, Integer> {
}
