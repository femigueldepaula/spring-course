package com.spring.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageModel<T> {
    private int totalElements;
    private int pageSize;
    private int totalpage;
    private List<T> elements;
}
