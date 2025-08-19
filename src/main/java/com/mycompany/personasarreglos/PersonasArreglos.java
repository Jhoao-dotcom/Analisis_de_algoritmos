/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.personasarreglos;

/**
 *
 * @author JHARE
 */
public class PersonasArreglos {
    public static void main(String[] args) {
        // Arreglos con nombres de ejemplo
        String[] nombres = {"Jhare", "Evany", "Liz"};
        String[] apellidos = {"Torres", "Gómez", "Rojas"};
        int[] edades = {20, 22, 19};

        // Mostrar los datos en pantalla
        System.out.println("Listado de personas:");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println((i+1) + ". " + nombres[i] + " " + apellidos[i] + " - Edad: " + edades[i]); 
                    }
     }
}
