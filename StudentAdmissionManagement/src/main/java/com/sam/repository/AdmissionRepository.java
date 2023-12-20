package com.sam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.entity.Admission;
import com.sam.entity.Student;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long>{

	void save(Student student);

}
