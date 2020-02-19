/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author steph
 */

public final class UserSession {

    private static UserSession instance;

    private String email;
    private int id;
    private String role;
    
    private UserSession()
    {
        email = getEmail();
        id = getId();
        role = getRole();
    }

    private UserSession(String email, int id,String role) {
        this.email = email;
        this.id = id;
        this.role = role;
    }
    
    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public static UserSession getInstace(String email, int id,String role) {
        if(instance == null) {
            instance = new UserSession(email, id,role);
        }
        return instance;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void cleanUserSession() {
        email = "";// or null
        id = -1;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "Email ='" + email + '\'' +
                ", Id =" + id +
                '}';
    }
}