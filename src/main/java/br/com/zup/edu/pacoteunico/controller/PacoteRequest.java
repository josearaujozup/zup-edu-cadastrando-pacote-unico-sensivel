package br.com.zup.edu.pacoteunico.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import br.com.zup.edu.pacoteunico.model.Pacote;

public class PacoteRequest {
	
	@NotBlank
	private String nomeTitular;
	
	@NotBlank
	@CPF
    private String cpfTitular;	
	
	@NotBlank
	@Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String celular;
	
	@NotNull
	@Min(5)
	@Max(50)
    private Integer quantidadeDados;
	
	public PacoteRequest(@NotBlank String nomeTitular, @NotBlank String cpfTitular,
			@NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$") String celular,
			@NotNull @Min(5) @Max(50) Integer quantidadeDados) {
		this.nomeTitular = nomeTitular;
		this.cpfTitular = cpfTitular;
		this.celular = celular;
		this.quantidadeDados = quantidadeDados;
	}
	
	public PacoteRequest() {
		
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public String getCpfTitular() {
		return cpfTitular;
	}

	public String getCelular() {
		return celular;
	}

	public Integer getQuantidadeDados() {
		return quantidadeDados;
	}

	public Pacote toModel() {
		return new Pacote(nomeTitular, cpfTitular, celular, quantidadeDados);
	}
	
}
