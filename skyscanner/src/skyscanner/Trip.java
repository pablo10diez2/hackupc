package skyscanner;

public class Trip {

	private int id;
	private String destination;
	//precio total= suma de desplazamientos de cada persona
	
	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private String getDestination() {
		return destination;
	}

	private void setDestination(String destination) {
		this.destination = destination;
	}
}
