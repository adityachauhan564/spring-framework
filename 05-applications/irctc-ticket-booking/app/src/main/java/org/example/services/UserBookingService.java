package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {

    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper=new ObjectMapper();
    private static final String USERS_PATH= "src/main/resources/localDb/users.json"; // relative to app/ (the gradle run working dir)


    public UserBookingService(User user1) throws IOException {
        this.user=user1;
        File users=new File(USERS_PATH);
        userList=objectMapper.readValue(users, new TypeReference<List<User>>(){});

    }



}
