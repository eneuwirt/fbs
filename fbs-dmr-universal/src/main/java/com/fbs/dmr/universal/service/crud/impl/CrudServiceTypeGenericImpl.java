package com.fbs.dmr.universal.service.crud.impl;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fbs.dmr.universal.model.type.Type;
import com.fbs.dmr.universal.service.crud.CrudServiceType;

public class CrudServiceTypeGenericImpl<T extends Type, ID extends Serializable> extends CrudServiceGenericImpl<T, ID> implements
        CrudServiceType<T, ID>
{

    public CrudServiceTypeGenericImpl(Class<T> entityClass)
    {
        super(entityClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findForDescription(String description)
    {
        T result = null;
        Query query;
        String queryString;

        try
        {
            queryString = String.format("SELECT t FROM %s t WHERE t.description = ?1", this.entityClass.getName());

            query = em.createQuery(queryString);
            query.setParameter(1, description);

            result = (T) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }

        return result;
    }

}
