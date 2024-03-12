package com.example.employeemanagementsystem.ModelQ2;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Service {
    @Pattern(regexp = "Laundry|Car rental|Valet parking|Room service (24-hour)")
    @NotNull(message = "service cant be null")
    private String service;
}
