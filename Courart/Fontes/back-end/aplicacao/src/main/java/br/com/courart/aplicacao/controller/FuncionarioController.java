package br.com.courart.aplicacao.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.courart.aplicacao.model.Funcionario;
import br.com.courart.aplicacao.model.service.FuncionarioService;

@RestController
@RequestMapping("funcionario")
@SuppressWarnings("all") 
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> salvar(@Valid @RequestBody Funcionario funcionario){
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.salvar(funcionario));
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Funcionario>> listarTodos(Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioService.listarTodos(pageable);
		return  funcionarios != null ? ResponseEntity.ok(funcionarios) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.buscarPorId(id);
		return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> atualizar(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.atualizar(funcionario, id));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> remover(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.remover(id));
	}
	
	@RequestMapping(value = "/buscarPorNome/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Funcionario>> buscarPorNome(@PathVariable String nome, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioService.buscarPorNome(nome, pageable);
		return  funcionarios != null ? ResponseEntity.ok(funcionarios) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/buscarPorCpf/{cpf}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Funcionario>> buscarPorCpf(@PathVariable String cpf, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioService.buscarPorCpf(cpf, pageable);
		return  funcionarios != null ? ResponseEntity.ok(funcionarios) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/relatorio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> relatorioFuncionariosAniversariantes(
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate inicio, 
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fim) throws Exception {
		byte[] relatorio = funcionarioService.relatorioFuncionariosAniversariantes(inicio, fim);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}

}
