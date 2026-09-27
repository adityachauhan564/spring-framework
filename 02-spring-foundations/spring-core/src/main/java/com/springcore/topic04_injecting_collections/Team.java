package com.springcore.topic04_injecting_collections;

import java.util.List;
import java.util.Map;

/* Receives collections that are defined once as standalone <util:*> beans and shared by reference. */
public class Team {

    private List<String> members;
    private Map<String, Integer> fees;

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setFees(Map<String, Integer> fees) {
        this.fees = fees;
    }

    public List<String> getMembers() {
        return members;
    }

    public Map<String, Integer> getFees() {
        return fees;
    }
}
