package org.nil.beanValidation.core;

import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
	@FormParam("name")
	@NotEmpty(message = "name cannot be empty")
	private String name;

	@FormParam("age")
	@Min(value = 18, message = "age below 18 is not allowed")
	@Max(value = 135, message = "age above 135 is not possible")
	private int age;
	
	@FormParam("height")
	@DecimalMin(value = "1", message = "height less than 1 ft not allowed")
	@DecimalMax(value = "15", message = "height more than 15 ft not allowed")
	private float height;

	@FormParam("address")
	@Length(max = 200)
	private String address;

	@FormParam("hobbies")
	@Size(max = 3, message = "more than 3 hobbies cannot be entered")
	private List<String> hobbies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height=" + height + ", address=" + address + ", hobbies="
				+ hobbies + "]";
	}
}
