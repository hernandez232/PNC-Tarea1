package com.ldar01.demoemployees.service;

import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import com.ldar01.demoemployees.dto.request.vacation.VacationUpdateRequest;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.entities.Vacation;

import java.util.List;

public interface VacationService {
    List<VacationResponse> findAll();
    VacationResponse findById(int id);
    List <VacationResponse> findByEmployeeId(int employeeId);
    VacationResponse save(VacationRequest vacation);
    VacationResponse update(VacationUpdateRequest vacation);
    void delete(int id);
}
