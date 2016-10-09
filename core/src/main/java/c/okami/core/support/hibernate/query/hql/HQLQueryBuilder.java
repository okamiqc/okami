package c.okami.core.support.hibernate.query.hql;


import c.okami.core.support.hibernate.query.AbstractXQLQueryBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class HQLQueryBuilder extends AbstractXQLQueryBuilder<HQLQueryBuilder> {
    @Override
    public HQLQueryBuilder limit(boolean limit) {
        setLimit(limit);
        return this;
    }

    /**
     * 数据查询必须有限制，不可查询全部
     *
     * @return
     */
    @Override
    public List list() {
        return getDataSession().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryQL);
                initParameter(query);
                if (isLimit()) {
                    //设置开始
                    query.setFirstResult(begin());
                    //设置每页数
                    query.setMaxResults(pageSize());
                }
                return query.list();
            }
        });
    }

    /**
     * count  将select 这类sql  替换select =》 select count(*)
     *
     * @return
     */
    @Override
    public Integer count() {
        return getDataSession().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = null;
                if (countQL == null) {
                    query = session.createQuery(countQL());
                } else {
                    query = session.createQuery(countQL);
                }
                initParameter(query);
                Object obj = query.uniqueResult();
                return obj == null ? 0 : ((Number) obj).intValue();
            }
        });
    }

    @Override
    public <T> T one() {
        return (T) getDataSession().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryQL);
                initParameter(query);
                return query.uniqueResult();
            }
        });
    }
}
