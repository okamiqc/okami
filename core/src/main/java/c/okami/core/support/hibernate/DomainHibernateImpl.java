package c.okami.core.support.hibernate;


import c.okami.core.support.annotation.Domain;
import c.okami.core.support.spring.SpringApplicationContext;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateOperations;

import java.io.Serializable;

public abstract class DomainHibernateImpl<K extends Serializable,T> implements IBasicDomain<K,T> {
    /**
     * 获取HibernateTemplate
     *
     * @return
     */
    protected static HibernateOperations getDatabaseSession() {
        return SpringApplicationContext.getHibernateTemplate();
    }

    /**
     * 得到持久化对象的类型
     *
     * @return 持久化类的类
     */
    protected Class<? extends IBasicDomain> getPersistentClass() {
//        Class clazz = new Object() {
//            public Class getClassForStatic() {
//                return this.getClass();
//            }
//        }.getClassForStatic();
        Class clazz = this.getClass();
        if (clazz.isAnnotationPresent(Domain.class)) {
            Domain domain = (Domain) clazz.getAnnotation(Domain.class);
            return domain.clazz();
        }
        return clazz;
    }

    protected String getPersistentClassName() {
        return getPersistentClass().getName();
    }

    @Override
    public T findById(LockMode lock) {
        T entity = (T) getDatabaseSession().get(getPersistentClass(), getId(), lock);
        return entity;
    }


    @Override
    public T findById() {
        T entity = (T) getDatabaseSession().get(getPersistentClass(), getId());
        return entity;
    }

    @Override
    public T loadById() {
        T entity = (T) getDatabaseSession().load(getPersistentClass(), getId());
        return entity;
    }

    @Override
    public T loadById(LockMode lock) {
        T entity = (T) getDatabaseSession().load(getPersistentClass(), getId(), lock);
        return entity;
    }

    @Override
    public T save() {
        getDatabaseSession().save(getPersistentClassName(), this);
        return (T) this;
    }

    @Override
    public void update() {
        getDatabaseSession().update(getPersistentClassName(), this);
    }

    @Override
    public void update(LockMode lock) {
        lock(lock);
        update();
    }

    @Override
    public void saveOrUpdate(LockMode lock) {
        lock(lock);
        saveOrUpdate();
    }

    @Override
    public void saveOrUpdate() {
        getDatabaseSession().saveOrUpdate(getPersistentClassName(), this);
        flush();
    }

    @Override
    public void delete() {
        getDatabaseSession().delete(getPersistentClassName(), this);
        flush();
    }

    @Override
    public void delete(LockMode lock) {
        lock(lock);
        delete();
    }

    @Override
    public void deleteById() {
        getDatabaseSession().delete(getPersistentClassName(), findById());
    }


    public void merge() {
        getDatabaseSession().merge(getPersistentClassName(), this);
        flush();
    }


    public void lock(LockMode lockMode) {
        getDatabaseSession().lock(getPersistentClassName(), this, lockMode);
    }

    public void flush() {
        getDatabaseSession().flush();
    }

    public void clear() {
        getDatabaseSession().clear();
    }
}
