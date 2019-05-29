package com.shiyanlou.jdbc;

import com.shiyanlou.jdbc.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class App {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
        String sql="select * from student";

        RowMapper<Student> rowMapper=new BeanPropertyRowMapper<Student>(Student.class);
        List<Student> students=jdbcTemplate.query(sql,rowMapper);
        for(Student student:students){
            System.out.println(student);
        }
    }
}