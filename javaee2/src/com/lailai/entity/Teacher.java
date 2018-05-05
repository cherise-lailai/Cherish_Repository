package com.lailai.entity;
// default package



/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String password;
     private String imgPath;
     private String email;
     private String goodAt;
     private Integer score;
     private Integer state;


    // Constructors

    /** default constructor */
    public Teacher() {
    }

	/** minimal constructor */
    public Teacher(String id, String name, String password, String email, String goodAt, Integer score, Integer state) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.goodAt = goodAt;
        this.score = score;
        this.state = state;
    }
    
    /** full constructor */
    public Teacher(String id, String name, String password, String imgPath, String email, String goodAt, Integer score, Integer state) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.imgPath = imgPath;
        this.email = email;
        this.goodAt = goodAt;
        this.score = score;
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

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgPath() {
        return this.imgPath;
    }
    
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoodAt() {
        return this.goodAt;
    }
    
    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}