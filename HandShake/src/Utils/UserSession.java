/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import Services.ServiceUser;
import java.sql.SQLException;

/**
 *
 * @author steph
 */

public final class UserSession {

    private static UserSession instance;

    private String email;
    private int id;
    private String role;
    private static User U= new User(22,"Malek","1234","malek.taktak@esprit.tn","admin");
    private static ServiceUser us= new ServiceUser();
    
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

    public static UserSession getInstance(String email, int id,String role) throws SQLException {
        if(instance == null) {
            instance = new UserSession(email, id,role);
            U=us.getUser(id);
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
        U=null;
    }

    public static User getU() {
        return U;
    }

    public static void setU(User U) {
        UserSession.U = U;
    }
    
    @Override
    public String toString() {
        return U.toString();
    }
}