package com.ldar01.demoemployees.dto.request.vacation;

import com.ldar01.demoemployees.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacationRequest {

    @NotNull(message = "Start date cannot be null")
    @NotBlank(message = "Start date cannot be empty")
    private String startDate;

    @NotNull(message = "End date cannot be null")
    @NotBlank(message = "End date cannot be empty")
    private String endDate;

    @NotNull(message = "Reason cannot be null")
    @NotBlank(message = "Reason cannot be empty")
    private String reason;

    public Status status;

    @NotNull(message = "Employee ID cannot be null")
    private Integer employeeId; // Assuming you want to link the vacation to an employee
}
