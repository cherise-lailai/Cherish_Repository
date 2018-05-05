package com.lailai.entity;
// default package



/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private Integer state;


    // Constructors

    /** default constructor */
    public Course() {
    }

    
    /** full constructor */
    public Course(String id, String name, Integer state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}