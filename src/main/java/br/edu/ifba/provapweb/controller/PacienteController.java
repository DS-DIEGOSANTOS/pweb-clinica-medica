package br.edu.ifba.provapweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.provapweb.domain.dto.request.PacienteCreateRequest;
import br.edu.ifba.provapweb.domain.dto.request.PacienteUpdateRequest;
import br.edu.ifba.provapweb.domain.dto.response.PacienteResponse;
import br.edu.ifba.provapweb.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping
	public ResponseEntity<Void> postPaciente(@RequestBody PacienteCreateRequest request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(pacienteService.cadastrarPaciente(request));
	}

	@GetMapping
	public ResponseEntity<Page<PacienteResponse>> getPacientes(
			@PageableDefault(page = 0, size = 10, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(pacienteService.listarPacientes(pageable));
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<Void> deletePaciente(@PathVariable String cpf) {
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(pacienteService.deletarPaciente(cpf));
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<PacienteResponse> putPaciente(@PathVariable String cpf,
																											@RequestBody PacienteUpdateRequest request) {
		return ResponseEntity.ok(pacienteService.atualizarPaciente(cpf, request));
	}

}
