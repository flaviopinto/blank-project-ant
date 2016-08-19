package br.com.blank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.blank.model.Usuario;

@Repository("usuarioDao")
@Transactional
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Usuario> listAll() {
		return entityManager.createQuery("from Usuario where enabled=true and mainUser=false", Usuario.class)
				.getResultList();
	}

	public void save(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public void update(Usuario usuario) {
		entityManager.merge(usuario);
	}

	public void delete(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Usuario findBy(String login, String senha) {
		return (Usuario) createCriteria().add(Restrictions.eq("login", login))
				.add(Restrictions.eq("senha", senha)).uniqueResult();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Usuario load(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	private Criteria createCriteria() {
		Session session = ((Session) entityManager.getDelegate());
		return session.createCriteria(Usuario.class);
	}

}
