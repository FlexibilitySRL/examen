package ar.com.plug.examen.app.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageDto<T>
{
    @JsonProperty
    private List<T> content;
    @JsonProperty
    private int currentPage;
    @JsonProperty
    private long totalItems;
    @JsonProperty
    private int totalPages;

    public PageDto(Page<T> page)
    {
        this.content = page.getContent();
        this.currentPage = page.getNumber();
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
