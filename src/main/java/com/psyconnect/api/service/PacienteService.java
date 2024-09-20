package com.psyconnect.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psyconnect.api.dto.PacienteDTO;
import com.psyconnect.api.model.Paciente;
import com.psyconnect.api.repository.PacienteRepository;
import com.psyconnect.api.service.exceptions.DataBaseException;
import com.psyconnect.api.service.exceptions.EntidadeNaoEncontradaException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteService {
	
	@Autowired
	private static PacienteRepository pacienteRepository;

	@Transactional(readOnly = true)
	public static PacienteDTO buscarPacienteId(Long id) {

		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente com id " + id + " não encontrado"));

		return new PacienteDTO(paciente);

	}
	
	@Transactional(readOnly = true)
	public List<PacienteDTO> buscarPacientes() {

		List<Paciente> pacientes = pacienteRepository.findAll();

		return pacientes.stream().map(paciente -> new PacienteDTO(paciente)).collect(Collectors.toList());

	}

	@Transactional
	public PacienteDTO criarPaciente(PacienteDTO dto) {

		Paciente paciente = new Paciente(dto);
		pacienteRepository.save(paciente);
		return new PacienteDTO(paciente);
	}
	public void deletarPaciente(Long id) {
		if (!pacienteRepository.existsById(id))
			throw new EntidadeNaoEncontradaException("Não é possível deletar, id informado não existe.");
		try {
			pacienteRepository.deleteById(id);

		}

		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade referencial violada");
		}

	}
	

	@Transactional
	public static PacienteDTO editarPaciente(Long id, PacienteDTO dto) {
		
		try {
			
			Paciente paciente = pacienteRepository.getReferenceById(id);
			copiarDtoParaEntidade(dto, paciente);
			paciente = pacienteRepository.save(paciente);
			
			return new PacienteDTO(paciente);
			
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException("Não é possivel atualizar, id informado não existe");
		}
	}

	private static void copiarDtoParaEntidade(PacienteDTO dto, Paciente paciente) {
		paciente.setNome(dto.getNome());
		paciente.setCpf(dto.getCpf());
		paciente.setIdade(dto.getIdade());
		paciente.setTelefone(dto.getTelefone());
		
	}
		
	
}
