package com.example.employeemanagementsystem.ModelQ2;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor@Data
public class Room {
    @NotNull(message = "Room Cant be null ")@Pattern(regexp = "VIP|Suit|VIP-Non-smoking |Suit-Non-smoking")
    @Size(min = 5,message = "Room length must be at least 5")
    private String room;
    //--------------------------
    @Min(2)@Max(5)@NotNull(message ="Capacity Can not null" )
    private int capacity;
    @FutureOrPresent(message = "Book now or later ")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkin;
    @FutureOrPresent(message = "Checkout now or later ")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkout;
    //--------------------------------------------
    @Max((long) 0.2)
    @Positive(message = "Positive number")
    private double discount;
//------------------------------------------------
    @NotNull(message = "Room number can not null")
    @Min(1)@Max(100)
    @Positive(message = "Positive number")
    private int roomNum;
    //----------------------------
    @Min(100)@Max(500)
    @NotNull(message = "Room Insurance can not be null")
    private double roomInsurance;
    //-------------------------------
    @NotNull(message = "Price can not null")
    @Min(600)@Max(3000)
    private double price;

}
