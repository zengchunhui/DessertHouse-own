package dessert.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import dessert.pvo.InfoPVO;

@Entity(name = "memberinfo")
public class Memberinfo {
	@Id
	private int id;
	private String compellation;
	private Date birthday;
	private int gender;
	private String address;
	private String phone;

	
	public void setFromInfoPVO(InfoPVO po){
		setId(Integer.parseInt(po.getId()));
		setCompellation(po.getCompellation());
		setBirthday(po.getBirthday());
		setGender(po.getGender());
		setAddress(po.getAddress());
		setPhone(po.getPhone());
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompellation() {
		return compellation;
	}

	public void setCompellation(String compellation) {
		this.compellation = compellation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
