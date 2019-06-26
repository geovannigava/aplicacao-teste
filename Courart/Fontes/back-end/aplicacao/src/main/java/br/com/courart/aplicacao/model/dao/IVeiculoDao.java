package br.com.courart.aplicacao.model.dao;

import java.time.LocalDate;
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
	@Query(" SELECT veiculo FROM Veiculo veiculo WHERE veiculo.modelo LIKE %:modelo% ")
	public List<Veiculo> buscarPorModelo(@Param("modelo") String modelo);
	
	/**
	 * Listar Todos os Veículo ordenados por ID.
	 * 
	 * @return List<Veiculo>
	 */
	public List<Veiculo> findAllByOrderByIdVeiculoAsc();
	
	/**
	 * Buscar Veículos ativos por período
	 * 
	 * @param inicio
	 * @param fim
	 * @return List<Veiculo>
	 */
	@Query(" SELECT veiculo FROM Veiculo veiculo WHERE veiculo.dataCadastro >= :inicio AND veiculo.dataCadastro <= :fim AND veiculo.ativoEnum = 0")
	public List<Veiculo> buscarVeiculosAtivos(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
	

}
