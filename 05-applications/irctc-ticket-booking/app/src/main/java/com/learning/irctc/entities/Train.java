package com.learning.irctc.entities;

import java.util.List;
import java.util.Map;

/*
 * A train as stored in trains.json. A record gives the constructor, getters, equals and toString,
 * and Jackson (2.12+) can read JSON straight into it.
 *
 *   seats         a grid, one list per coach row: 0 = free, 1 = booked
 *   stations      in the order the train visits them
 *   stationTimes  station -> departure time
 * Field names are camelCase here and snake_case in the file (train_no); JsonStore maps between them.
 */
public record Train(String trainId, String trainNo, List<List<Integer>> seats,
                    Map<String, String> stationTimes, List<String> stations) {

    public static final int FREE = 0;
    public static final int BOOKED = 1;

    /** True if the train stops at both stations, in that order: Delhi -> Jaipur is not Jaipur -> Delhi. */
    public boolean runsBetween(String source, String destination) {
        int from = indexOf(source);
        int to = indexOf(destination);
        return from >= 0 && to >= 0 && from < to;
    }

    public boolean isFree(int row, int seat) {
        return row >= 0 && row < seats.size()
                && seat >= 0 && seat < seats.get(row).size()
                && seats.get(row).get(seat) == FREE;
    }

    public long freeSeats() {
        return seats.stream().flatMap(List::stream).filter(s -> s == FREE).count();
    }

    private int indexOf(String station) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).equalsIgnoreCase(station)) return i;
        }
        return -1;
    }
}
