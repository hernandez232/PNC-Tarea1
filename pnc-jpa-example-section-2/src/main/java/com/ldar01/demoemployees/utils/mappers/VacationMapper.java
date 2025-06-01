package com.ldar01.demoemployees.utils.mappers;

import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import com.ldar01.demoemployees.dto.request.vacation.VacationUpdateRequest;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.entities.Employee;
import com.ldar01.demoemployees.entities.Status;
import com.ldar01.demoemployees.entities.Vacation;

import java.util.List;

public class VacationMapper {

    public static Vacation toEntityCreate(VacationRequest vacationDTO, Employee employee) {
        return Vacation.builder()
                .startDate(vacationDTO.getStartDate())
                .endDate(vacationDTO.getEndDate())
                .reason(vacationDTO.getReason())
                .status(Status.PENDING) // Assuming default status is PENDING
                .employee(employee)
                .build();
    }

    public static Vacation toEntityUpdate(VacationUpdateRequest vacationDTO, Employee employee) {
        return Vacation.builder()
                .id(vacationDTO.getVacationId())
                .startDate(vacationDTO.getStartDate())
                .endDate(vacationDTO.getEndDate())
                .reason(vacationDTO.getReason())
                .status(vacationDTO.getStatus())
                .employee(employee)
                .build();
    }

    public static VacationResponse toDTO(Vacation vacation) {
        return VacationResponse.builder()
                .vacationId(vacation.getId())
                .startDate(vacation.getStartDate())
                .endDate(vacation.getEndDate())
                .reason(vacation.getReason())
                .status(vacation.getStatus())
                .employeeId(vacation.getEmployee().getId())
                .build();
    }

    public static List<VacationResponse> toDTOList(List<Vacation> vacations) {
        return vacations.stream()
                .map(VacationMapper::toDTO)
                .toList();
    }
}
