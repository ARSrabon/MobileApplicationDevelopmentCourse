package com.example.msrabon.productinventory.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by msrabon on 11/16/16.
 */

@Table(name = "users")
public class User extends Model {

    @Column(name = "username" , unique = true)
    private String user_name;

    @Column(name = "email" , unique = true)
    private String user_email;

    @Column(name = "password")
    private String user_password;

    public User() {
    }

    /**
     * @param user_name
     * @param user_email
     * @param user_password
     */
    public User(String user_name, String user_email, String user_password) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return user_name + "    " + user_email ;
    }
}
