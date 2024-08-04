package co.edu.uptc.concessionaire.model;

public class Maintenance {
	private String date;
	private String brand;
	private String plate;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Maintenance(String plate, String brand,String date) {
		super();
		this.date = date;
		this.brand = brand;
		this.plate = plate;
	}
	
	public Maintenance() {}
	@Override
	public String toString() {
		return "Maintenance [date=" + date + ", brand=" + brand + ", plate=" + plate + "]";
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
}
