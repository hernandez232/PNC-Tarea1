package com.ldar01.demoemployees.dto.response.vacation;

import com.ldar01.demoemployees.entities.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacationResponse {

    private Integer vacationId;
    private String startDate;
    private String endDate;
    private String reason;
    private Status status;
    private Integer employeeId;
}
