package c.okami.core.support.hibernate.query;


import c.okami.core.support.spring.SpringApplicationContext;
import org.springframework.orm.hibernate3.HibernateOperations;

public abstract class AbstractQueryBuilder<T> implements IQueryBuilder<T> {

    protected Integer begin() {
        return (page - 1) * size;
    }

    private boolean limit = true;

    private Integer size = 10;

    private Integer page = 1;

    protected boolean isLimit() {
        return limit;
    }

    protected void setLimit(boolean limit) {
        this.limit = limit;
    }

    public T page(Integer page) {
        this.page = (page == null) ? 1 : page;
        return (T) this;
    }

    public T size(Integer size) {
        this.size = (size == null) ? 10 : size;
        return (T) this;
    }

    protected Integer pageSize() {
        return size;
    }

    public HibernateOperations getDataSession() {
        return SpringApplicationContext.getHibernateTemplate();
    }

    /**
     * 获取page对象
     *
     * @return
     */

    public PageData nowPage() {
        if (!isLimit()) {
            throw new RuntimeException("limit关闭，不可以使用nowPage方法");
        }
        PageData pageData = new PageData();
        pageData.setCount(count());
        pageData.setList(list());
        pageData.setPageNumber(page);
        pageData.setPageSize(size);
        return pageData;
    }

    @Override
    public Integer maxPage() {
        if (!isLimit()) {
            throw new RuntimeException("limit关闭，不可以使用maxPage方法");
        }
        PageData pageData = new PageData();
        pageData.setCount(count());
        pageData.setPageNumber(page);
        pageData.setPageSize(size);
        return pageData.getLastPageNumber();
    }
}
