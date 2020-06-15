package entity;

/**
 * 基础实体类
 */
public class BasicEntity {
    private int currentPage = 1;  //当前页
    private int pageSize = 5;  //当前页显示数量

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
}
