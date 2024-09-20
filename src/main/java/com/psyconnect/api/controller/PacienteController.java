package com.psyconnect.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.psyconnect.api.dto.PacienteDTO;
import com.psyconnect.api.service.PacienteService;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;


	@GetMapping
	public ResponseEntity<List<PacienteDTO>> buscarPacientes() {

		List<PacienteDTO> dto = pacienteService.buscarPacientes();

		return ResponseEntity.ok().body(dto);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> buscarPacienteId(@PathVariable Long id) {

		PacienteDTO dto = PacienteService.buscarPacienteId(id);

		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO dto) {

		dto = pacienteService.criarPaciente(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		pacienteService.deletarPaciente(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> editarPaciente(@RequestBody PacienteDTO dto, @PathVariable Long id) {

		dto = PacienteService.editarPaciente(id, dto);
		
		return ResponseEntity.ok().body(dto);	}

	
}
