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
    private String roles;
    private String username;
    private static User U;
    private static ServiceUser us= new ServiceUser();
    
    private UserSession()
    {
        email = getEmail();
        id = getId();
        roles = getroles();
        username = getusername();
    }

    private UserSession(String email, int id,String roles,String username) {
        this.email = email;
        this.id = id;
        this.roles = roles;
        this.username = username;
    }
    
    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public static UserSession getInstace(String email, int id,String roles,String username) throws SQLException {
        if(instance == null) {
            instance = new UserSession(email, id,roles,username);
        }
        return instance;
    }
    public static UserSession getInstance(String email, int id,String password,String roles,String username) throws SQLException {
        if(instance == null) {
            instance = new UserSession(email, id,roles,username);
            U=new User(id,username,password,email,roles,us.getUser(id).isAccesShakeHub());
        }
        return instance;
    }

    public String getroles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getusername() {
        return username;
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
        UserSession.U = new User(U.getid(),U.getusername(),U.getPassword(),U.getEmail(),U.getroles());
    }
    
    @Override
    public String toString() {
        return U.toString();
    }
}
