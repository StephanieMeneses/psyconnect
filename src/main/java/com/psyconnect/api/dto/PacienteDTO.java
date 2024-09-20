package com.psyconnect.api.dto;

import com.psyconnect.api.model.Paciente;

public class PacienteDTO {
		
		private Long id;	
		private String nome;
		private String cpf;
		private String idade;	
		private String telefone;
		
		public PacienteDTO () {
				
		}
		
		public PacienteDTO(Long id, String nome, String cpf, 
				 String idade, String telefone) {
			super();
			this.id = id;
			this.nome = nome;
			this.cpf = cpf;
			this.idade = idade;
			this.telefone = telefone;
		}
		
		public PacienteDTO (Paciente entity) {
			this.id = entity.getId();
			this.nome = entity.getNome();
			this.cpf = entity.getCpf();
			this.idade = entity.getIdade();
			this.telefone = entity.getTelefone();		
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getIdade() {
			return idade;
		}

		public void setIdade(String idade) {
			this.idade = idade;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		
}

	
