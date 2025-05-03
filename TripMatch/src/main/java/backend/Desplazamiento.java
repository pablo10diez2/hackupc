package backend;

public class Desplazamiento {
	
	private String id;
	private String origin;
	private String destination;
	private String departureDate;
	private int price;
	
	private String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	private String getOrigin() {
		return origin;
	}

	private void setOrigin(String origin) {
		this.origin = origin;
	}

	private String getDestination() {
		return destination;
	}

	private void setDestination(String destination) {
		this.destination = destination;
	}

	private String getDepartureDate() {
		return departureDate;
	}

	private void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	private int getPrice() {
		return price;
	}

	private void setPrice(int price) {
		this.price = price;
	}
}
