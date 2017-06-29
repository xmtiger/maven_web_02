/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.form.service;

import com.mikex.form.model.User;
import java.util.List;

/**
 *
 * @author xmtig
 */
public interface UserService {
    
    User findById(Integer id);
    
    List<User> findAll();
    
    void saveOrUpdate(User user);
    
    void delete(int id);
}
