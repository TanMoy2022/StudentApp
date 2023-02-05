package com.student.payload;


import com.student.model.Profile;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDto {

    private Long id;
    @NotBlank(message = "name field should not be null")
    private String name;
    @NotBlank(message = "department field should not be null")
    private String department;
    @NotBlank(message="please provide correct contact number")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String contact;
    @NotBlank(message="email should be correct")
    @Email(message="please provide valid email")
    private String email;



}
