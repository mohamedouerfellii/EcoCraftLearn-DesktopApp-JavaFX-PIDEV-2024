package tn.SIRIUS.EcoCraftLearning;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.UserService;
import tn.SIRIUS.utils.GoogleSignInManager;

public class MainTest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User currentuser = userService.getUserByEmail("barkaoui885@gmail.com");
        System.out.println(currentuser);

    }
}