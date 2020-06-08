package john.blog.dto;

import java.util.List;

public class PageBean {

    public static final Integer DEFAULT_MESSAGE_PAGE_SIZE = 10;
    public static final Integer DEFAULT_BLOG_COMMENT_PAGE_SIZE = 5;
    public static final Integer DEFAULT_ALBUM_COMMENT_PAGE_SIZE = 5;

    // A number that represents the current page
    private Integer currentPage;
    // The number of data shown in each page
    private Integer pageSize;
    // The number of all data
    private Integer totalCount;
    // The number of all pages
    private Integer totalPages;
    // The specific data shown in the page
    private List list;

    public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        // If current page number is not provided, set to Page One by default
        if (currentPage == null) {
            this.currentPage = 1;
        }

        // If page size is not provided, display Five pieces of data in each page by default
        if (this.pageSize == null) {
            this.pageSize = 5;
        }

        // Calculate totalPages
        this.totalPages = (int) Math.ceil(1.0 * this.totalCount / this.pageSize);

        // Security check
        if (this.currentPage > this.totalPages) {
            // If searched page is larger the the number of total pages, return the last page
            this.currentPage = this.totalPages;
        }
        if (this.currentPage < 1) {
            // If searched page is smaller than 1, return the first page
            this.currentPage = 1;
        }
    }

    // Helper function when searching messages
    public Integer startPosition() {
        return (this.currentPage - 1) * this.pageSize;
    }

    
    /* Getters and Setters */
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
