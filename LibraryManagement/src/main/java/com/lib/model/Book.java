package com.lib.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    private String title;
    private String author;
    private boolean borrowed;
    private LocalDate dueDate;  
    private double fine;        
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy;
	
}
