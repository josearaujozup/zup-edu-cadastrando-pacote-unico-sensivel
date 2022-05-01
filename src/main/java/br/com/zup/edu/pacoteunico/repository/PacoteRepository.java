package br.com.zup.edu.pacoteunico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.pacoteunico.model.Pacote;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {

	public boolean existsByHashDoCpf(String hashDoCpf);

}
