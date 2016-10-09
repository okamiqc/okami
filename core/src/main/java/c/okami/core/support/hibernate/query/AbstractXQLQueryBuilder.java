package c.okami.core.support.hibernate.query;

import org.hibernate.Query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AbstractXQLQueryBuilder<T> extends AbstractQueryBuilder<T> {
    protected static String COUNT_PREFIX = "select count(1) ";
    protected static String FROM = "from";
    protected static String GROUP = "group";

    protected String queryQL;
    protected String countQL;
    protected Map<String, Object> map;

    protected AbstractXQLQueryBuilder() {
    }

    public T queryQL(String queryQL) {
        this.queryQL = queryQL;
        return (T) this;
    }

    public T countQL(String countQL) {
        this.countQL = countQL;
        return (T) this;
    }

    public T parameter(String key, Object value) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put(key, value);
        return (T) this;
    }

    protected void cleanFrom(StringBuffer newQL) {
        if (queryQL.indexOf(FROM) != -1) {
            newQL.append(queryQL.substring(queryQL.indexOf(FROM), queryQL.length()));
        }
        if (queryQL.indexOf(FROM.toUpperCase()) != -1) {
            newQL.append(queryQL.substring(queryQL.indexOf(FROM.toUpperCase()), queryQL.length()));
        }
    }

//    public void cleanGroup() {
//        if (queryKey.indexOf(GROUP) != -1) {
//            queryKey = queryKey.substring(0, queryKey.indexOf(GROUP));
//        }
//        if (queryKey.indexOf(GROUP.toUpperCase()) != -1) {
//            queryKey = queryKey.substring(0, queryKey.indexOf(GROUP.toUpperCase()));
//        }
//    }

    protected String countQL() {
        StringBuffer newQL = new StringBuffer(COUNT_PREFIX);
        cleanFrom(newQL);
        return newQL.toString();
    }

    protected void initParameter(Query query) {
        if (!map.isEmpty()) {
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = map.get(key);
                query.setParameter(key, value);
            }
        }
    }
}
