package com.company.repository;

import com.company.container.ComponentContainer;
import com.company.dto.PostDTO;
import com.company.mappers.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createPost(String title, Double price, String description, Integer id) {
        String  sql="INSERT INTO post(title,price,description,customer_id)" +
                "values(:title,:price,:description,:customer_id);";
        Map<String ,Object> map=new HashMap<>();
        map.put("title",title);
        map.put("price",price);
        map.put("description",description);
        map.put("customer_id",id);
        int update = namedParameterJdbcTemplate.update(sql, map);
        System.out.println("update = " + update);

    }

    public List<PostDTO> getOwnPost()
    {
        String  sql="select * from post where customer_id='"+ ComponentContainer.currentCustomer.getId() +"' and status='ACTIVE';";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(PostDTO.class));
    }

    public  List<PostDTO> getAllPost() {
        String  sql="select p.id pID,p.title pTitle,p.price pPrice,p.description  description ,p.created_date dateOf,c.name cName,c.phone cPhone from post p " +
                "inner join customer c on c.id =p.customer_id " +
                " where status='ACTIVE';";
        return jdbcTemplate.query(sql,new PostMapper());

    }

    public boolean delPost(int i) {

        String  sql="UPDATE  post SET status ='BLOCKED' where id='"+i+"';";
        int update = jdbcTemplate.update(sql);
        return update!=0;

    }

    public boolean isAvailablePost(int i) {
        String  sql="SELECT  COUNT(*) FROM post where id='"+i+"';";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer!=0;
    }

    public void updateFullPost(int i, String title, double price, String description) {

        String sql="UPDATE post set title=:title,price=:price,description=:desc where id=:postId and customer_id=:custId;";
        Map<String,Object> postField=new HashMap<>();
        postField.put("title",title);
        postField.put("price",price);
        postField.put("desc",description);
        postField.put("postId",i);
        postField.put("custId",ComponentContainer.currentCustomer.getId());
        int update = namedParameterJdbcTemplate.update(sql, postField);
        System.out.println("update = " + update);
    }
}
