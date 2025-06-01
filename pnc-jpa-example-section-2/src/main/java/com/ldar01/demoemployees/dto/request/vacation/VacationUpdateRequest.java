package com.ldar01.demoemployees.dto.request.vacation;

import com.ldar01.demoemployees.entities.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacationUpdateRequest {

    //@NotNull(message = "You must provide a vacation ID")
    private Integer vacationId; // Assuming you want to update a specific vacation by its ID
    private String startDate; // Optional, if you want to allow partial updates
    private String endDate; // Optional, if you want to allow partial updates
    private String reason; // Optional, if you want to allow partial updates
    private Status status;
    private Integer employeeId; // Optional, if you want to allow changing the associated employee
}
