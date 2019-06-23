package br.com.courart.aplicacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.courart.aplicacao.model.Veiculo;
import br.com.courart.aplicacao.model.service.VeiculoService;


@RestController
@RequestMapping("veiculo")
@SuppressWarnings("all") 
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> salvar(@Valid @RequestBody Veiculo veiculo){
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.salvar(veiculo));
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Veiculo>> listarTodos() {
		List<Veiculo> veiculos = veiculoService.listarTodos();
		return  veiculos != null ? ResponseEntity.ok(veiculos) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
		Veiculo veiculo = veiculoService.buscarPorId(id);
		return veiculo != null ? ResponseEntity.ok(veiculo) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> atualizar(@Valid @RequestBody Veiculo veiculo, @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.atualizar(veiculo, id));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> remover(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.remover(id));
	}
	
	@RequestMapping(value = "/buscarPorPlaca/{placa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Veiculo>> buscarPorPlaca(@PathVariable String placa) {
		List<Veiculo> veiculos = veiculoService.buscarPorPlaca(placa);
		return  veiculos != null ? ResponseEntity.ok(veiculos) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/buscarPorModelo/{modelo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Veiculo>> buscarPorModelo(@PathVariable String modelo) {
		List<Veiculo> veiculos = veiculoService.buscarPorModelo(modelo);
		return  veiculos != null ? ResponseEntity.ok(veiculos) : ResponseEntity.notFound().build();
	}

}
