package com.jkshian.arms.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private int id;
    private String bstart;
    private String bend;
//    private String useremail;
    private int bnumofseat;
    private double price;


}
