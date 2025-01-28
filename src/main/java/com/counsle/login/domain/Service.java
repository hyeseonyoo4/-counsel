package com.nuriapp.login.domain;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    private Long sno;
    private Date counselingDate;
    private String counselingName;
    private String counselingState;
    private String counselingCategory;
    private String counselingResult;
    private String counselingNextDay;
}
