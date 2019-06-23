package br.com.courart.aplicacao.model.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.courart.aplicacao.model.Funcionario;
import br.com.courart.aplicacao.model.dao.FuncionarioDao;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	/**
	 * Buscar Funcionarios por nome.
	 * 
	 * @param nome
	 * @return List<Funcionario>
	 */
	public List<Funcionario> buscarPorNome(String nome) {
		return funcionarioDao.buscarPorNome(nome);
	}
	
	/**
	 * Buscar Funcionarios por cpf.
	 * 
	 * @param cpf
	 * @return List<Funcionario>
	 */
	public List<Funcionario> buscarPorCpf(String cpf) {
		return funcionarioDao.buscarPorCpf(cpf);
	}

	/**
	 * Salva Funcionario.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioDao.salvar(funcionario);
	}

	/**
	 * Listar Todos os Funcionarios.
	 * 
	 * @return List<Funcionario>
	 */
	public List<Funcionario> listarTodos() {
		return funcionarioDao.listarTodos();
	}

	/**
	 * Buscar Funcionario por Id.
	 * 
	 * @param id
	 * @return Funcionario
	 */
	public Funcionario buscarPorId(Long id) {
		return funcionarioDao.buscarPorId(id);
	}

	/**
	 * Atualizar dados do Funcionario por Id.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	public Funcionario atualizar(Funcionario funcionario, Long id) {
		Funcionario funcionarioDb = this.buscarPorId(id);
		if(funcionarioDb != null) {
			BeanUtils.copyProperties(funcionario, funcionarioDb, "idFuncionario");
			return funcionarioDao.atualizar(funcionarioDb);
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}

	/**
	 * Remover Veiculo.
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean remover(Long id) {
		try {
			funcionarioDao.remover(id);
			return true;
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
	}

}
