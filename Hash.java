/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hash;
import java.util.HashMap;
/**
 *
 * @author JHARE
 */
public class Hash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear un HashMap con clave String y valor Integer
        HashMap<String, Integer> mapa = new HashMap<>();

        // Insertar elementos (put)
        mapa.put("Juan", 25);
        mapa.put("Maria", 30);
        mapa.put("Pedro", 40);

        // Obtener elementos (get)
        System.out.println("Edad de Juan: " + mapa.get("Juan"));
        System.out.println("Edad de Maria: " + mapa.get("Maria"));

        // Verificar si contiene una clave
        if (mapa.containsKey("Pedro")) {
            System.out.println("Pedro está en el mapa.");
        }

        // Eliminar un elemento (remove)
        mapa.remove("Maria");

        // Recorrer todo el HashMap
        System.out.println("\nContenido del HashMap:");
        for (String clave : mapa.keySet()) {
            System.out.println(clave + " → " + mapa.get(clave));
        }
    }
}
