package com.lailai.entity;
// default package



/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String password;
     private String imgPath;
     private String email;
     private Integer jh;
     private Integer state;
     private String classID;


    // Constructors

    /** default constructor */
    public User() {
    	
    }

    



	public User(String id, String name, String password, String imgPath, String email, Integer jh, Integer state,
			String classID) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.imgPath = imgPath;
		this.email = email;
		this.jh = jh;
		this.state = state;
		this.classID = classID;
	}





	public String getClassID() {
		return classID;
	}


	public void setClassID(String classID) {
		this.classID = classID;
	}


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

    public Integer getJh() {
        return this.jh;
    }
    
    public void setJh(Integer jh) {
        this.jh = jh;
    }

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }





	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", imgPath=" + imgPath + ", email="
				+ email + ", jh=" + jh + ", state=" + state + ", classID=" + classID + "]";
	}
   








}