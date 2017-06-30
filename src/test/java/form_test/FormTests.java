/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_test;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mikex.form.model.User;
import com.mikex.form.service.UserService;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author MikeX
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
public class FormTests {
    
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    private WebClient webClient;
    
    //private WebDriver driver;
    
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        
        this.webClient = MockMvcWebClientBuilder.webAppContextSetup(this.wac).build();
        
        //this.driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(wac).build();
    }
    
    //@Test
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
        
        MvcResult mvcResult = mockMvc.perform(get("/users")).andExpect(view().name("users/list")).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        Map<String, Object> map = modelAndView.getModel();
        List<User> obj = (List<User>) map.get("users");
        System.out.println(obj.size());
    }
    
    //@Test
    public void test_list_page() throws Exception{
        
        mockMvc.perform(get("/users"))
            .andExpect(view().name("users/list"))
            .andExpect(model().attributeExists("users"));
        
        /*if(webClient == null){
            System.out.println("webClient is null");
            return;
        }else{
            System.out.println("webClient is not null");
        }
        
        Page listPage = webClient.getPage("http://localhost/maven_web_02/users/list");
        
        if(listPage == null){
            System.out.println("can not get the html page");
            return;
        }
        
        if(listPage instanceof HtmlPage){
            System.out.println("get Access into http://localhost:8080/maven_web_02/index.htm");
        }*/
    }
    
    //@Test
    public void test_show_page() throws Exception{
        mockMvc.perform(get("/users/100"))
            .andExpect(view().name("users/show"))
            .andExpect(model().attributeExists("user"));
    }
    
    @Test
    public void test_update_action() throws Exception{  
        mockMvc.perform(get("/users/100/update"))
            .andExpect(view().name("users/userform"))
            .andExpect(model().attributeExists("userForm", "frameworkList", "numberList", "countryList", "javaSkillList"));
                
    }
    
    //@Test
    public void test_update_page() throws Exception{
        //WebClient wc = new WebClient();
        //HtmlPage page = (HtmlPage) wc.getPage("http://localhost:8084/maven_web_02/users/");
        HtmlPage page = (HtmlPage) webClient.getPage("http://localhost:8084/maven_web_02/users");
    }
}
