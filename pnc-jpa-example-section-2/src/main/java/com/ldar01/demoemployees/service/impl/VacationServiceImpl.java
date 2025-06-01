package com.ldar01.demoemployees.service.impl;

import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import com.ldar01.demoemployees.dto.request.vacation.VacationUpdateRequest;
import com.ldar01.demoemployees.dto.response.employee.EmployeeResponse;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.entities.Vacation;
import com.ldar01.demoemployees.exception.VacationNotFoundException;
import com.ldar01.demoemployees.repository.VacationRepository;
import com.ldar01.demoemployees.service.EmployeeService;
import com.ldar01.demoemployees.service.VacationService;
import com.ldar01.demoemployees.utils.mappers.EmployeeMapper;
import com.ldar01.demoemployees.utils.mappers.VacationMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;
    private final EmployeeService employeeService;

    @Autowired
    public VacationServiceImpl(VacationRepository repository, EmployeeService employeeService) {
        this.vacationRepository = repository;
        this.employeeService = employeeService;
    }

    @Override
    public List<VacationResponse> findAll() {
        return VacationMapper.toDTOList(vacationRepository.findAll());
    }

    @Override
    public VacationResponse findById(int id) {
        return VacationMapper.toDTO(vacationRepository.findById(id)
                .orElseThrow(() -> new VacationNotFoundException("Vacation with ID: " + id + " not found")));
    }

    @Override
    public List <VacationResponse> findByEmployeeId(int employeeId) {
        return VacationMapper.toDTOList(vacationRepository.findByEmployeeId(employeeId));
    }

    @Override
    @Transactional
    public VacationResponse save(VacationRequest vacation) {
        EmployeeResponse employee = employeeService.findById(vacation.getEmployeeId());

        LocalDate startDate = LocalDate.parse(vacation.getStartDate());
        LocalDate endDate = LocalDate.parse(vacation.getEndDate());

        if (startDate.isAfter(endDate)) {
            throw new VacationNotFoundException("Start date cannot be after end date");
        }

        return VacationMapper.toDTO(vacationRepository.save(VacationMapper.toEntityCreate(vacation, EmployeeMapper.toEntity(employee))));
    }

    @Override
    @Transactional
    public VacationResponse update(VacationUpdateRequest vacation) {
        EmployeeResponse employee = employeeService.findById(vacation.getEmployeeId());

        LocalDate startDate = LocalDate.parse(vacation.getStartDate());
        LocalDate endDate = LocalDate.parse(vacation.getEndDate());

        if (startDate.isAfter(endDate)) {
            throw new VacationNotFoundException("Start date cannot be after end date");
        }

        return VacationMapper.toDTO(vacationRepository.save(VacationMapper.toEntityUpdate(vacation, EmployeeMapper.toEntity(employee))));
    }

    @Override
    public void delete(int id) {
        vacationRepository.deleteById(id);
    }
}
