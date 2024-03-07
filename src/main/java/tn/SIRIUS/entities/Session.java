package tn.SIRIUS.entities;

import tn.SIRIUS.utils.GoogleSignInManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public   class Session {
    static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
    public static void logout(){
        user = null;
        Path directoryPath = Paths.get(System.getProperty("user.home"), ".store/oauth2_sample");

        try {
            GoogleSignInManager.deleteFilesInsideDirectory(directoryPath);
            System.out.println("Files inside directory deleted successfully.");
        } catch (IOException e) {
            System.err.println("Error deleting files inside directory: " + e.getMessage());
        }
    }

}
