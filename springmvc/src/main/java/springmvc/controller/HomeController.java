package springmvc.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("first")
public class HomeController {

	@RequestMapping({"/", "/home"})
	public String home(Model model) {
		System.out.println("This is home URL");
		model.addAttribute("name", "Roshan Chauhan");
		model.addAttribute("id", 1621);
		
		//Sending List
		List<String> friends=new ArrayList<String>();
			
		friends.add("Roshan");
		friends.add("Abc");
		friends.add("Def");
		friends.add("Bsnl");
		friends.add("S_Vankar");
		
		
		model.addAttribute("f",friends);
		
		
		return "index";
	}
	
	@RequestMapping("/about")
	public String about() {
		System.out.println("This is about Mapping");
		return "about";
	}


//make services
//help , you can add here	
//@RequestMapping(path ="/help" ,method=RequestMethod.GET) //Default here is GET 
@RequestMapping("/help")	
public ModelAndView help() {
	System.out.println("This is help Controller");
	
	//Creating Model And View Object
	ModelAndView modelandview=new ModelAndView();
	//setting the data
	modelandview.addObject("name", "Ghi");
	modelandview.addObject("rollnumber", 121621);
	LocalDateTime now = LocalDateTime.now();
	modelandview.addObject("time", now);
	
	//Marks
	List<Integer> list=new ArrayList<Integer>();
	
	list.add(12);
	list.add(15);
	list.add(34);
	list.add(3421);
	list.add(12221);
	list.add(5123);
	
	modelandview.addObject("marks",list);
	//setting the view name
	modelandview.setViewName("help");
	
	
	return modelandview;
}
}