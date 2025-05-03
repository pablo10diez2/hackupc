package skyscanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.FileInputStream;


public class Main {
	
	public static LinkedHashMap<String, Float> ordenarDestinos(Trip viaje, HashMap<PreferenciaEnum, Float>mapa){
		HashMap<String, Float> mapaDestinos = new HashMap<>();
		PreferenciaEnum[] preferencias = PreferenciaEnum.values();
		for (String destino : viaje.getDestination()) {
			try {
				Scanner sc = new Scanner(new FileInputStream("preferencias.csv"));
				
				while (sc.hasNext()) {
					String linea = sc.nextLine();
					String[] campos = linea.split(",");
					
					String destinoViaje = campos[0];
					float puntuacion = 0;
					if(destino.equals(destinoViaje)) {
						for (int i = 0; i < preferencias.length; i++) {
	                        PreferenciaEnum clave = preferencias[i];
	                        Float valorUsuario = mapa.get(clave);
	                        int valorDestino = Integer.parseInt(campos[i + 1]);

	                        if (valorUsuario != null) {
	                            puntuacion += 4 - Math.abs(valorUsuario - valorDestino);
	                        }   
	                    }
						puntuacion=(float) (puntuacion*2.5);//puntuacion entre 0 y 100
						mapaDestinos.put(destinoViaje, puntuacion);

					}		
				}
				} catch (FileNotFoundException e) {
				System.out.println("Error: no se ha podido abrir el fichero " + "preferencias.csv");
			}
		}
		
		return mapaDestinos.entrySet().stream().sorted(Map.Entry.<String, Float>comparingByValue().reversed()).collect
				(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1, e2) -> e1,LinkedHashMap::new));
	}
	public static void resultadoFinal(LinkedHashMap<String, Float> mapaPuntuaciones) {
	    try {
	        System.out.println("Los mejores destinos son los siguientes:\n");

	        Scanner sc = new Scanner(new FileInputStream("actividadesporciudad.csv"));
	        Map<String, List<String>> actividadesPorCiudad = new HashMap<>();
	        while (sc.hasNext()) {
	            String linea = sc.nextLine();
	            String[] campos = linea.split(",");
	            actividadesPorCiudad.put(campos[0], Arrays.asList(Arrays.copyOfRange(campos, 1, campos.length)));
	        }

	        int contador = 0;
	        for (String destino : mapaPuntuaciones.keySet()) {
	            if (contador >= 5) break; // Limita a 5 destinos

	            List<String> actividades = actividadesPorCiudad.get(destino);
	            if (actividades != null) {
	                System.out.println(destino + " con un " + mapaPuntuaciones.get(destino) + "% de compatibilidad...");
	                for (int i = 0; i < Math.min(5, actividades.size()); i++) {
	                    System.out.println((i + 1) + ". " + actividades.get(i));
	                }
	            }
	            System.out.println("\n");
	            contador++;
	        }

	    } catch (FileNotFoundException e) {
	        System.out.println("Error: no se ha podido abrir el fichero " + "actividadesporciudad.csv");
	    }
	}

	
	public static HashMap<PreferenciaEnum, Float> preferenciaEquipo(Trip viaje){
		HashMap<PreferenciaEnum, Float> mapa = new HashMap<>();
		
		int i = 0;
		for (PreferenciaEnum e : PreferenciaEnum.values()) {
			Float media = 0.0f;
			for (Person persona : viaje.getPersonas()) {
				media = media + persona.getPreferencias().get(i).getPriority();
			}
			media = media / viaje.getPersonas().size();
			mapa.put(e, media);
			i++;
		}
		
		return mapa;
	}
	
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
		Person persona2 = new Person(2, "Paco", "Ibiza", 1, 1000, preferencias2);
		Person persona3 = new Person(3, "Sara", "Cairo", 1, 1000, preferencias3);
		Person persona4 = new Person(4, "Alberto", "Nueva York", 1, 1200, preferencias4);
		
		HashMap<String, Integer> mapa1 = buscarDestinos(persona1);
		HashMap<String, Integer> mapa2 = buscarDestinos(persona2);
		HashMap<String, Integer> mapa3 = buscarDestinos(persona3);
		HashMap<String, Integer> mapa4 = buscarDestinos(persona4);
		
		HashSet<String> set = new HashSet<>(mapa1.keySet());
		set.retainAll(mapa2.keySet());
		set.retainAll(mapa3.keySet());
		set.retainAll(mapa4.keySet());
		
		ArrayList<Person> arrayPersonas = new ArrayList<>();
		ArrayList<String> arrayDestinos = new ArrayList<>();
		Trip viaje = new Trip(1, arrayDestinos, arrayPersonas);
		
		viaje.getPersonas().add(persona1);
		viaje.getPersonas().add(persona2);
		viaje.getPersonas().add(persona3);
		viaje.getPersonas().add(persona4);
		
		for (String string : set) {
//			System.out.println(string);
			viaje.getDestination().add(string);
		}
		
		HashMap<PreferenciaEnum, Float> preferenciaEquipo = preferenciaEquipo(viaje);
//		for (PreferenciaEnum e : preferenciaEquipo.keySet()) {
//			System.out.println(e+": "+preferenciaEquipo.get(e));
//		}
		
		LinkedHashMap<String, Float> mapaPuntuaciones = ordenarDestinos(viaje, preferenciaEquipo);
//		for (String s : mapaPuntuaciones.keySet()) {
//			System.out.println(s+": "+mapaPuntuaciones.get(s));
//		}
		resultadoFinal(mapaPuntuaciones);
	}
}
