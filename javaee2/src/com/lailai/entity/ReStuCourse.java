package com.lailai.entity;
// default package

import java.sql.Timestamp;


/**
 * ReStuCourse entity. @author MyEclipse Persistence Tools
 */

public class ReStuCourse  implements java.io.Serializable {


    // Fields    

     private String id;
     private String courseName;
     private String teacher;
     private String studyTime;
     private String period;
     private Integer state;


    // Constructors

    /** default constructor */
    public ReStuCourse() {
    }

	/** minimal constructor */
    public ReStuCourse(String id, String courseName, String teacher, String period, Integer state) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.period = period;
        this.state = state;
    }
    
    /** full constructor */
    public ReStuCourse(String id, String courseName, String teacher, String studyTime, String period, Integer state) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.studyTime = studyTime;
        this.period = period;
        this.state = state;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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

    public String getStudyTime() {
        return this.studyTime;
    }
    
    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getPeriod() {
        return this.period;
    }
    
    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}