package com.demo.tms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.tms.entity.TaskEntity;

public interface TMSRepository extends JpaRepository<TaskEntity, Long> {

}
