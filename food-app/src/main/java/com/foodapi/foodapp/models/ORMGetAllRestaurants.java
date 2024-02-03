package com.foodapi.foodapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ORMGetAllRestaurants {
    @Id
    private Long restaurant_id;
    private String restaurant_name;
    private String phone;
    private String type;
	public Long getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    

}
