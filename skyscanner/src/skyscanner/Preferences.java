package skyscanner;

public class Preferences {

	private PreferenciaEnum name;
	private int priority;//valor de 1 a 5, en el setter

	public Preferences(PreferenciaEnum name, int priority) {
		super();
		this.name = name;
		this.priority = priority;
	}

	private PreferenciaEnum getName() {
		return name;
	}

	private void setName(PreferenciaEnum name) {
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
