package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "helpproposition")
public class HelpProposition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String branch;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date dateBegin;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private Date timeBegin;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private Date timeEnd;
    
    @Column
    private String comment;
    
    @ManyToOne
    private User mentor;

    @ManyToOne
    private User poulain;

    public HelpProposition() {

    }

    public Integer getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public Date getDateBegin() {
        return dateBegin;
    }
    
    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getTimeBegin(){
        return timeBegin;
    }
    
    public Date getTimeEnd(){
        return timeEnd;
    }
    
    public String getComment() {
        return comment;
    }

    public User getMentor(){
        return mentor;
    }

    public User getPoulain(){
        return poulain;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setDateBegin(Date date) {
        this.dateBegin = date;
    }

    public void setDateEnd(Date date) {
        this.dateEnd = date;
    }

    public void setTimeBegin(Date date) {
        this.timeBegin = date;
    }

    public void setTimeEnd(Date date) {
        this.timeEnd = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }
    
    public void setPoulain(User poulain) {
        this.poulain = poulain;
    }
}
