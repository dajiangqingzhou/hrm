package org.deepsl.hrm.service;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.EmployeeCondition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void addEmployee(Employee employee);

    List<Employee> findEmployeesByCondition(Map<String,Object> map);

    int countEmployeesByCondition(Map<String,Object> map);

    Employee findEmployeeById(int id);

    void updateEmployee(Employee employee);
}
