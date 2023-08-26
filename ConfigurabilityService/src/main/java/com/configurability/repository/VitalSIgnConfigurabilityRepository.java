package com.configurability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.configurability.model.VitalSignConfigurability;

@Repository
public interface VitalSIgnConfigurabilityRepository extends JpaRepository<VitalSignConfigurability, Long>{
	VitalSignConfigurability findByPatientId(Long patientId);
}
