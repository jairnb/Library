package com.example.library;

public class UserSingleton {
    private static UserSingleton instance;
    private static String user_id;
    private static String role;
    private UserSingleton(String id,String r){
        user_id =id;
        role = r;
    }

    public static UserSingleton createInstance(String user_id,String role){
        if(instance == null)
            instance = new UserSingleton(user_id, role);

        return instance;
    }

    public static UserSingleton getInstance(){
        return  instance;
    }

    public static String getUserRole(){
        return role;
    }

    public static String getUserId(){
        return user_id;
    }
}
