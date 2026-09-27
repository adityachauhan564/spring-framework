package com.learning.irctc.services;

import java.util.List;
import java.util.Optional;

import com.learning.irctc.entities.Train;
import com.learning.irctc.store.JsonStore;

/** Finding trains. Reads fresh from the store every time, so it always sees the latest bookings. */
public class TrainService {

    private final JsonStore store;

    public TrainService(JsonStore store) {
        this.store = store;
    }

    public List<Train> search(String source, String destination) {
        return store.loadTrains().stream()
                .filter(train -> train.runsBetween(source, destination))
                .toList();
    }

    public Optional<Train> findById(String trainId) {
        return store.loadTrains().stream().filter(train -> train.trainId().equalsIgnoreCase(trainId)).findFirst();
    }
}
