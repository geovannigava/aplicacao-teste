package br.com.courart.aplicacao.model.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.courart.aplicacao.model.Funcionario;


@Component
public class FuncionarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IFuncionarioDao iFuncionarioDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Buscar Funcionarios por nome.
	 * 
	 * @param nome
	 * @return List<Funcionario>
	 */
	public List<Funcionario> buscarPorNome(String nome) {
		return iFuncionarioDao.buscarPorNome(nome);
	}
	
	/**
	 * Buscar Funcionarios por cpf.
	 * 
	 * @param cpf
	 * @return List<Funcionario>
	 */
	public List<Funcionario> buscarPorCpf(String cpf) {
		return iFuncionarioDao.buscarPorCpf(cpf);
	}
	
	/**
	 * Salva Funcionario.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	public Funcionario salvar(Funcionario funcionario) {
		return iFuncionarioDao.save(funcionario);
	}

	/**
	 * Listar Todos os Funcionarios ordenando por ID.
	 * 
	 * @return List<Funcionario>
	 */
	public List<Funcionario> listarTodos() {
		return (List<Funcionario>) iFuncionarioDao.findAllByOrderByIdFuncionarioAsc();
	}
	


	/**
	 * Buscar Funcionario por Id.
	 * 
	 * @param id
	 * @return Funcionario
	 */
	public Funcionario buscarPorId(Long id) {
		return iFuncionarioDao.findOne(id);
	}

	/**
	 * Atualizar dados do Funcionario por Id.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	public Funcionario atualizar(Funcionario funcionario) {
		return entityManager.merge(funcionario);
	}

	/**
	 * Remover Funcionario.
	 * 
	 * @param id
	 * @return void
	 */
	public void remover(Long id) {
		iFuncionarioDao.delete(id);
	}

	/**
	 * Listar Todos os Funcionarios aniversariantes por período.
	 * 
	 * @param inicio
	 * @param fim
	 * @return List<Funcionario>
	 */
	public List<Funcionario> buscarFuncionariosAniversariantes(LocalDate inicio, LocalDate fim) {
		Query query = entityManager.createNativeQuery( "SELECT * FROM courart.tb_funcionarios WHERE (extract(DOY FROM data_nascimento) BETWEEN :diaInicio AND :diaFim ) ORDER BY id_funcionario ASC", 
				Funcionario.class); 
		query.setParameter("diaInicio", inicio.getDayOfYear()); 
		query.setParameter("diaFim", fim.getDayOfYear()); 
		return query.getResultList(); 
		
	}

}
