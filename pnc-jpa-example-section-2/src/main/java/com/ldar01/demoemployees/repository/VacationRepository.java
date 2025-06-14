package com.ldar01.demoemployees.repository;

import com.ldar01.demoemployees.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    List<Vacation> findByEmployeeId(int employeeId);
}
