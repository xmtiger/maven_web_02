/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.form.dao;

import com.mikex.form.model.User;
import java.util.List;

/**
 *
 * @author xmtig
 */
public interface UserDao {
    User findById(Integer id);
    
    List<User> findAll();
    
    void save(User user);
    
    void update(User user);
    
    void delete(Integer id);
}
