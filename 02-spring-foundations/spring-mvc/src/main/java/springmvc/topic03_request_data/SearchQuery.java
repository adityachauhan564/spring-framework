package springmvc.topic03_request_data;

/*
 * @ModelAttribute packs several request parameters into ONE object:
 * ?city=Pune&minAge=18 -> new SearchQuery("Pune", 18). Any missing ones become null.
 */
public record SearchQuery(String city, Integer minAge) {
}
