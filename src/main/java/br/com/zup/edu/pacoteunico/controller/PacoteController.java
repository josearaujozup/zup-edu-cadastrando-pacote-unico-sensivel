package br.com.zup.edu.pacoteunico.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.pacoteunico.model.Pacote;
import br.com.zup.edu.pacoteunico.repository.PacoteRepository;
import br.com.zup.edu.pacoteunico.utils.CpfUtils;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {
	
	private final PacoteRepository repository;

	public PacoteController(PacoteRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacoteRequest request, UriComponentsBuilder uriComponentsBuilder){
		
		String hashDoCpf = CpfUtils.hash(request.getCpfTitular()); // encripta cpf informado
		if(repository.existsByHashDoCpf(hashDoCpf)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Cpf já existe no sistema");
		}
		
        Pacote pacote = request.toModel();

        repository.save(pacote);

        URI location = uriComponentsBuilder.path("/pacotes/{id}")
                .buildAndExpand(pacote.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		Map<String, Object> body = Map.of(
				"message", "cpf já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
	
}
