package orderingSystem;

public class Lunch {

	private String cousineType;
	private String name;
	private double price;

	public Lunch(String cousineType, String name, double price) {

		this.cousineType = cousineType;
		this.name = name;
		this.price = price;
	}

	public String getCousineType() {
		return cousineType;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

}
