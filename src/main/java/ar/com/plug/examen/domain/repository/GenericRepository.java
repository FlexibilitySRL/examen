package ar.com.plug.examen.domain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericRepository<T> {

    protected SessionFactory factory;

    protected Class<T> type;

    public T saveOrUpdate(T instance){
        Session session = factory.openSession();
        session.saveOrUpdate(instance);
        return instance;
    }

    public void delete(T instance){
        Session session = factory.openSession();
        session.delete(instance);
    }

    public T getById(Long id){
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        Predicate idPredicate = cb.equal(root.get("id"), id);
        cq.where(idPredicate);
        TypedQuery<T> query = session.createQuery(cq);
        return query.getSingleResult();
    }

    public List<T> getAll() {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cr = cb.createQuery(type);
        Root<T> root = cr.from(type);
        cr.select(root);
        Query<T> query = session.createQuery(cr);
        List<T> results = query.getResultList();
        return results;
    }

}
