/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_test;

import com.mikex.form.model.User;
import com.mikex.form.service.UserService;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 *
 * @author MikeX
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class FormTests {
    
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Test
    public void test_db_connection() throws Exception{
        //this.mockMvc.perform(get("/")).andExpect(status().isOk());
        
        UserService userService = wac.getBean(UserService.class);
        List<User> users = userService.findAll();
        Iterator<User> iter_users = users.iterator();
        while(iter_users.hasNext()){
            User user = iter_users.next();
            String name = user.getName();
            System.out.println(name);
        }
        
        assertEquals(3, users.size());
        
        
    }
}
