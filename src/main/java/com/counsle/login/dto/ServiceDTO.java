package com.nuriapp.login.dto;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

    private Long sno;
    private Date counselingDate;
    private String counselingName;
    private String counselingState;
    private String counselingCategory;
    private String counselingResult;
    private String  counselingNextDay;
}
