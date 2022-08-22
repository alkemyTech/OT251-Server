package com.alkemy.ong.dto.response.pagination;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResultResponse<T> {

	private List<T> content = new ArrayList<>();
	private String next_page_url;
	private String previous_page_url;

}
