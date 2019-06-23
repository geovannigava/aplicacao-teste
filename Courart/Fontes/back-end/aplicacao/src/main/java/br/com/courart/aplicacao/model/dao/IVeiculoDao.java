package br.com.courart.aplicacao.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.courart.aplicacao.model.Veiculo;

public interface IVeiculoDao extends AbstractRepository<Veiculo, Long>{
	
	/**
	 * Buscar Veículo por placa.
	 * 
	 * @param placa
	 * @return List<Veiculo>
	 */
	@Query(" SELECT veiculo FROM Veiculo veiculo WHERE veiculo.placa = :placa ")
	public List<Veiculo> buscarPorPlaca(@Param("placa") String placa);
	
	/**
	 * Buscar Veículo por modelo.
	 * 
	 * @param modelo
	 * @return List<Veiculo>
	 */
	@Query(" SELECT veiculo FROM Veiculo veiculo WHERE veiculo.modelo = :modelo ")
	public List<Veiculo> buscarPorModelo(@Param("modelo") String modelo);

}
