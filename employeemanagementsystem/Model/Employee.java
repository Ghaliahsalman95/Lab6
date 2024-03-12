package com.example.employeemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data@AllArgsConstructor
public class Employee {
    //ID:
    //- Cannot be null.
    //- Length must be more than 2 characters.
    @NotNull(message = "ID Cannot be null.")
    @Size(min = 2,message = "ID Length must be more than 2 characters.")
    private String ID;
    //--------------------------------------
    //▪ Name:
    //- Cannot be null.
    //- Length must be more than 4 characters.
    //- Must contain only characters (no numbers)
    @NotNull(message = "Name Cannot be null.")
    @Size(min = 4,message = "Name Length must be more than 4 characters.")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name Must contain only characters (no numbers)")
    private String name;
    //-------------------------------------
    //Email:
    //- Must be a valid email format
    @Email(message = "Exapmle@email.com")
    private String Email;
    //-------------------------------------
    //Phone Number:
    //- Must start with "05".
    //- Must consists of exactly 10 digits.\\b05[0-9]*\b
    @Pattern(regexp ="^05\\d{8}$",message = "Phone number Must start with \"05\"")
    @Size(min = 10,max = 10,message = "Phone numberMust consists of exactly 10 digits")
    private String phone;
    //--------------------------------------
    //Age:
    //- Cannot be null.
    //- Must be a number.
    //- Must be more than 25.
    @NotNull(message = "Age Cannot be null ")
    @Positive(message = "Age Must be a number")
    @Min(value = 25,message = "Age Must be more than 25.")
    private Integer age;
//--------------------------------------------------------------
    //Position:
    //- Cannot be null.
    //- Must be either "supervisor" or "coordinator" only.
    @NotNull(message = "Position Cannot be null")
    @Pattern(regexp = "supervisor|coordinator")
    private String position;
    //-----------------------------------------------------------
    //onLeave:
    //- Must be initially set to false.
    @AssertFalse(message = "onLeave: Must be initially set to false.")
    private boolean onLeave;
    //--------------------------------------
    //hireDate:
    //- Cannot be null.
    //- should be a date in the past or the present.
    //- Must be a valid year (e.g., 1900 or later).
    @PastOrPresent(message = "should be a date in the past or the present.")
    @JsonFormat(pattern = "yyy-mm-dd")
    //(current = "1900-01-01")

    private LocalDate hireDate;
    //------------------------------------------
    //AnnualLeave:
    //- Cannot be null.
    //- Must be a positive number.
    @NotNull(message = "AnnualLeave Cannot be null.")
    @Positive(message = "AnnualLeave Must be a positive number")
    private int AnnualLeave;

}
