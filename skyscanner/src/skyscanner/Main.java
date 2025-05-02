package skyscanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;


public class Main {
	
	ArrayList<Person> array = new ArrayList<>();
	
	public static HashMap<String, Integer> buscarDestinos(Person persona) {
		HashMap<String, Integer> mapa = new HashMap<>();
		
			try {
				Scanner sc = new Scanner(new FileInputStream("vuelos.csv"));
				
				while (sc.hasNext()) {
					String linea = sc.nextLine();
					String[] campos = linea.split(",");
					
					String origen = campos[0];
					String destino = campos[1];
					int precio = Integer.parseInt(campos[2]);
					
					if(origen.equals(persona.getOrigen())) {
						if (precio <= persona.getPresupuesto()) {
							mapa.put(destino, precio);
						}
					}	
				}		
			} catch (FileNotFoundException e) {
				System.out.println("Error: no se ha podido abrir el fichero " + "vuelos.csv");
			}
		
		return mapa; 
	}
	
	public static void main(String[] args) {
		//Seguridad, Costo, Accesibilidad, Clima, Comida, Cultura_Arte, Actividades,
		//Playa, Montana_Naturaleza, Vida_Nocturna;
		
		ArrayList<Preferences> preferencias1 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Preferences pref = new Preferences(PreferenciaEnum.values()[i], 2);
			preferencias1.add(pref);
		}
		
		ArrayList<Preferences> preferencias2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Preferences pref = new Preferences(PreferenciaEnum.values()[i], 3);
			preferencias2.add(pref);
		}
		
		ArrayList<Preferences> preferencias3 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Preferences pref = new Preferences(PreferenciaEnum.values()[i], 4);
			preferencias3.add(pref);
		}
		
		ArrayList<Preferences> preferencias4 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Preferences pref = new Preferences(PreferenciaEnum.values()[i], 1);
			preferencias4.add(pref);
		}
		
		Person persona1 = new Person(1, "Pedro", "Madrid", 1, 500, preferencias1);
		Person persona2 = new Person(2, "Paco", "Ibiza", 1, 500, preferencias2);
		Person persona3 = new Person(3, "Sara", "Cairo", 1, 500, preferencias3);
		Person persona4 = new Person(4, "Alberto", "Nueva York", 1, 800, preferencias4);
		
		HashMap<String, Integer> mapa1 = buscarDestinos(persona1);
		HashMap<String, Integer> mapa2 = buscarDestinos(persona2);
		HashMap<String, Integer> mapa3 = buscarDestinos(persona3);
		HashMap<String, Integer> mapa4 = buscarDestinos(persona4);
		
		HashSet<String> set = new HashSet<>(mapa1.keySet());
		set.retainAll(mapa2.keySet());
		set.retainAll(mapa3.keySet());
		set.retainAll(mapa4.keySet());
		
		for (String string : set) {
			System.out.println(string);
		}
		
	}
}
