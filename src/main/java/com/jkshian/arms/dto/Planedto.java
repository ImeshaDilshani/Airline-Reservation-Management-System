package com.jkshian.arms.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Planedto {
    private  int id;
    private String start;
    private String end;
    private int avlSeat;
    private double numOfKm;

}
