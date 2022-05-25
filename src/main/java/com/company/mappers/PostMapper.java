package com.company.mappers;

import com.company.dto.CustomerDTO;
import com.company.dto.PostDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PostMapper implements RowMapper<PostDTO> {
    @Override
    public PostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        PostDTO postDTO=new PostDTO();
        postDTO.setId(rs.getInt("pID"));
        postDTO.setTitle(rs.getString("pTitle"));
        postDTO.setPrice( rs.getDouble("pPrice"));
        postDTO.setDescription(rs.getString("description"));
        postDTO.setCreatedDate( rs.getTimestamp("dateOf"));

        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setName(rs.getString("cName"));
        customerDTO.setPhone(rs.getString("cPhone"));

        postDTO.setCustomerDTO(customerDTO);




        return postDTO;
    }
}
