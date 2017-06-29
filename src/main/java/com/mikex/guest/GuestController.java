package com.mikex.guest;
 
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping(value="/guest")
public class GuestController {
 
    @Autowired
    private GuestDao guestDao;
 
    @RequestMapping(value="")
    public @ResponseBody ModelAndView guestbook(HttpServletRequest request) {
        // Handle a new guest (if any):
        String name = request.getParameter("name");
        if (name != null)
            guestDao.persist(new Guest(name));
 
        // Prepare the result view (guest.jsp):
        return new ModelAndView("guest", "guestDao", guestDao);
    }
    
    @RequestMapping(value="/one")
    public String test01() {
               
        return "test01";
    }
    
    
}