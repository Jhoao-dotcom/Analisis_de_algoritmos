/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package authsimple;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
/**
 *
 * @author JHARE
 */

    /**
     * @param args the command line arguments
     */
public class AuthSimple {
    // Usuarios almacenados en memoria (usuario -> hashContraseña)
    private static Map<String, String> usuarios = new HashMap<>();

    // Tokens de sesión (token -> usuario)
    private static Map<String, String> tokens = new HashMap<>();

    // Registrar usuario (contraseña guardada como hash)
    public static boolean registrar(String usuario, String contraseña) {
        if (usuarios.containsKey(usuario)) {
            return false; // ya existe
        }
        usuarios.put(usuario, sha256(contraseña));
        return true;
    }

    // Login (devuelve token si correcto, null si falla)
    public static String login(String usuario, String contraseña) {
        if (!usuarios.containsKey(usuario)) {
            return null;
        }
        String hashAlmacenado = usuarios.get(usuario);
        String hashIngresado = sha256(contraseña);
        if (hashAlmacenado.equals(hashIngresado)) {
            String token = UUID.randomUUID().toString();
            tokens.put(token, usuario);
            return token;
        }
        return null;
    }

    // Validar token
    public static boolean validarToken(String token) {
        return tokens.containsKey(token);
    }

    // Cerrar sesión (logout)
    public static void logout(String token) {
        tokens.remove(token);
    }

    // Generar hash SHA-256 de una contraseña
    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------------------- MENÚ POR CONSOLA ----------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== SISTEMA DE AUTENTICACIÓN SIMPLE ===");
            System.out.println("1) Registrar usuario");
            System.out.println("2) Login");
            System.out.println("3) Validar token");
            System.out.println("4) Logout");
            System.out.println("5) Salir");
            System.out.print("Elige opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Usuario: ");
                    String u = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String p = sc.nextLine();
                    if (registrar(u, p)) {
                        System.out.println("Usuario registrado.");
                    } else {
                        System.out.println("El usuario ya existe.");
                    }
                    break;

                case "2":
                    System.out.print("Usuario: ");
                    String uLogin = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pLogin = sc.nextLine();
                    String token = login(uLogin, pLogin);
                    if (token != null) {
                        System.out.println("Login exitoso. Token: " + token);
                    } else {
                        System.out.println("Usuario o contraseña incorrectos.");
                    }
                    break;

                case "3":
                    System.out.print("Introduce token: ");
                    String tVal = sc.nextLine();
                    if (validarToken(tVal)) {
                        System.out.println("Token válido. Usuario: " + tokens.get(tVal));
                    } else {
                        System.out.println("Token inválido.");
                    }
                    break;

                case "4":
                    System.out.print("Introduce token: ");
                    String tOut = sc.nextLine();
                    logout(tOut);
                    System.out.println("Sesión cerrada (si el token existía).");
                    break;

                case "5":
                    activo = false;
                    System.out.println("Cerrando sistema.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}

