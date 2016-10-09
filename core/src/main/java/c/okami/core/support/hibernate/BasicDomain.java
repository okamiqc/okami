package c.okami.core.support.hibernate;


import c.okami.core.support.hibernate.query.hql.HQLQueryBuilder;
import c.okami.core.support.hibernate.query.qbc.EntityQueryBuilder;

import java.io.Serializable;

/**
 * 基础领域对象
 * 充血模型
 * 使用jpa注解配置
 */
public abstract class BasicDomain<T> extends DomainHibernateImpl<Integer,T> implements Serializable {

    public synchronized static HQLQueryBuilder build() {
        return new HQLQueryBuilder();
    }

    public synchronized static EntityQueryBuilder build(Class clazz) {
        return new EntityQueryBuilder(clazz);
    }

    protected BasicDomain() {

    }
}
