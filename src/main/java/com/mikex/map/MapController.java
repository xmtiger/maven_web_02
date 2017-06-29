/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author xmtig
 */
@Controller
@RequestMapping(value="map")
public class MapController {
    
    @RequestMapping(value="")
    public String map01Controller(){
        
        return "/Maps/map";
    }
}
