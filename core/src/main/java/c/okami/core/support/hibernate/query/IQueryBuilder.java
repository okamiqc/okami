package c.okami.core.support.hibernate.query;

import java.util.List;

public interface IQueryBuilder<T> {

    List list();

    T page(Integer page);

    T size(Integer pageSize);

    Integer count();

    PageData nowPage();

    IQueryBuilder limit(boolean limit);

    <T> T one();

    Integer maxPage();

}
