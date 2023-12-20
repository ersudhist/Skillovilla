package com.sam.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.entity.Admission;
import com.sam.repository.AdmissionRepository;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    // This method is used to calculate fine for due payment
    
     public double calculateFine(Admission admission) {
    	
        LocalDate currentDate = LocalDate.now();
        
        if (!admission.isPaid() && admission.getDueDate().isBefore(currentDate)) {
        	
            long daysLate = ChronoUnit.DAYS.between(admission.getDueDate(), currentDate);
            return daysLate * 100.0; 
        }
        
     // if payment is done within due date then no fine is apply 
        
        return 0.0; 
    }
}

