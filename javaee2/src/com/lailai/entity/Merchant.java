package com.lailai.entity;
// default package



/**
 * Merchant entity. @author MyEclipse Persistence Tools
 */

public class Merchant  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String password;
     private Integer state;


    // Constructors

    /** default constructor */
    public Merchant() {
    }

    
    /** full constructor */
    public Merchant(String id, String name, String password, Integer state) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}