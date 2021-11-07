package com.ub.tag.TagManagement.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tag")

public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String userName;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "department", nullable = false)
	private String department;
	
	@NotNull(message = "area name is required.")
	@Size(min = 1, max = 30, message = "area name must be between 2 and 30 characters.")
	@Column(name = "area", nullable = false)
	private String area;
	
	@NotNull(message = "subArea name is required.")
	@Size(min = 1, max = 30, message = "subarea name must be between 2 and 30 characters.")
	@Column(name = "subArea", nullable = false)
	private String subArea;

	@NotNull(message = " description is required.")
	// @Size(min = 10, max = 1000, message = "Product description must be between 10
	// and 1000 characters.")
	@Column(name = "description", nullable = false)
	private String description;

	@Lob
	@Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	private String image;


	@Column(name = "create_date", nullable = false)
	private String createDate;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	
	public Tag() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
