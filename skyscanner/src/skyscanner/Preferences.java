package skyscanner;

public class Preferences {

	private String name;
	private int priority;//valor de 1 a 5, en el setter
	//id_tipo?

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private int getPriority() {
		return priority;
	}

	private void setPriority(int priority) {
		if (priority < 1 || priority > 5) {
			throw new IllegalArgumentException("La prioridad debe ser un valor entero entre 1 y 5");
		}
		this.priority = priority;
	}
	
}
