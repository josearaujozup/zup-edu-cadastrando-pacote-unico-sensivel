package br.com.zup.edu.pacoteunico.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pacote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private String cpf;
	
	@Column(nullable = false)
    private String celular;
	
	@Column(nullable = false)
    private Integer quantidadeDados;
	
	@Column(nullable = false)
    private LocalDate dataCadastro;
}
