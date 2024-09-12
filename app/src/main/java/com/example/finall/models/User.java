package com.example.finall.models;

public class User {
    private Long id;
    private String name;
    private String mail;
    private String password;
    private int isAdmin;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getIsAdmin() {return isAdmin;}
    public void setIsAdmin(int isAdmin) {this.isAdmin=isAdmin;}
}
