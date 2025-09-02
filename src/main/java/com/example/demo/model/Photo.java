package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "photos")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@NotEmpty(message = "this field is required!")
	@Length(min = 2, message = "The url have a min length of 2 char")
    private String url;

    public Photo(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public Photo() {
    }
    
    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
