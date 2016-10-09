package c.okami.core.support.hibernate;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MysqlDialect extends MySQL5Dialect {
    public MysqlDialect() {
        super();
        registerFunction("distance", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "distance(?1,?2,?3,?4)"));
    }
}
