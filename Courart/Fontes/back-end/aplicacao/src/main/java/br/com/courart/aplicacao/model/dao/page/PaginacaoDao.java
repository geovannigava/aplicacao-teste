package br.com.courart.aplicacao.model.dao.page;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class PaginacaoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
	 * Faz a paginação do resultado de uma consulta
	 * 
	 * @param jpql
	 * @param classeRoot
	 * @param pageable
	 * @return Page<T>
	 */
	protected  <T> Page<T> paginar(String jpql, Class<T> classeRoot, Pageable pageable) {
		TypedQuery<T> query = entityManager.createQuery(jpql, classeRoot);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<T>(query.getResultList(), pageable, total(jpql, classeRoot));
	}
	
	
	/**
	 * Adiciona restriçoes de paginacao
	 * 
	 * @param query
	 * @param pageable
	 */
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	/**
	 * Contabiliza o total de regristros em uma query
	 * 
	 * @param jpql 
	 * @param classeRoot
	 * @return Long
	 */
	private <T> Long total(String jpql, Class<?> classeRoot) {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM  "+classeRoot.getSimpleName()+"  e WHERE e IN ( " + jpql + " )");
		return (Long) query.getSingleResult();
	}
	

}
