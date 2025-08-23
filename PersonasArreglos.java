/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.personasarreglos;

/**
 *
 * @author JHARE
 */
public class PersonasArreglos {
    public static void master(String[] args) {
        // Arreglos con nombres de ejemplo
        String[] nombres = {"Jhare", "Evany", "Liz","Soso"};
        String[] apellidos = {"Torres", "Gómez", "Rojas","Ewei"};
        int[] edades = {20, 22, 19,21};

        // Mostrar los datos en pantalla
        System.out.println("Listado de personas:");
        // lector para que siga los arreglos
        for (int i = 0; i < nombres.length; i++) {
            System.out.println((i+1) + ". " + nombres[i] + " " + apellidos[i] + " - Edad: " + edades[i]); 
                    }
     }
}
