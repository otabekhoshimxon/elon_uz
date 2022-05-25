package com.company.dto;

import com.company.enums.StatusPost;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class PostDTO {
    private Integer  id;
    private String title;
    private Double price;
    private String description;
    private Timestamp createdDate;
    private StatusPost statusPost=StatusPost.ACTIVE;
    private CustomerDTO customerDTO;

}
