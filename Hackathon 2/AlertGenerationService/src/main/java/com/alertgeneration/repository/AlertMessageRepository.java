package com.alertgeneration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alertgeneration.model.AlertMessage;

public interface AlertMessageRepository extends JpaRepository<AlertMessage, Long>{

}
