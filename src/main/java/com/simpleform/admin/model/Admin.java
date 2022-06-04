package com.simpleform.admin.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="testing")


public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;
    Integer user_id;
    Integer user;
    double total_score;
    Date created;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }
}
