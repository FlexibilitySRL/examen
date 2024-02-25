package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.service.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericRepository<T> {

    private final SessionFactory factory;

    public GenericRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public T saveOrUpdate(T instance){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
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

    public void delete(T instance){

    }

}
