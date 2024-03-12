package com.example.employeemanagementsystem.ModelQ2;

import jakarta.annotation.Priority;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Hotel {
//    @Pattern(regexp = )
//    private String classRoom;
@NotNull(message = "total Room can not be null")
@Min(50)@Max(100)
private int totalRoom;
    @NotNull(message = "subscribeCompany cant be null")
    @Size(min = 10)
    private String subscribeCompany;
    //------------------------------------
    @NotNull(message = "Hotel name can not be null")
    @Size(min = 10)
    @Pattern(regexp = "[a-zA-Z]+",message = "Hotel Name Must contain only characters (no numbers)")
    private String hotelName;
    //-------------------------------------------
    @Email@NotNull(message = "Email can not be null")
    @Pattern(regexp = "[1-9][a-z]]@hotel\\.com$")
    private String Email;
    //-------------------------------------
    @NotNull(message = "Phone can not be null")@Pattern(regexp = "^011\\d{8}$")
    private String Phone;
    //-------------------------------------
    @NotNull(message = "TotalClub Can not null ")@Max(3)@Min(1)
    private int TotalClub;
    //---------------------------------------
    @NotNull(message = "totalRestaurant cant be null")@Max(4)@Min(1)
    private int  totalRestaurant;




}
