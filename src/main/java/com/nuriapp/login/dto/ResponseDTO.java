package com.nuriapp.login.dto;

import lombok.*;

import java.util.List;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {

    private String error;
    private String success;
    private List<T> list;
    private T value;

}
