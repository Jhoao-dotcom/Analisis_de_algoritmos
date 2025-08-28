/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.burbujaejemplo;

/**
 *
 * @author JHARE
 */
public class BurbujaEjemplo {

    // Método de ordenamiento burbuja
    public static void burbuja(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) { //pasadas para revisar i empieza en 0 y el numero de pasadas en n-1
            for (int j = 0; j < n - i - 1; j++) { // recorre la segunda parte 
                if (arr[j] > arr[j + 1]) { //compara dos datos juntos
                    // Intercambiar arr[j] y arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Método constructor para imprimir el arreglo
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Método principal
    public static void main(String[] args) {
        int[] numeros = {64, 34, 25, 12, 22, 11, 90,3, 7, 10, 15, 20, 7, 45, 11, 58, 34, 74, 47, 12, 21};

        System.out.println("Arreglo original:");
        imprimirArreglo(numeros);

        // Ordenamos con burbuja
        burbuja(numeros);

        System.out.println("Arreglo ordenado:");
        imprimirArreglo(numeros);
    }
}
