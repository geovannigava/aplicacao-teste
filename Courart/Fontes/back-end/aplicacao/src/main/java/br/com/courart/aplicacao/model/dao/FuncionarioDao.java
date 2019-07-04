package br.com.courart.aplicacao.model.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.courart.aplicacao.model.Funcionario;
import br.com.courart.aplicacao.model.dao.page.PaginacaoDao;


@Component
public class FuncionarioDao extends PaginacaoDao {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IFuncionarioDao iFuncionarioDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Buscar Funcionarios por nome.
	 * 
	 * @param nome
	 * @param pageable 
	 * @return List<Funcionario>
	 */
	public Page<Funcionario> buscarPorNome(String nome, Pageable pageable) {
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.nome LIKE :nome",
				Funcionario.class);
		query.setParameter("nome", ("%"+nome+"%")); 
		Query queryCount = entityManager.createQuery("SELECT count(funcionario) FROM Funcionario funcionario WHERE funcionario.nome LIKE :nome");
		queryCount.setParameter("nome", ("%"+nome+"%")); 
		return paginar(query, queryCount, pageable);
	}
	
	/**
	 * Buscar Funcionarios por cpf.
	 * 
	 * @param cpf
	 * @return List<Funcionario>
	 */
	public Page<Funcionario> buscarPorCpf(String cpf, Pageable pageable) {
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.cpf = :cpf", 
				Funcionario.class);
		query.setParameter("cpf", cpf); 
		Query queryCount = entityManager.createQuery("SELECT count(funcionario) FROM Funcionario funcionario WHERE funcionario.cpf = :cpf");
		queryCount.setParameter("cpf", cpf); 
		return paginar(query, queryCount, pageable);
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
	public Page<Funcionario> listarTodos(Pageable pageable) {
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT funcionario FROM Funcionario funcionario", Funcionario.class);
		Query queryCount = entityManager.createQuery("SELECT count(funcionario) FROM Funcionario funcionario");
		return paginar(query, queryCount, pageable);
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
		Query query = entityManager.createNativeQuery( "SELECT * FROM courart.tb_funcionarios WHERE (extract(DOY FROM data_nascimento) "
				+ " BETWEEN :diaInicio AND :diaFim ) ORDER BY id_funcionario ASC", Funcionario.class); 
		query.setParameter("diaInicio", inicio.getDayOfYear()); 
		query.setParameter("diaFim", fim.getDayOfYear()); 
		return query.getResultList(); 
		
	}

}
