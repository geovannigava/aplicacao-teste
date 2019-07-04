package br.com.courart.aplicacao.model.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T , ID extends Serializable>  extends CrudRepository<T, ID> {

}
