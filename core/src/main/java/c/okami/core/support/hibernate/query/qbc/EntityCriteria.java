package c.okami.core.support.hibernate.query.qbc;

import org.hibernate.criterion.DetachedCriteria;

public class EntityCriteria implements IEntityCriteria<EntityCriteria> {
    private DetachedCriteria criteria;

    public EntityCriteria(Class clazz) {
        this.criteria = DetachedCriteria.forClass(clazz);
    }

    @Override
    public EntityCriteria between(String propertyName, Object lo, Object hi) {
        criteria.add(Where.Between.IS.where(propertyName, lo, hi));
        return this;
    }

    @Override
    public EntityCriteria notBetween(String propertyName, Object lo, Object hi) {
        criteria.add(Where.Between.NOT.where(propertyName, lo, hi));
        return this;
    }

    @Override
    public EntityCriteria in(String propertyName, Object... values) {
        criteria.add(Where.In.IS.where(propertyName));
        return this;
    }

    @Override
    public EntityCriteria notIn(String propertyName, Object... values) {
        criteria.add(Where.In.NOT.where(propertyName, values));
        return this;
    }

    @Override
    public EntityCriteria asc(String propertyName) {
        criteria.addOrder(OrderBy.ASC.order(propertyName));
        return this;
    }

    @Override
    public EntityCriteria desc(String propertyName) {
        criteria.addOrder(OrderBy.DESC.order(propertyName));
        return this;
    }

    @Override
    public EntityCriteria isNull(String propertyName) {
        criteria.add(Where.Null.IS.where(propertyName));
        return this;
    }

    @Override
    public EntityCriteria isNotNull(String propertyName) {
        criteria.add(Where.Null.NOT.where(propertyName));
        return this;
    }

    @Override
    public EntityCriteria isEmpty(String propertyName) {
        criteria.add(Where.Empty.IS.where(propertyName));
        return this;
    }

    @Override
    public EntityCriteria isNotEmpty(String propertyName) {
        criteria.add(Where.Empty.NOT.where(propertyName));
        return this;
    }


    @Override
    public EntityCriteria likeEnd(String propertyName, Object value) {
        criteria.add(Where.LIKE.where(propertyName, value + "%"));
        return this;
    }

    @Override
    public EntityCriteria likeStart(String propertyName, Object value) {
        criteria.add(Where.LIKE.where(propertyName, "%" + value));
        return this;
    }

    @Override
    public EntityCriteria like(String propertyName, Object value) {
        criteria.add(Where.LIKE.where(propertyName, "%" + value + "%"));
        return this;
    }

    @Override
    public EntityCriteria lt(String propertyName, Object value) {
        criteria.add(Where.LT.where(propertyName, value));
        return this;
    }

    @Override
    public EntityCriteria le(String propertyName, Object value) {
        criteria.add(Where.LE.where(propertyName, value));
        return this;
    }

    @Override
    public EntityCriteria ge(String propertyName, Object value) {
        criteria.add(Where.GE.where(propertyName, value));
        return this;
    }

    @Override
    public EntityCriteria gt(String propertyName, Object value) {
        criteria.add(Where.GT.where(propertyName, value));
        return this;
    }

    @Override
    public EntityCriteria eq(String propertyName, Object value) {
        criteria.add(Where.EQ.where(propertyName, value));
        return this;
    }

    @Override
    public EntityCriteria ne(String propertyName, Object value) {
        criteria.add(Where.NE.where(propertyName, value));
        return this;
    }

    public DetachedCriteria criteria() {
        return criteria;
    }
}
