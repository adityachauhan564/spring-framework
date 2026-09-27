package springmvc.topic03_request_data;

/*
 * Several request parameters bound into ONE object by @ModelAttribute:
 * ?city=Pune&minAge=18 -> new SearchQuery("Pune", 18). Missing ones become null.
 */
public record SearchQuery(String city, Integer minAge) {
}
