package skyscanner;

import java.util.ArrayList;

public class Trip {

	private int id;
	private ArrayList<String> destination;
	private ArrayList<Person> personas;
	public Trip(int id, ArrayList<String> destination, ArrayList<Person> personas) {
		super();
		this.id = id;
		this.destination = destination;
		this.personas = personas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<String> getDestination() {
		return destination;
	}
	public void setDestination(ArrayList<String> destination) {
		this.destination = destination;
	}
	public ArrayList<Person> getPersonas() {
		return personas;
	}
	public void setPersonas(ArrayList<Person> personas) {
		this.personas = personas;
	}

	
	
}
