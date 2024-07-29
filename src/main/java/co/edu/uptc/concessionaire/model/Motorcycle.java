package co.edu.uptc.concessionaire.model;

public class Motorcycle {
	private String brand,cylinder, plate, model, color, owner;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCylinder() {
		return cylinder;
	}

	public void setCylinder(String cylinder) {
		this.cylinder = cylinder;
	}

	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	
	
	
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Motorcycle(String brand, String cylinder, String plate, String model, String color, String owner) {
		super();
		this.brand = brand;
		this.cylinder = cylinder;
		this.plate = plate;
		this.model = model;
		this.color = color;
		this.owner = owner;
	}

	public Motorcycle() {}

	@Override
	public String toString() {
		return "Motorcycle [brand=" + brand + ", cylinder=" + cylinder + ", plate=" + plate + ", model=" + model
				+ ", color=" + color + ", owner=" + owner + "]";
	}
}
