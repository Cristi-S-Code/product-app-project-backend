package com.project.product.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.project.product.validators.PasswordConstraint;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id")
	private Long userId;
	
	@Email(message = "Invalid email format!")
	@NotBlank(message = "Email cannot be blank!")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "Username cannot be blank!")
	@Column(name = "username", nullable = false, unique = true)
	@Size(min = 5, max = 50)
	private String username;
	
//	@PasswordConstraint
	@NotBlank
	@Column(name = "password", nullable = false)
//	@Size(min = 8, max = 20)
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(required = true, example ="2021-08-20@12:02:03")
	@NotNull(message = "Creation date cannot be null!")
//	@NotBlank(message = "Creation date cannot be blank!")
	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", creationDate=" + creationDate + "]";
	}
}
