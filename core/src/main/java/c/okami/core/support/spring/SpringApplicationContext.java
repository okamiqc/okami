package c.okami.core.support.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateOperations;

public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    private static final String REGISTER_CACHED_KEY = "memcachedProvider";
    private static final String REGISTER_HIBERNATE_TEMPLATE_KEY = "hibernateTemplate";
    private static final String REGISTER_JDBC_TEMPLATE_KEY = "jdbcTemplate";


    @Override
    @SuppressWarnings("unchecked")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContext.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        Object object = null;
        if (applicationContext != null) {
            object = applicationContext.getBean(name);
        }
        return (T) object;
    }

    /**
     * 获取HibernateTemplate
     *
     * @return HibernateTemplate
     */
    public static HibernateOperations getHibernateTemplate() {
        return getBean(REGISTER_HIBERNATE_TEMPLATE_KEY);
    }


    /**
     * 获取JdbcTemplate
     *
     * @return JdbcTemplate
     */
    public static JdbcTemplate getJdbcTemplate() {
        return getBean(REGISTER_JDBC_TEMPLATE_KEY);
    }

}
