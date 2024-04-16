package com.xcs.springmvc.test;

import com.xcs.springmvc.mapper.EmployeeMapper;
import com.xcs.springmvc.pojo.Employee;
import com.xcs.springmvc.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SpringMvcRestFulTest {
    @Test
    public void testSelectMethod(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.selectAll();
        employees.forEach(System.out::println);
        sqlSession.close();
    }
}
