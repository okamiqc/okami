package c.okami.core.support.hibernate.query.qbc;


import c.okami.core.exception.QueryException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

public enum Where {
    EQ("="), GT(">"), GE(">="), LT("<"), LE("<="), LIKE("like"), NE("<>");

    /**
     * OBC专用
     *
     * @param propertyName
     * @param value
     * @return
     */
    public Criterion where(String propertyName, Object value) {
        switch (this) {
            case EQ:
                return Restrictions.eq(propertyName, value);
            case GT:
                return Restrictions.gt(propertyName, value);
            case GE:
                return Restrictions.ge(propertyName, value);
            case LT:
                return Restrictions.lt(propertyName, value);
            case LE:
                return Restrictions.le(propertyName, value);
            case NE:
                return Restrictions.ne(propertyName, value);
            case LIKE:
                return Restrictions.like(propertyName, value);
            default:
                throw new QueryException("enum-where is null.pls choose one!");
        }
    }

    private String desc;

    private Where(String desc) {
        this.desc = desc;
    }

    public enum In {
        NOT, IS;

        /**
         * OBC专用
         *
         * @param propertyName
         * @return
         */
        public Criterion where(String propertyName, Object... value) {
            switch (this) {
                case IS:
                    return Restrictions.in(propertyName, value);
                case NOT:
                    Restrictions.not(Restrictions.in(propertyName, value));
                default:
                    throw new QueryException("enum-Between is null.pls choose one!");
            }
        }

        public Criterion where(String propertyName, Collection values) {
            switch (this) {
                case IS:
                    return Restrictions.in(propertyName, values);
                case NOT:
                    Restrictions.not(Restrictions.in(propertyName, values));
                default:
                    throw new QueryException("enum-Between is null.pls choose one!");
            }
        }
    }

    public enum Between {
        NOT, IS;

        /**
         * OBC专用
         *
         * @param propertyName
         * @param lo
         * @param hi
         * @return
         */
        public Criterion where(String propertyName, Object lo, Object hi) {
            switch (this) {
                case IS:
                    return Restrictions.between(propertyName, lo, hi);
                case NOT:
                    return Restrictions.not(Restrictions.between(propertyName, lo, hi));
                default:
                    throw new QueryException("enum-Between is null.pls choose one!");
            }
        }
    }

    public enum Null {
        NOT, IS;

        /**
         * OBC专用
         *
         * @param propertyName
         * @return
         */
        public Criterion where(String propertyName) {
            switch (this) {
                case IS:
                    return Restrictions.isNull(propertyName);
                case NOT:
                    return Restrictions.isNotNull(propertyName);
                default:
                    throw new QueryException("enum-Between is null.pls choose one!");
            }
        }
    }

    public enum Empty {
        NOT, IS;

        /**
         * OBC专用
         *
         * @param propertyName
         * @return
         */
        public Criterion where(String propertyName) {
            switch (this) {
                case IS:
                    return Restrictions.isEmpty(propertyName);
                case NOT:
                    return Restrictions.isNotEmpty(propertyName);
                default:
                    throw new QueryException("enum-Between is null.pls choose one!");
            }
        }
    }
}
