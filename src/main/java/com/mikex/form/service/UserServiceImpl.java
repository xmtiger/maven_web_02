/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.form.service;

import com.mikex.form.dao.UserDao;
import com.mikex.form.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xmtig
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    
    UserDao userDao;
    
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    
    @Override
    public User findById(Integer id){
        return userDao.findById(id);
    }
    
    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }
    
    @Override
    public void saveOrUpdate(User user){
        if(findById(user.getId()) == null){
            userDao.save(user);
        }else{
            userDao.update(user);
        }
    }
    
    @Override
    public void delete(int id){
        userDao.delete(id);
    }
}
