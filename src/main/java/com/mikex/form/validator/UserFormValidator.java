/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.form.validator;

import com.mikex.form.model.User;
import com.mikex.form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author xmtig
 */
@Component
public class UserFormValidator implements Validator{
    
    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;
    
    @Autowired
    UserService userService;
    
    @Override
    public boolean supports(Class<?> cla){
        return User.class.equals(cla);
    }
    
    @Override
    public void validate(Object target, Errors errors){
        
        User user = (User) target;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userform.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userform.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.userform.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userform.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userform.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userform.sex");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userform.country");
        
        if(!emailValidator.valid(user.getEmail())){
            errors.rejectValue("email", "Pattern.userForm.email");
        }
        
        if(user.getNumber()==null || user.getNumber() <=0){
            errors.rejectValue("number", "NotEmpty.userForm.number");
        }
        
        if(user.getCountry().equalsIgnoreCase("none")){
            errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
        }
        
        if(user.getFramework() == null || user.getFramework().size() <2){
            errors.rejectValue("framework", "Valid.userForm.framework");
        }
        
        if(user.getSkill() == null || user.getSkill().size() < 3){
            errors.rejectValue("skill", "Valid.userForm.skill");
        }
    }
}
