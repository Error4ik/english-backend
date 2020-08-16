package backend.english.util;

import java.util.UUID;

public class SearchParams {
    private UUID category;
    private Integer pageNumber;
    private Integer pageLimit;

    public SearchParams() {
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    @Override
    public String toString() {
        return String.format(
                "SearchParams {categoryId = %s, pageNumber = %s, pageLimit = %s}",
                this.getCategory(),
                this.getPageNumber(),
                this.getPageLimit()
        );
    }
}
