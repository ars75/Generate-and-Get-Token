package org.gettoken;

import java.util.List;

public class Credentials {

    public String username;
    public String password;

    public Credentials (){}

    public Credentials (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void printRecords(List<Credentials> credentials) {
        for(Credentials credential : credentials) {
            System.out.println("======================================================");
            System.out.println("Username : " + credential.getUsername());
            System.out.println("Password : " + credential.getPassword());
        }
    }
}
