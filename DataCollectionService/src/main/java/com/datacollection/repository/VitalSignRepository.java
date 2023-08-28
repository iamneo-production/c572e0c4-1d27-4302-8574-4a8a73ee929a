package com.datacollection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollection.model.VitalSings;

public interface VitalSignRepository extends JpaRepository<VitalSings, Long>{
	
	List<VitalSings> findByPatientId(Long patientId);

}
