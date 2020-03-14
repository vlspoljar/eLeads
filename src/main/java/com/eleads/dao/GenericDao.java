package com.eleads.dao;

import com.eleads.annotation.CdiIntercept;
import com.eleads.dto.GenericDto;
import com.eleads.util.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko
 */
@CdiIntercept
public abstract class GenericDao<E, T extends GenericDto<PK>, PK extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private Session session;

    protected abstract E formEntity(T dto);

    protected abstract T formDTO(E entity);

    protected abstract Class<?> entityClass();

    protected abstract Class<?> dtoClass();

    public T create(T dto) {
        try {
            E entity = formEntity(dto);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.flush();
            return formDTO(entity);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public T edit(T dto) {
        try {
        E entity = formEntity(dto);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return findByPrimaryKey(dto.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(PK primaryKey) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Object tempEntity = session.load(entityClass(), primaryKey);
            session.delete(tempEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public T findByPrimaryKey(PK primaryKey) {
        return formDTO(findEntityByPrimaryKey(primaryKey));
    }

    @SuppressWarnings("unchecked")
    protected E findEntityByPrimaryKey(PK primaryKey) {
        if (primaryKey != null) {
            String sql = (getBasicSql().toLowerCase().contains("where") ? getBasicSql().concat(" and ") : getBasicSql().concat(" where ")).concat(StringUtils.uncapitalize(entityClass().getSimpleName())).concat(".id").concat(" = ").concat(":primaryKey");
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createNativeQuery(sql);
            try {
                query.setParameter("primaryKey", primaryKey);
            } catch (IllegalArgumentException iae) {
                if (StringUtils.isNumeric(primaryKey.toString())) {
                    query.setParameter("primaryKey", Integer.parseInt(primaryKey.toString()));
                } else if (primaryKey.toString().length() == 1) {
                    query.setParameter("primaryKey", primaryKey.toString().trim().charAt(0));
                }
            }
            try {
                return (E) query.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String getBasicSql() {
        return "select " + StringUtils.uncapitalize(entityClass().getSimpleName()) + " from " + entityClass().getSimpleName() + " " + StringUtils.uncapitalize(entityClass().getSimpleName()) + " ";
    }

    public String getDefaultOrderBySql() {
        return " ";
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> listDto = new ArrayList<>();
        String sql = getBasicSql();
        if (StringUtils.isNotBlank(sql)) {
            sql = sql.concat(getDefaultOrderBySql());
        }
        session = HibernateUtil.getSessionFactory().openSession();
        List<E> listEntity = session.createNativeQuery(sql).getResultList();
        if (listEntity != null && !listEntity.isEmpty()) {
            for (E e : listEntity) {
                listDto.add(formDTO(e));
            }
        }
        return listDto;
    }

    // getters and setters
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
