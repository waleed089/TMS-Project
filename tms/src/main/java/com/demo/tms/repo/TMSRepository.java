package com.demo.tms.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.tms.entity.TaskEntity;

public interface TMSRepository extends JpaRepository<TaskEntity, BigInteger> {

	@Query (value = "Select * From tms.task where priority <> 'POSTPONED' Order by due_date, CASE WHEN priority = 'HIGH' THEN 1 "
			+ "	 WHEN priority = 'MEDIUM' THEN 2 WHEN priority = 'LOW' THEN 3 END", nativeQuery = true)
	public List<Object[]> getAllTaskByOrder();
}
