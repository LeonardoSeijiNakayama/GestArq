package com.example.pojetopdm.classes;

import java.util.ArrayList;

public class UserHandler {
    private static User user;

    public static void buildUser(String name, String email){
        if(user == null){
            ArrayList<Project> projects = new ArrayList<>();
            ArrayList<Client> clients = new ArrayList<>();
            ArrayList<Material> materials = new ArrayList<>();
            user = new User(name, email, projects, clients, materials);


        }
    }
    public static void buildUser(String name, String email, ArrayList<Project> projects,
                                 ArrayList<Client> clients, ArrayList<Material> materials){
        if(user == null){
            user = new User(name, email, projects, clients, materials);
        }
    }



    public static User getUser() {
        return user;
    }

}
