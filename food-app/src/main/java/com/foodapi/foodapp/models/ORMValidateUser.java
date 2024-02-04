package com.foodapi.foodapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ORMValidateUser {
	@Id
	private Long user_id;
	private String name;
	private String picture_link;
	private String is_restaurant;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture_link() {
		return picture_link;
	}
	public void setPicture_link(String picture_link) {
		this.picture_link = picture_link;
	}
	public String getIs_restaurant() {
		return is_restaurant;
	}
	public void setIs_restaurant(String is_restaurant) {
		this.is_restaurant = is_restaurant;
	}
	
}
