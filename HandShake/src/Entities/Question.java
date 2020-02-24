/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ghost
 */
public class Question {
    private int questionId;
    private String texteQuestion;
    private Date dateQuestion;
    private User user;
    private int score;
    public Question() {
    }
    
    public Question(int questionId, String texteQuestion, Date dateQuestion, User user) {
        this.questionId = questionId;
        this.texteQuestion = texteQuestion;
        this.dateQuestion = dateQuestion;
        this.user = user;
    }

    public Question(int questionId, String texteQuestion, Date dateQuestion, int score, User user) {
        this.questionId = questionId;
        this.texteQuestion = texteQuestion;
        this.dateQuestion = dateQuestion;
        this.user = user;
        this.score=score;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public Date getDateQuestion() {
        return dateQuestion;
    }

    public void setDateQuestion(Date dateQuestion) {
        this.dateQuestion = dateQuestion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
    @Override
    public String toString() {
        return "Question posée par " + user.getLogin() + ": '" + texteQuestion + "' Créee le=" + dateQuestion ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.questionId;
        hash = 59 * hash + Objects.hashCode(this.texteQuestion);
        hash = 59 * hash + Objects.hashCode(this.dateQuestion);
        hash = 59 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        if (this.questionId != other.questionId) {
            return false;
        }
        if (!Objects.equals(this.texteQuestion, other.texteQuestion)) {
            return false;
        }
        if (!Objects.equals(this.dateQuestion, other.dateQuestion)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

  
    
}
