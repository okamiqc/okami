package c.okami.core.support.hibernate;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by THY on 2016/4/1.
 */
public class DataGridPage<T> {

    private Integer draw =0;

    private Integer recordsTotal = 0;

    private Integer recordsFiltered = 0;

    private List<T> data = Lists.newArrayList();

    private Integer start = 0;

    private Long length = 0l;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
