package com.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="profiles")
public class Profile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;
    private String feedback;
    private String status;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="student_id", nullable=false)
    private Student student;


}
