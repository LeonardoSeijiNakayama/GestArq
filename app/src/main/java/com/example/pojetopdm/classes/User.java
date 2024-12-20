package com.example.pojetopdm.classes;

import java.util.ArrayList;

public class User {
    private String name, email;
    private ArrayList<Project> projects;
    private ArrayList<Client> clients;
    private ArrayList<Material> materials;

    public User(String name, String email, ArrayList<Project> projects, ArrayList<Client> clients, ArrayList<Material> materials) {
        this.name = name;
        this.email = email;
        this.projects = projects;
        this.clients = clients;
        this.materials = materials;
    }

    public void addProject(Project project) {
        projects.add(project);

    }

    public void addClient(Client client){
        clients.add(client);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }
}
