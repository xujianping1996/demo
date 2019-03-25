package com.jianping.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component//将该类注入ioc容器
@ConfigurationProperties(prefix="student")//设定配置前缀
public class Student {
	private String name;
	private int age;
	private boolean sex;
	private Date birthday;
	private Map<String , Object> location;
	private String [] hobbies;
	private List<String > skills;
	private Pet pet;
	public Student() {
		super();
	}
	public Student(String name, int age, boolean sex, Date birthday, Map<String, Object> location, String[] hobbies,
			List<String> skills, Pet pet) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
		this.location = location;
		this.hobbies = hobbies;
		this.skills = skills;
		this.pet = pet;
	}
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
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Map<String, Object> getLocation() {
		return location;
	}
	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
}
