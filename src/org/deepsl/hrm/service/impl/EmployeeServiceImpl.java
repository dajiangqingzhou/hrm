package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.EmployeeDao;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.EmployeeCondition;
import org.deepsl.hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl() {
        System.out.println("----EmployeeServiceImpl" );
    }

    @Autowired
    EmployeeDao employeeDao;

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public List<Employee> findEmployeesByCondition(Map<String, Object> map) {
        List<Employee> employees = employeeDao.selectByPage(map);
        return employees;
    }


    @Override
    public int countEmployeesByCondition(Map<String,Object> map) {
        Integer count = employeeDao.count(map);
        return count;
    }

    @Override
    public Employee findEmployeeById(int id) {
        Employee employee = employeeDao.selectById(id);
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }
}
