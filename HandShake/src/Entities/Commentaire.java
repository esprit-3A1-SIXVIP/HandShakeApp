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
public class Commentaire {
    private User user;
    private Question question;
    private String texteCommentaire;
    private Date dateCommentaire;
    private int score;

    public Commentaire() {
    }

    public Commentaire(User user, Question question, String texteCommentaire, Date dateCommentaire) {
        this.user = user;
        this.question = question;
        this.texteCommentaire = texteCommentaire;
        this.dateCommentaire = dateCommentaire;
    }

    public Commentaire(User user, Question Q, String texteCommentaire, Date dateCommentaire, int score) {
        this.user = user;
        this.question = Q;
        this.texteCommentaire = texteCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.score=score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    

    
    public String getTexteCommentaire() {
        return texteCommentaire;
    }

    public void setTexteCommentaire(String texteCommentaire) {
        this.texteCommentaire = texteCommentaire;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "user=" + user + ", question=" + question + ", texteCommentaire=" + texteCommentaire + ", dateCommentaire=" + dateCommentaire + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.user);
        hash = 89 * hash + Objects.hashCode(this.question);
        hash = 89 * hash + Objects.hashCode(this.texteCommentaire);
        hash = 89 * hash + Objects.hashCode(this.dateCommentaire);
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
        final Commentaire other = (Commentaire) obj;
        if (!Objects.equals(this.texteCommentaire, other.texteCommentaire)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.dateCommentaire, other.dateCommentaire)) {
            return false;
        }
        return true;
    }

    

    
    
}

