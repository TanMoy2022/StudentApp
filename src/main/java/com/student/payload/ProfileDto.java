package com.student.payload;

import com.student.model.BaseEntity;
import lombok.Data;

@Data
public class ProfileDto extends BaseEntity {

    private Long id;
    private String address;
    private String feedback;
    private String status;



}
