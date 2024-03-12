package com.example.employeemanagementsystem.ModelQ2;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data@AllArgsConstructor
public class EmployeeHotel {
    @NotNull(message = "ID can not null")@Size(min=10,max=10)
    private String ID;
    //------------------
    @Size(max=8)@NotNull(message = "Name can not be null")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name Must contain only characters (no numbers)")
    private String name;
    //-----------------------------
    @Min(20)@NotNull(message = "age cant null")
    @Positive
    private int age ;
    //----------------------
    @Positive@NotNull@Min(3000)
    private double salary;
    @NotNull@Email@Pattern(regexp = "[1-9][a-z]]@hotel\\.com$")
    private String Email;
    //----------------------------------
    @NotNull
    @Pattern(regexp = "Room service|Concierge service|Valet parking|Admin")
    private String position;

}
