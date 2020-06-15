package utils;

import java.util.List;

/**
 * @author mingoxu
 * @date 2020-06-09
 * @description 分页工具类
 */
public class Page<T> {

    /**
     * 结果列表
     */
    private List<T> data;

    /**
     * 结果列表数
     */
    private int dataSize;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 每页显示个数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private int recordsRows;

    /**
     * 总页数
     */
    private int pageNum;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordsRows() {
        return recordsRows;
    }

    public void setRecordsRows(int recordsRows) {
        this.recordsRows = recordsRows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
