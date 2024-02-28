package tn.SIRIUS.entities;

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
    }
}
