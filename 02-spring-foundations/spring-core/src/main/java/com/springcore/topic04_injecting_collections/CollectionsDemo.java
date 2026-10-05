package com.springcore.topic04_injecting_collections;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Injecting List, Set, Map and Properties
 * Key idea : Spring can fill List, Set, Map and Properties fields from XML. Two ways:
 *            - <list>/<set>/<map>/<props> build a collection INSIDE one bean.
 *              Only that bean can use it.
 *            - <util:list>/<util:map> make the collection its OWN bean, so many beans can share it.
 *              You can also pick the exact class (list-class, map-class), e.g. LinkedList or TreeMap.
 *            - Like a tiffin made for one person vs one big pot that the whole family shares.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic04_injecting_collections.CollectionsDemo
 * Try this : Add a duplicate <value> to the <set> and see it disappear.
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic04_injecting_collections/collections.xml")) {
            Employee employee = context.getBean("employee", Employee.class);
            System.out.println("Inline collections for " + employee.getName() + ":");
            System.out.println("  phones    " + employee.getPhones() + "  -> " + type(employee.getPhones()));
            System.out.println("  addresses " + employee.getAddresses() + "  -> " + type(employee.getAddresses()));
            System.out.println("  courses   " + employee.getCourses() + "  -> " + type(employee.getCourses()));
            System.out.println("  settings  " + employee.getSettings());

            Team team = context.getBean("team", Team.class);
            System.out.println("\nStandalone <util:*> collections, shared by reference:");
            System.out.println("  members   " + team.getMembers() + "  -> " + type(team.getMembers()));
            System.out.println("  fees      " + team.getFees() + "  -> " + type(team.getFees()) + " (sorted keys)");
        }
    }

    // prints the real class name Spring used, e.g. ArrayList or LinkedHashSet
    private static String type(Object collection) {
        return collection.getClass().getSimpleName();
    }
}
