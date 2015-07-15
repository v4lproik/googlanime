package net.v4lproik.googlanime.dao.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractRepository<E, I extends Serializable> {

    private final Class<E> klazz;

    public SessionFactory sessionFactory;

    public AbstractRepository(final Class<E> klazz, final SessionFactory sessionFactory) {
        this.klazz = klazz;
        this.klazz.getName();
        this.sessionFactory = sessionFactory;
    }

    public AbstractRepository() {
        this.klazz = null;
    }

    public List<E> list() {
        return currentSession().createCriteria(klazz).list();
    }

    public I save(E entity) {
        Transaction tx=currentSession().beginTransaction();
        Object idSave = currentSession().save(entity);
        tx.commit();

        return (I) idSave;
    }

    public void saveOrUpdate(E entity) {
        Transaction tx=currentSession().beginTransaction();
        currentSession().saveOrUpdate(entity);
        tx.commit();
    }

    public void update(E entity) {
        currentSession().update(entity);
    }

    public E getById(I id) {
        return (E) currentSession().get(klazz, id);
    }

    public List<E> getBySimpleCondition(String column, Object value) {
        Criteria c1 = currentSession().createCriteria(klazz);
        return c1.add(Restrictions.eq(column, value)).list();
    }

    public void delete(E entity) {
        Transaction tx=currentSession().beginTransaction();
        currentSession().delete(entity);
        tx.commit();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}