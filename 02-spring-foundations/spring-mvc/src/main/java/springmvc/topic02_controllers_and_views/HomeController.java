package springmvc.topic02_controllers_and_views;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Topic    : Controllers, Model and views
 * Key idea : a @Controller method handles one URL and returns the NAME of a view.
 *            Data for the page goes into the Model; the JSP reads it with ${...} (EL).
 *              Model        - you add data, and return the view name as a String
 *              ModelAndView - one object holding both the data and the view name
 *            @GetMapping is the short form of @RequestMapping(path = ..., method = GET).
 * Try this : open /springmvc/ , /springmvc/about and /springmvc/help, then add a /team page.
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("name", "Roshan Chauhan");
        model.addAttribute("id", 1621);
        model.addAttribute("friends", List.of("Roshan", "Asha", "Ravi", "Meera"));
        return "index";                                   // -> /WEB-INF/views/index.jsp
    }

    @GetMapping("/about")
    public String about() {
        return "about";                                   // a page with no data at all
    }

    @GetMapping("/help")
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView("help");
        modelAndView.addObject("name", "Ghi");
        modelAndView.addObject("rollNumber", 121621);
        modelAndView.addObject("time", LocalDateTime.now().withNano(0));
        modelAndView.addObject("marks", List.of(12, 15, 34, 88, 97));
        return modelAndView;
    }
}
