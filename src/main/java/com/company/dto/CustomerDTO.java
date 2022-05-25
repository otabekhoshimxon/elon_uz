package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CustomerDTO {
    private Integer  id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private PostDTO postDTO;


}