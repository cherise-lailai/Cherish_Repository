package com.lailai.entity;
// default package

import java.util.Date;


/**
 * Evaluation entity. @author MyEclipse Persistence Tools
 */

public class Evaluation  implements java.io.Serializable {


    // Fields    

     private String id;
     private String userName;
     private String courseName;
     private String teacher;
     private Date date;
     private String feedback;
     


    // Constructors

    public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	/** default constructor */
    public Evaluation() {
    }

    
    /** full constructor */
    public Evaluation(String id, String userName, String courseName, String teacher, Date date, String feedback) {
		super();
		this.id = id;
		this.userName = userName;
		this.courseName = courseName;
		this.teacher = teacher;
		this.date = date;
		this.feedback = feedback;
	}
   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}