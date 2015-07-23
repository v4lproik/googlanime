package net.v4lproik.googlanime.dao.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractRepositoryHelper<E, I extends Serializable> {

    protected Object getBySimpleCondition(Session currentSession, Class<?> type, String columnName, String columnValue){
        Criteria criteria = currentSession.createCriteria(type);
        if ( columnValue != null) criteria.add(Restrictions.eq(columnName, columnValue));
        return criteria.uniqueResult();
    }

    protected Object getBySimpleCondition(Session currentSession, Class<?> type, Map<String, String> conditions){
        Criteria criteria = currentSession.createCriteria(type);

        for (Map.Entry<String, String> entry : conditions.entrySet())
        {
            if ( entry.getValue() != null) criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }

        return criteria.uniqueResult();
    }

    protected Object getBySimpleConditionObject(Session currentSession, Class<?> type, Map<String, Object> conditions){
        Criteria criteria = currentSession.createCriteria(type);

        for (Map.Entry<String, Object> entry : conditions.entrySet())
        {
            if ( entry.getValue() != null) criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }

        return criteria.uniqueResult();
    }
}