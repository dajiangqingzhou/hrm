package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.UserDao;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.UserService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User login(String loginname, String password) {
        HashMap<String,String> map = new HashMap<>();
        map.put("loginname",loginname);
        map.put("password",password);
        return userDao.selectByLoginnameAndPassword(map);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("pageModel",pageModel);
        Integer count = userDao.count(map);
        if (count == 0) {
            return null;
        }
        pageModel.setRecordCount(count);
        return userDao.selectByPage(map);
    }

    @Override
    public void removeUserByIds(List<Integer> idsList) {
        Map<String,List<Integer>> map = new HashMap<>();
        map.put("idsList",idsList);
        userDao.deleteByIds(map);
    }

    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }

    @Override
    public void addUser(User user) {

    }
}
