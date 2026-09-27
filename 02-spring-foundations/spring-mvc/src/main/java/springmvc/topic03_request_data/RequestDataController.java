package springmvc.topic03_request_data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Topic    : Reading data from the request
 * Key idea : Spring converts request text into typed method parameters for you:
 *   @RequestParam  - query string:  /greet?name=Asha
 *   @PathVariable  - part of the path: /students/7  (identifies ONE resource)
 *   @ModelAttribute - many parameters into one object: /search?city=Pune&minAge=18
 *   The old way was request.getParameter("name") on an HttpServletRequest, as a String.
 * Try this : open /springmvc/students/abc - why does Spring answer 400 Bad Request?
 */
@Controller
public class RequestDataController {

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "guest") String name, Model model) {
        return show(model, "@RequestParam name", "Hello, " + name + "!");
    }

    @GetMapping("/students/{id}")
    public String student(@PathVariable int id, Model model) {
        return show(model, "@PathVariable id", "student number " + id + " (already converted to an int)");
    }

    @GetMapping("/search")
    public String search(@ModelAttribute SearchQuery query, Model model) {
        return show(model, "@ModelAttribute SearchQuery", query.toString());
    }

    private static String show(Model model, String source, String value) {
        model.addAttribute("source", source);
        model.addAttribute("value", value);
        return "request-data";
    }
}
