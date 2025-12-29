package main.model;

import org.mindrot.jbcrypt.BCrypt;

public class Login {

    public static String hashPassword(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        return hashed;
    }

    public static boolean checkPassword(String password, String hashed){
        boolean matched = BCrypt.checkpw(password, hashed);
        
        return matched;
    }
}

