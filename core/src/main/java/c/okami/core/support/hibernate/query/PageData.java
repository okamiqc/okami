package c.okami.core.support.hibernate.query;

import java.util.ArrayList;
import java.util.List;

public class PageData {
    //当前页
    private Integer pageNumber = 1;
    //下一页
    private Integer nextPageNumber;
    //前一页
    private Integer previousPageNumber;
    //最后一页
    private Integer lastPageNumber;
    //分页数量标准
    private Integer pageSize = 10;
    //总数量
    private Integer count = null;
    //前页数量
    private Integer after_range_num = 4;
    //后页数量
    private Integer before_range_num = 4;
    //当前页数据集合
    private List list;

    public List<Integer> getPageNumList() {
        List<Integer> pageNumList = new ArrayList<Integer>();
        int to = pageNumber + before_range_num > getLastPageNumber() ? getLastPageNumber() : pageNumber + before_range_num;
        if (pageNumber >= after_range_num) {
            for (int i = pageNumber - after_range_num > 0 ? pageNumber - after_range_num : 1; i <= to; i++) {
                pageNumList.add(i);
            }
        } else {
            for (int i = 1; i <= to; i++) {
                pageNumList.add(i);
            }
        }
        return pageNumList;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getBegin() {
        return (pageNumber - 1) * pageSize;
    }

    public Integer getEnd() {
        return pageNumber * pageSize >= count ? count : pageNumber * pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLastPageNumber() {
        lastPageNumber = (count - 1) / pageSize + 1;
        return lastPageNumber;
    }


    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = null == pageNumber || pageNumber <= 0 ? 1 : pageNumber;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getNextPageNumber() {
        if (pageNumber >= 1 && pageNumber < getLastPageNumber()) {
            nextPageNumber = pageNumber + 1;
        } else {
            nextPageNumber = getLastPageNumber();
        }
        return nextPageNumber;
    }

    public Integer getPreviousPageNumber() {
        if (pageNumber > 1 && pageNumber <= getLastPageNumber()) {
            previousPageNumber = pageNumber - 1;
        } else {
            previousPageNumber = 1;
        }
        return previousPageNumber;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return (int)Math.ceil(Double.parseDouble(count+"")/Double.parseDouble(pageSize+""));
    }
}


