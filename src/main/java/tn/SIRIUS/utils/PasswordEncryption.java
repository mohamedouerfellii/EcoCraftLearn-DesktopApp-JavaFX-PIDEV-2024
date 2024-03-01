package tn.SIRIUS.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncryption {
    public static String hashPassword(String password){
        StringBuilder hexString=new StringBuilder();
        try {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] hash=md.digest(password.getBytes());

            for(byte b:hash){
                hexString.append(String.format("%02x",b));
            }
        }catch (NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }
        return hexString.toString();
    }
    public static void main(String[] args) {
        String password = "password";
        String hashedPassword = hashPassword(password);
        System.out.println(hashedPassword);
        hashedPassword = hashPassword(hashedPassword);
        System.out.println(hashedPassword);
    }
}
