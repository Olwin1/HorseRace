package Utils;

import Primary.User;
import java.util.ArrayList;


/**
 * Basic singleton to keep track of User instances. This will ensure the number
 * of users is maintained throughout the program.
 * 
 */
public class UserInstances {
    private static UserInstances _instance;

    private ArrayList<User> users;

    /// Get the stored instance of the [UserInstances] class.  If there is no existing one then a new instance is created.  
    public static UserInstances getInstance() {
        if (_instance == null) {
            _instance = new UserInstances();
        }
        return _instance;
    }

    /// Accessor method for the [ArrayList] of type [User]
    public ArrayList<User> getUsers() {
        return this.users;
    }

    // Instantiate user list in constructor.
    UserInstances() {
        users = new ArrayList<>();
    }

    public static void main(String[] args) {
        UserInstances.getInstance();
    }

    /**
     * Adds a user to the betting pool tracking
     * 
     * @param newUser the user to be added
     */
    public void addUser(User newUser) {

        // Add the new user to the ArrayList
        users.add(newUser);
    }
}
