package br.com.zup.edu.pacoteunico.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.zup.edu.pacoteunico.utils.CpfUtils;

@Entity
public class Pacote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nomeTitular;
	
	@Column(nullable = false)
    private String cpfTitular;
	
	@Column(nullable = false, unique = true, length = 64) // unico
    private String hashDoCpf;
	
	@Column(nullable = false)
    private String celular;
	
	@Column(nullable = false)
    private Integer quantidadeDados;
	
	@Column(nullable = false)
    private LocalDate dataCadastro = LocalDate.now();

	public Pacote(String nome, String cpf, String celular, Integer quantidadeDados) {
		this.nomeTitular = nome;
		this.cpfTitular = CpfUtils.anonymize(cpf);
		this.hashDoCpf = CpfUtils.hash(cpf); // gera hash do cpf
		this.celular = celular;
		this.quantidadeDados = quantidadeDados;
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
	public Pacote() {
		
	}
	
	public Long getId() {
		return id;
	}
	
}
