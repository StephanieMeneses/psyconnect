package com.psyconnect.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psyconnect.api.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
