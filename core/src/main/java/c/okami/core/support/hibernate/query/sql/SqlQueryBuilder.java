package c.okami.core.support.hibernate.query.sql;


import com.google.common.collect.Lists;
import c.okami.core.support.spring.SpringApplicationContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlQueryBuilder {
    protected Integer begin() {
        return (page - 1) * size;
    }

    public static SqlQueryBuilder newInstance() {
        return new SqlQueryBuilder();
    }

    private List parameters = new ArrayList();
    private Integer size = 10;
    private Integer page = 1;
    private String sql;
    private boolean limit = false;

    protected boolean isLimit() {
        return limit;
    }

    public SqlQueryBuilder parameter(Object obj) {
        parameters.add(obj);
        return this;
    }

    public SqlQueryBuilder parameter(Object... objs){
        for (Object obj : objs){
            parameters.add(obj);
        }
        return this;
    }

    public SqlQueryBuilder sql(String sql) {
        this.sql = sql;
        return this;
    }

    public SqlQueryBuilder page(Integer page) {
        this.page = page;
        return this;
    }

    public SqlQueryBuilder size(Integer size) {
        this.size = size;
        return this;
    }

    public SqlQueryBuilder limit(boolean limit) {
        this.limit = limit;
        return this;
    }

    private Object[] par() {
        Integer index = parameters.size();
        if (limit) {
            index = index + 2;
            parameters.add(begin());
            parameters.add(size);
        }
        Object[] objs = new Object[index];
        for (int i = 0; i < parameters.size(); i++) {
            objs[i] = parameters.get(i);
        }
        return objs;
    }

    public List list(RowMapper rowMapper) {
        if (limit) {
            sql = sql + " limit ?,?";
        }
        return SpringApplicationContext.getJdbcTemplate().query(sql, par(), rowMapper);
    }

    public <T> T one(RowMapper rowMapper) {
        return (T) SpringApplicationContext.getJdbcTemplate().queryForObject(sql, par(), rowMapper);
    }

    public <T> T one(Class<T> clazz){
        return SpringApplicationContext.getJdbcTemplate().queryForObject(sql,par(),clazz);
    }

    public List<Map<String, Object>> list(){
        List<Map<String,Object>> params = null;
        params = SpringApplicationContext.getJdbcTemplate().queryForList(sql);
        return  params;
    }

    public List<Map<String, Object>> list(Object... args){
        List<Map<String,Object>> params = Lists.newArrayList();
        params.addAll(SpringApplicationContext.getJdbcTemplate().queryForList(sql, args));
        return  params;
    }

    public void execute(){
        SpringApplicationContext.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.createSQLQuery(sql).executeUpdate();
                return null;
            }
        });
    }
}
