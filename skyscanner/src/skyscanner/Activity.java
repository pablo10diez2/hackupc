package skyscanner;

public class Activity {

	private int id;
	private String name;
	private String description;
	//private int price //se incluye en el presupuesto de cada persona?
	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}
}
