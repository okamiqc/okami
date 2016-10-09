package c.okami.core.support.hibernate;

import c.okami.core.support.hibernate.query.hql.HQLQueryBuilder;
import c.okami.core.support.hibernate.query.qbc.EntityQueryBuilder;

import java.io.Serializable;

/**
 * Created by THY on 2015/10/28.
 */
public abstract class IdentityBasicDomain<K extends Serializable,T> extends DomainHibernateImpl<K,T> implements Serializable {

    public synchronized static HQLQueryBuilder build() {
        return new HQLQueryBuilder();
    }

    public synchronized static EntityQueryBuilder build(Class clazz) {
        return new EntityQueryBuilder(clazz);
    }

    public IdentityBasicDomain(){
    }
}
