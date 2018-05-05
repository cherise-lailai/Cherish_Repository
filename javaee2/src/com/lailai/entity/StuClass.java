package com.lailai.entity;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * StuClass entity. @author MyEclipse Persistence Tools
 */

public class StuClass  implements java.io.Serializable {


    // Fields    

     private String id;
     private String num;
     private Double price;
     private String year;
     private Integer state;
     private Set coursetimes = new HashSet(0);


    // Constructors

    /** default constructor */
    public StuClass() {
    }

	/** minimal constructor */
    public StuClass(String id, String num, Double price, String year, Integer state) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.year = year;
        this.state = state;
    }
    
    /** full constructor */
    public StuClass(String id, String num, Double price, String year, Integer state, Set coursetimes) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.year = year;
        this.state = state;
        this.coursetimes = coursetimes;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return this.num;
    }
    
    public void setNum(String num) {
        this.num = num;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }

    public Set getCoursetimes() {
        return this.coursetimes;
    }
    
    public void setCoursetimes(Set coursetimes) {
        this.coursetimes = coursetimes;
    }
   








}