package backend;

public class Preferences {

	private PreferenciaEnum name;
	private int priority;//valor de 1 a 5, en el setter

	public Preferences(PreferenciaEnum name, int priority) {
		super();
		this.name = name;
		this.priority = priority;
	}

	public PreferenciaEnum getName() {
		return name;
	}

	public void setName(PreferenciaEnum name) {
		this.name = name;
	}

	int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		if (priority < 1 || priority > 5) {
			throw new IllegalArgumentException("La prioridad debe ser un valor entero entre 1 y 5");
		}
		this.priority = priority;
	}
	
}
