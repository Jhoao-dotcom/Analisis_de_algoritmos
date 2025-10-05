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
// creo clase main con la iniciacion del arreglo
    public static void main(String[] args) {
        int[] numeros = {3, 7, 10, 15, 20, 7, 45, 11, 58, 34, 74, 47, 12, 21};
 // directamente para manejar error
        try (Scanner scar = new Scanner(System.in)) {
            System.out.print("Ingrese el número que desea buscar: ");
            int buscar = scar.nextInt();
            // si, sino
            if (Recorrelemento(numeros, buscar)) {
                System.out.println(" El número " + buscar + " sí está en el arreglo.");
            } else {
                System.out.println(" El número " + buscar + " no está en el arreglo.");
            }
        }
    }    
//metodo para buscar lo dato
    public static boolean Recorrelemento (int[] lista, int elemento) {
        for (int i : lista) {
            if (i == elemento) {
                return true;
            }
        }
        return false;
    }
    
}

//mejorar, para ser grande
