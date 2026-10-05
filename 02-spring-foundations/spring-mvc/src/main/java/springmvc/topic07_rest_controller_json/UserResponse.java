package springmvc.topic07_rest_controller_json;

import springmvc.topic04_service_and_dao_layers.User;

/*
 * Topic    : A JSON API with @RestController
 * Read     : UserResponse -> CreateUserRequest -> UserRestController -> RestExceptionHandler
 * What the API SENDS back. Returning the User entity directly would leak passwordHash.
 * A response record lists exactly the fields that clients are allowed to see.
 * Like a bank statement: it shows your balance, not the bank's internal notes.
 */
public record UserResponse(int id, String userName, String email) {

    static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUserName(), user.getEmail());
    }
}
