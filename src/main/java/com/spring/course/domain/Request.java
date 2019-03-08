package com.spring.course.domain;

import com.spring.course.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private Long id;
    private String subject;
    private String description;
    private Date creationDate;
    private RequestState state;
    private List<RequestStage> stages = new ArrayList<>();
}
