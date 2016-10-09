package c.okami.core.support.hibernate.query.qbc;


import c.okami.core.exception.QueryException;
import org.hibernate.criterion.Order;

/**
 * 用 DESC 表示按倒序排序(即：从大到小排序)
 * 用 ACS   表示按正序排序(即：从小到大排序)
 */
public enum OrderBy {
    ASC, DESC;

    /**
     * OBC专用
     *
     * @param propertyName
     * @return
     */
    public Order order(String propertyName) {
        switch (this) {
            case ASC:
                return Order.asc(propertyName);
            case DESC:
                return Order.desc(propertyName);
            default:
                throw new QueryException("enum OrderBy is null，pls choose one！");
        }
    }
}
