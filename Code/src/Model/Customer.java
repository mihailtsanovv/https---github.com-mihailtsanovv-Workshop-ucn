package Model;

public class Customer {
	private int id;
	private String name;
	private String address;
	private int zipCode;
	private String city;
	private String phone;
	
	public Customer(){
		
	}

	public Customer(int id, String name, String address, int zipCode, String city, String phone){
	this.id = id;
	this.name = name;
	this.address = address;
	this.zipCode = zipCode;
	this.city = city;
	this.phone = phone;
	}

	public int getId(){
	    return id;
	}

	public void setId(int id){
	    this.id = id;
	}

	public String getName(){
	    return name;
	}

	public void setName(String name){
	    this.name = name;
	}

	public String getAddress(){
	    return address;
	}

	public void setAddress(String address){
	    this.address = address;
	}

	public int getZipCode(){
	    return zipCode;
	}

	public void setZipCode(int zipCode){
	    this.zipCode = zipCode;
	}

	public String getCity(){
	    return city;
	}

	public void setCity(String city){
	    this.city = city;
	}

	public String getPhone(){
	    return phone;
	}

	public void setPhone(String phone){
	    this.phone = phone;
	}
}
