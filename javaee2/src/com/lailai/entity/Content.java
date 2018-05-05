package com.lailai.entity;
// default package



/**
 * Content entity. @author MyEclipse Persistence Tools
 */

public class Content  implements java.io.Serializable {


    // Fields    

     private String id;
     private String title;
     private String content;
     private Integer flag;


    // Constructors

    /** default constructor */
    public Content() {
    }

    
    /** full constructor */
    public Content(String id, String title, String content, Integer flag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.flag = flag;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
   








}