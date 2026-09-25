package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;

import springmvc.model.User;
import springmvc.service.UserService;

//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ContactController {
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/contact")
	public String showForm() {
		
		return "contact";
	}

    //@RequestMapping(path="/processform", method=RequestMethod.POST)
	/*public String handleForm(HttpServletRequest request) { //using Servlet Old tech 
		String email=request.getParameter("email");
		System.out.println("user email is:"+email);
    	return "";
		*/

@RequestMapping(path="/processform", method=RequestMethod.POST)
public String handleForm(@ModelAttribute User user, Model model){ //Using mvc We Reduces a lot of code
	
//	User user=new User();
	
	/*
	 * user.setEmail(userEmail); user.setUserName(userName);
	 * user.setPassword(userPassword);
	 */
   //System.out.println("user "+user); //it's for printing the output we can remove it also
	//process
	//model.addAttribute("user",user);
	this.userService.createUser(user);
	
	return "success";

}

/*public String handleForm(
        @RequestParam("email") String userEmail,
        @RequestParam("userName") String userName,
        @RequestParam("password") String userPassword, Model model){

System.out.println("User Email "+userEmail);
System.out.println("User Name "+userName);
System.out.println("User Password "+userPassword);

//process
model.addAttribute("name", userName);
model.addAttribute("email", userEmail);
model.addAttribute("password", userPassword);
return "success";*/

//}

}

