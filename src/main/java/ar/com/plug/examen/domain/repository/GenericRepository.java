package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.service.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericRepository<T> {

    protected SessionFactory factory;

    protected Class<T> type;

    public T saveOrUpdate(T instance){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(instance);
            tx.commit();
        }
        // tiene que estar aspectado por un transactional.
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return instance;
    }

    public T getById(Long id){
        return null;
    }

    public T getAll(Filter filter){
        return null;
    }

    public List<T> getAll() {
        List objects = null;
        try {
            Session session = factory.openSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(type);
            Root<T> root = cr.from(type);
            cr.select(root);

            Query<T> query = session.createQuery(cr);
            List<T> results = query.getResultList();
            objects = results;

        } catch (HibernateException e) {
           throw e;
        }
        return objects;

    }

    public void delete(T instance){

    }





}
