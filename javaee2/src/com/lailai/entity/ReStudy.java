package com.lailai.entity;
// default package



/**
 * ReStudy entity. @author MyEclipse Persistence Tools
 */

public class ReStudy  implements java.io.Serializable {


    // Fields    

     private String id;
     private String userName;
     private String restucourseid;
     private Integer num;
     private Integer state;


    // Constructors

    /** default constructor */
    public ReStudy() {
    }

    
    /** full constructor */
    public ReStudy(String id, String userName, String restucourseid, Integer num, Integer state) {
        this.id = id;
        this.userName = userName;
        this.restucourseid = restucourseid;
        this.num = num;
        this.state = state;
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

    public String getRestucourseid() {
        return this.restucourseid;
    }
    
    public void setRestucourseid(String restucourseid) {
        this.restucourseid = restucourseid;
    }

    public Integer getNum() {
        return this.num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}