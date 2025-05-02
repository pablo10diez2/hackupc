package skyscanner;

import java.util.ArrayList;

public class Person {
	private int id;
	private String nombre;
	private String origen;
	private int idViaje;
	private int presupuesto;
	private ArrayList<Preferences> preferencias;
	
	public Person(int id, String nombre, String origen, int idViaje, int presupuesto,
			ArrayList<Preferences> preferencias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.origen = origen;
		this.idViaje = idViaje;
		this.presupuesto = presupuesto;
		this.preferencias = preferencias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public ArrayList<Preferences> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(ArrayList<Preferences> preferencias) {
		this.preferencias = preferencias;
	}
	

}
