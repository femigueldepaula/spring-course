package com.spring.course.domain;

import com.spring.course.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStage {
    private Long id;
    private String description;
    private Date realizationDate;
    private RequestState state;
    private Request request;
    private User user;
}
