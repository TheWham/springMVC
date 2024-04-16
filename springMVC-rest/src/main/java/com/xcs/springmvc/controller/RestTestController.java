package com.xcs.springmvc.controller;

import com.xcs.springmvc.mapper.EmployeeMapper;
import com.xcs.springmvc.pojo.Employee;
import com.xcs.springmvc.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RestTestController {
    private static SqlSession sqlSession = SqlSessionUtil.openSession();
    private static EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    @RequestMapping(value = "/")
    public ModelAndView restTest(ModelAndView modelAndView)
    {
        List<Employee> employeeList = employeeMapper.selectAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") String id){
        int i = employeeMapper.deleteByPrimaryKey(Integer.parseInt(id));
        sqlSession.commit();
        System.out.println(i);
        return "redirect:/";
    }
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public ModelAndView acceptObject(@PathVariable("id") String id, ModelAndView modelAndView){
        Employee employee = employeeMapper.selectByPrimaryKey(Integer.parseInt(id));
        modelAndView.addObject("employee", employee);
        System.out.println(employee);
        modelAndView.setViewName("employee_update");
        return modelAndView;
    }
    @RequestMapping(value = "/testRest", method = RequestMethod.PUT)
    public String update(Employee employee){
        System.out.println(employee);
        int i = employeeMapper.updateByPrimaryKey(employee);
        System.out.println(i);
        sqlSession.commit();
        return "redirect:/";
    }
    @RequestMapping(value = "/testRest/add", method = RequestMethod.GET)
    public String toAddForm(){
        return "employee_add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Employee employee){
        int insert = employeeMapper.insert(employee);
        sqlSession.commit();
        System.out.println(insert);
        return "redirect:/";
    }
}
