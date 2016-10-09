package c.okami.core.support.hibernate.query.qbc;


import com.alibaba.druid.util.StringUtils;
import c.okami.core.support.hibernate.query.AbstractQueryBuilder;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class EntityQueryBuilder extends AbstractQueryBuilder<EntityQueryBuilder> implements IEntityCriteria<EntityQueryBuilder> {

    @Override
    public EntityQueryBuilder limit(boolean limit) {
        setLimit(limit);
        return this;
    }

    /**
     * 离线版QBC查询对象
     */
    private DetachedCriteria detachedCriteria;
    private DetachedCriteria countDetachedCriteria;

    public EntityQueryBuilder(Class clazz) {
        detachedCriteria = DetachedCriteria.forClass(clazz);
        countDetachedCriteria = DetachedCriteria.forClass(clazz);
    }

    private void addCriterion(Criterion criterion) {
        detachedCriteria.add(criterion);
        countDetachedCriteria.add(criterion);
    }

    private void addOrder(Order order) {
        detachedCriteria.addOrder(order);
        countDetachedCriteria.addOrder(order);
    }

    /**
     * 不等于  <>
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder ne(String propertyName, Object value) {
        addCriterion(Where.NE.where(propertyName, value));
        return this;
    }

    public enum Sort {
        asc, desc
    }

    public EntityQueryBuilder sort(String sortKey, String sortName) {
        Sort sort = Sort.valueOf(sortKey);
        if (Sort.asc.equals(sort)) {
            asc(sortName);
        } else {
            desc(sortName);
        }
        return this;
    }

    /**
     * 等于  =
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder eq(String propertyName, Object value) {
        addCriterion(Where.EQ.where(propertyName, value));
        return this;
    }

    /**
     * 大于  >
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder gt(String propertyName, Object value) {
        addCriterion(Where.GT.where(propertyName, value));
        return this;
    }

    /**
     * 大于等于 >=
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder ge(String propertyName, Object value) {
        addCriterion(Where.GE.where(propertyName, value));
        return this;
    }

    /**
     * 小于 <
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder lt(String propertyName, Object value) {
        addCriterion(Where.LT.where(propertyName, value));
        return this;
    }

    /**
     * 小于等于 <=
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder le(String propertyName, Object value) {
        addCriterion(Where.LE.where(propertyName, value));
        return this;
    }

    /**
     * like %value%
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder like(String propertyName, Object value) {
        if(!StringUtils.isEmpty((String) value)){
            addCriterion(Where.LIKE.where(propertyName, "%" + value + "%"));
        }
        return this;
    }

    /**
     * like %value
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder likeStart(String propertyName, Object value) {
        addCriterion(Where.LIKE.where(propertyName, "%" + value));
        return this;
    }

    /**
     * like value%
     *
     * @param propertyName
     * @param value
     * @return
     */
    public EntityQueryBuilder likeEnd(String propertyName, Object value) {
        addCriterion(Where.LIKE.where(propertyName, value + "%"));
        return this;
    }

    /**
     * is not null
     *
     * @param propertyName
     * @return
     */
    public EntityQueryBuilder isNotNull(String propertyName) {
        addCriterion(Where.Null.NOT.where(propertyName));
        return this;
    }

    /**
     * is null
     *
     * @param propertyName
     * @return
     */
    public EntityQueryBuilder isNull(String propertyName) {
        addCriterion((Where.Null.IS.where(propertyName)));
        return this;
    }

    /**
     * is not Empty
     *
     * @param propertyName
     * @return
     */
    public EntityQueryBuilder isNotEmpty(String propertyName) {
        addCriterion(Where.Empty.NOT.where(propertyName));
        return this;
    }

    /**
     * is Empty
     *
     * @param propertyName
     * @return
     */
    public EntityQueryBuilder isEmpty(String propertyName) {
        addCriterion((Where.Empty.IS.where(propertyName)));
        return this;
    }


    /**
     * @param propertyName
     * @return
     */
    public EntityQueryBuilder asc(String propertyName) {
        addOrder(OrderBy.ASC.order(propertyName));
        return this;
    }

    /**
     * @param propertyName
     * @return
     */
    public EntityQueryBuilder desc(String propertyName) {
        addOrder(OrderBy.DESC.order(propertyName));
        return this;
    }

    /**
     * in (value...)
     *
     * @param propertyName
     * @param values
     * @return
     */
    public EntityQueryBuilder in(String propertyName, Object... values) {
        addCriterion(Where.In.IS.where(propertyName, values));
        return this;
    }

    /**
     * in (value...)
     *
     * @param propertyName
     * @param values
     * @return
     */
    public EntityQueryBuilder in(String propertyName, Collection values) {
        addCriterion(Where.In.IS.where(propertyName, values));
        return this;
    }


    /**
     * not in
     *
     * @param propertyName
     * @param values
     * @return
     */
    public EntityQueryBuilder notIn(String propertyName, Object... values) {
        addCriterion(Where.In.NOT.where(propertyName, values));
        return this;
    }

    /**
     * not in
     *
     * @param propertyName
     * @param values
     * @return
     */
    public EntityQueryBuilder notIn(String propertyName, Collection values) {
        addCriterion(Where.In.NOT.where(propertyName, values));
        return this;
    }

//    /**
//     * and
//     *
//     * @return
//     */
//    public EntityQueryBuilder and(AbstractQuery query, Class clazz) {
//        addCriterion(query.query(clazz, Where.Flag.AND));
//        return this;
//    }
//
//    /**
//     * or
//     *
//     * @return
//     */
//    public EntityQueryBuilder or(AbstractQuery query, Class clazz) {
//        addCriterion(query.query(clazz, Where.Flag.OR));
//        return this;
//    }

    public EntityQueryBuilder between(String propertyName, Object lo, Object hi) {
        addCriterion(Where.Between.IS.where(propertyName, lo, hi));
        return this;
    }

    public EntityQueryBuilder notBetween(String propertyName, Object lo, Object hi) {
        addCriterion(Where.Between.NOT.where(propertyName, lo, hi));
        return this;
    }

    private String countDistinctPropertyName;

    public EntityQueryBuilder countDistinct(String propertyName) {
        countDistinctPropertyName = propertyName;
        return this;
    }

    public List list() {
        return getDataSession().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                if (isLimit()) {
                    //设置开始
                    criteria.setFirstResult(begin());
                    //设置每页数
                    criteria.setMaxResults(pageSize());
                }
                List list = criteria.list();
                return list;
            }
        });
    }

    public Integer count() {
        return getDataSession().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = countDetachedCriteria.getExecutableCriteria(session);
                Projection projection = Projections.rowCount();
                if (countDistinctPropertyName != null) {
                    projection = Projections.countDistinct(countDistinctPropertyName);
                }
                Long count = (Long) criteria.setProjection(projection).uniqueResult();
                return count == null ? 0 : count.intValue();
            }
        });
    }

    public <T> T one() {
        return (T) getDataSession().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
//                if (isLimit()) {
//                    //设置开始
//                    criteria.setFirstResult(begin());
//                    //设置每页数
//                    criteria.setMaxResults(pageSize());
//                }
                criteria.setFirstResult(0);
                criteria.setMaxResults(1);
                return criteria.uniqueResult();
            }
        });
    }


    public Double sum(final String property) {
        return getDataSession().execute(new HibernateCallback<Double>() {
            @Override
            public Double doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                Double sum = (Double) criteria.setProjection(Projections.sum(property)).uniqueResult();
                return sum == null ? 0.0d : sum;
            }
        });
    }
}
