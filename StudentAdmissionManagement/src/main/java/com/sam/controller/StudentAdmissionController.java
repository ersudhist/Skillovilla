package com.sam.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.entity.Admission;
import com.sam.entity.Student;
import com.sam.repository.AdmissionRepository;
import com.sam.repository.StudentRepository;
import com.sam.service.AdmissionService;

@RestController
public class StudentAdmissionController {
	
	@Autowired
	private  StudentRepository studentRepository;
	
	@Autowired
	private  AdmissionRepository admissionRepository;
	
	@Autowired
	private AdmissionService admissionService;

	    @PostMapping("/api/pay/{admissionId}")
	    public ResponseEntity<String> makePayment(@RequestBody Student student, @PathVariable Long admissionId ) {
	        Admission admission = admissionRepository.findById(admissionId).orElse(null);
	        
	        if (admission == null) {
	            return ((BodyBuilder) ResponseEntity.notFound()).body("Admission not found for ID: " + admissionId);
	        }
	       
	        double fine = admissionService.calculateFine(admission);
	        if (fine > 0) {
	            return ResponseEntity.badRequest().body("Please pay the fine of " + fine + " rupees.");
	        }

	        admission.setPaid(true);
	        admissionRepository.save(admission);
	        student.setHasPaidFees(true);
	        return ResponseEntity.ok("Payment successful.");
	    }
	    
	    @PostMapping("/add")
	    public ResponseEntity<String> addStudent(@RequestBody Student student) {
	        boolean hasPaidFees = student.isHasPaidFees();

	        if (!hasPaidFees) {
	            return ResponseEntity.badRequest().body("Error: Student has not paid the fees.");
	        }

	        studentRepository.save(student);

	        Admission admission = new Admission();
	        admission.setStudent(student);
	        admission.setAdmissionDate(LocalDate.now());
	        admission.setDueDate(LocalDate.parse("18/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	        admission.setFee(1200.00);
	        admission.setPaid(hasPaidFees);

	        admissionRepository.save(admission);

	        return ResponseEntity.ok("Student added to Admission successfully.");
	    }
	    
}
