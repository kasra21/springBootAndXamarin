package com.boot.demo.model;

public class User {

    String username;
    String email;
    String first;
    String last;

    public User(String username, String email, String first, String last) {
        this.username = username;
        this.email = email;
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
    
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
    
}
