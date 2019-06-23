package br.com.courart.aplicacao.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.courart.aplicacao.model.Funcionario;

public interface IFuncionarioDao extends AbstractRepository<Funcionario, Long>{
	
	/**
	 * Buscar Funcionarios por nome.
	 * 
	 * @param nome
	 * @return List<Funcionario>
	 */
	@Query(" SELECT funcionario FROM Funcionario funcionario WHERE funcionario.nome LIKE %:nome%")
	public List<Funcionario> buscarPorNome(@Param("nome") String nome);
	
	/**
	 * Buscar Funcionarios por cpf.
	 * 
	 * @param cpf
	 * @return List<Funcionario>
	 */
	@Query(" SELECT funcionario FROM Funcionario funcionario WHERE funcionario.cpf = :cpf")
	public List<Funcionario> buscarPorCpf(@Param("cpf") String cpf);

}
