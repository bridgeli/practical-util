package cn.bridgeli.web;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PAGE_SIZE = 30;

    private int totalCount;// 总条数

    private int totalPage;// 总页数

    private int currentPage = 1;// 当前页

    private int pageSize = DEFAULT_PAGE_SIZE;// 每页大小

    private Object searchParam;

    private List<T> pageData;

    public Object getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(Object searchParam) {
        this.searchParam = searchParam;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    private boolean hasNextPage;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        totalPage = (totalCount + pageSize - 1) / pageSize;
        this.totalCount = totalCount;
    }

    public int getTotalPage() {

        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        if (currentPage > getTotalPage()) {
            return totalCount;
        }
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

    public boolean isHasNextPage() {

        hasNextPage = (totalCount > currentPage * pageSize);
        return hasNextPage;
    }

}
