package com.jianping.demo.entity;

public class Pet {
	private String name ;
	private String strain;
	
	public Pet() {
		super();
	}
	public Pet(String name, String strain) {
		super();
		this.name = name;
		this.strain = strain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrain() {
		return strain;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	
}
