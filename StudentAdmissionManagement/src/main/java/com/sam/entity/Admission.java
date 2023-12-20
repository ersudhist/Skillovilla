package com.sam.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate admissionDate;
    private LocalDate dueDate;
    private double fee;
    private boolean isPaid;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
}


