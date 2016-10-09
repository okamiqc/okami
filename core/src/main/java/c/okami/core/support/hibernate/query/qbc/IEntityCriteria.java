package c.okami.core.support.hibernate.query.qbc;

public interface IEntityCriteria<T> {

    T between(String propertyName, Object lo, Object hi);

    T notBetween(String propertyName, Object lo, Object hi);

    T in(String propertyName, Object... values);

    T notIn(String propertyName, Object... values);

    T asc(String propertyName);

    T desc(String propertyName);

    T isNull(String propertyName);

    T isNotNull(String propertyName);

    T isEmpty(String propertyName);

    T isNotEmpty(String propertyName);

    T likeEnd(String propertyName, Object value);

    T likeStart(String propertyName, Object value);

    T like(String propertyName, Object value);

    T lt(String propertyName, Object value);

    T le(String propertyName, Object value);

    T ge(String propertyName, Object value);

    T gt(String propertyName, Object value);

    T eq(String propertyName, Object value);

    T ne(String propertyName, Object value);

}

