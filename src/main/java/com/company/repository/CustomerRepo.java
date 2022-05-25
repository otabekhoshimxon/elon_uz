package com.company.repository;

import com.company.container.ComponentContainer;
import com.company.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean hasNumber(String phone) {
        String  sql="SELECT COUNT (*)FROM customer WHERE phone='"+phone+"';";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer==0;

    }

    public void regist(String name, String surname, String phone, String password) {
        String insert="INSERT INTO customer (name,surname,phone,password) values (:name,:surname,:phone,:password)";
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("name",name);
        objectMap.put("surname",surname);
        objectMap.put("phone",phone);
        objectMap.put("password",password);
        namedParameterJdbcTemplate.update(insert,objectMap);

    }

    public boolean authorization(String phone, String password) {
        String sql="SELECT * from customer where phone='"+phone+"' and password='"+password+"';";
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("phone",phone);
        objectMap.put("pass",password);
        CustomerDTO customerDTO = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CustomerDTO.class));
        if (customerDTO!=null)
        {
            ComponentContainer.currentCustomer=customerDTO;
          return true;
        }
    return false;

    }
}
