package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Response for paginated list")
@JsonRootName(value = "page")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDto<T>
{
	@Schema(description = "List of elements")
	@JsonProperty
	private List<T> content;
	@Schema(description = "Current page during pagination", example = "2")
	@JsonProperty
	private int currentPage;
	@Schema(description = "Total number of items", example = "35")
	@JsonProperty
	private long totalItems;
	@Schema(description = "Total of pages available for pagination", example = "5")
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
