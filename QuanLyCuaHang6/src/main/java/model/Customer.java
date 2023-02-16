package model;

import subclass.CustomDate;

public class Customer {
	private String id;
	private String user;
	private String name;
	private CustomDate dob;
	private String address;
	private byte[] image;
	private String phoneNumber;
	private String email;
	private Integer score;
	
	public Customer(String id, String user, String name, CustomDate dob,String address, 
					String phoneNumber, String email, byte[] img, Integer score) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.image = img;
		this.score = score;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CustomDate getDob() {
		return dob;
	}
	public void setDob(CustomDate dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
