package com.example.demo.studentDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class newStudentDto {
    private String name;

    @Email
    @NotBlank(message = "email required")
    private String email;
}
