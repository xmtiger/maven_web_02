/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.form.web;

import com.mikex.form.model.User;
import com.mikex.form.service.UserService;
import com.mikex.form.validator.UserFormValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author xmtig
 */
@Controller
//@RequestMapping(value="form")
public class UserController {
    
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserFormValidator userFormValidator;
    
    private UserService userService;
    
    /*@Bean
    public UserFormValidator getUserFormValidator(){
        UserFormValidator curUserFormValidator = new UserFormValidator();
        return curUserFormValidator;
    }*/
    
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(userFormValidator);
    }    
    
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model){
        logger.debug("index()");
        return "redirect:/users";
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model){
        
        logger.debug("showAllUsers()");
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("userForm") @Validated User user, 
            BindingResult result, Model model, final RedirectAttributes redirectAttributes){
        
        logger.debug("saveOrUpdateUser(): {}", user);
        
        if(result.hasErrors()){
            populateDefaultModel(model);
            return "users/userform";
        }else{
            
            redirectAttributes.addAttribute("css", "success");
            if(user.isNew()){
                redirectAttributes.addFlashAttribute("msg", "User added successfully");
            }else{
                redirectAttributes.addFlashAttribute("msg", "User updated successfully");
            }
            
            userService.saveOrUpdate(user);
            
            return "redirect:/users/" +  user.getId();
        }
    }
    
    @RequestMapping(value="/users/add", method=RequestMethod.GET)
    public String showAddUserForm(Model model){
        
        logger.debug("showAdduserForm()");
        
        User user = new User();
        
        user.setName("yamax01");
        user.setEmail("yamax@gmail.com");
        user.setAddress("abc");
        
        user.setNewsletter(true);
        user.setSex("M");
        user.setFramework(new ArrayList<String>(Arrays.asList("Spring MVC", "Struts 2")));
        user.setSkill(new ArrayList<String>(Arrays.asList("Spring", "Struts")));
        user.setCountry("CA");
        user.setName("1");
        
        model.addAttribute("userForm", user);
        
        populateDefaultModel(model);
        
        return "users/userform";
    }
    
    @RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int id, Model model){
        
        logger.debug("showUpdateUserForm() : {}", id);
        
        User user = userService.findById(id);
        model.addAttribute("userForm", user);
        
        populateDefaultModel(model);
        
        return "users/userform";
    }
    
    //delete user
    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes){
        
        logger.debug("deleteUser(): {}", id);
        
        userService.delete(id);
        
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted");
        
        return "redirect:/users";
    }
    
    //show user
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model){
        
        logger.debug("showUser() id: {}", id);
        
        User user = userService.findById(id);
        if(user == null){
            logger.debug("user was not found");
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        else{
            logger.debug(user.toString());
        }
        model.addAttribute("user", user);
        
        return "users/show";
    }
    
    private void populateDefaultModel(Model model) {
        
        List<String> frameworksList = new ArrayList<>();
        frameworksList.add("Spring MVC");
        frameworksList.add("Struts 2");
        frameworksList.add("JSF 2");
        
        model.addAttribute("frameworkList", frameworksList);
        
        Map<String, String> skill = new LinkedHashMap();
        skill.put("Hibernate", "Hibernate");
        skill.put("Spring", "Spring");
        skill.put("Struts", "Struts");        
        model.addAttribute("javaSkillList", skill);
        
        List<Integer> numbers = new ArrayList();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        model.addAttribute("numberList", numbers);
        
        Map<String, String> country = new LinkedHashMap();
        country.put("US", "United States");
        country.put("CA", "Canada");
        country.put("CN", "China");
        model.addAttribute("countryList", country);
    }
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex){
        
        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);
        
        ModelAndView model = new ModelAndView();
        model.setViewName("user/show");
        model.addObject("msg", "user not found");
        
        return model;
    }
}
