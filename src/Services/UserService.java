package Services;

import Classes.User;

import java.util.Vector;

public class UserService {
    //aici tinem userii aplicatiei din database in App

    protected Vector<User> useri;

    public UserService(Vector<User> useri) {
        this.useri = useri;
    }
}
