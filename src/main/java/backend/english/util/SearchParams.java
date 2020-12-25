package backend.english.util;

import java.util.Objects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchParams that = (SearchParams) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(pageNumber, that.pageNumber) &&
                Objects.equals(pageLimit, that.pageLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, pageNumber, pageLimit);
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
