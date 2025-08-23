/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.complejidad_algoritmica;
import java.util.Scanner;
/**
 *
 * @author JHARE
 */
public class Complejidad_algoritmica {

    public static void main(String[] args) {
        int[] numeros = {3, 7, 10, 15, 20};

        try (Scanner scar = new Scanner(System.in)) {
            System.out.print("Ingrese el número que desea buscar: ");
            int buscar = scar.nextInt();
            if (Recorrelemento(numeros, buscar)) {
                System.out.println(" El número " + buscar + " SÍ está en el arreglo.");
            } else {
                System.out.println(" El número " + buscar + " NO está en el arreglo.");
            }
            // lo de aqui abajo lo borre, no se como
        }
    }    

    public static boolean Recorrelemento (int[] lista, int elemento) {
        for (int i : lista) {
            if (i == elemento) {
                return true;
            }
        }
        return false;
    }
}