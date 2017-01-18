package stockage;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Stateless
@LocalBean
public class DaoGarage<T> implements Dao<T>, Serializable {
	private static final long serialVersionUID = 1L;
	//@PersistenceContext(name=" ")
	private EntityManager em;

public DaoGarage() {
	em = Persistence.createEntityManagerFactory("MonVoisinMotard").createEntityManager();
}
	@Override
	public T find(int id) {
		return (T) em.createNamedQuery("find").setParameter("cle", id).getSingleResult();
	}

	@Override
	public List<T> findAll() {
		return em.createNamedQuery("findAll").getResultList();
	}
	
	@Override
	public void modifier(T obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		}

	@Override
	public void supprimer(T obj) {
		em.remove(obj);
	}

	@Override
	public void inserer(T obj) {
		em.persist(obj);
	}

}
