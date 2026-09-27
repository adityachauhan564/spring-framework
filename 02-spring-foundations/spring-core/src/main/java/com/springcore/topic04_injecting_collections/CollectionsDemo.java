package com.springcore.topic04_injecting_collections;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Injecting List, Set, Map and Properties
 * Key idea : <list>/<set>/<map>/<props> build a collection INSIDE one bean.
 *            <util:list>/<util:map> define a collection as its OWN bean, so several beans can
 *            share it - and you can choose the implementation class (list-class, map-class).
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic04_injecting_collections.CollectionsDemo
 * Try this : add a duplicate <value> to the <set> and see it disappear.
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

    private static String type(Object collection) {
        return collection.getClass().getSimpleName();
    }
}
