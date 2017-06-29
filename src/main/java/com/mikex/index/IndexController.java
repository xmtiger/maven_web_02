/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author MikeX
 */
@Controller
@RequestMapping("index")
public class IndexController {
    
    @RequestMapping("")
    public String indexJsp(){
        
        return "index";
    }
}
