package com.datacollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollection.model.PatientData;

public interface PatientDataRepository extends JpaRepository<PatientData, Long>{

}
