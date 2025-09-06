/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package estudiantesframe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author JHARE
 */

    /**
     */

public class EstudiantesFrame extends JFrame {
//el jframe es para ejecutar la ventana 
    private final  JTable table;
    private final DefaultTableModel model;
    private final ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

    public EstudiantesFrame() {
        setTitle("Gestión de Estudiantes");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modelo de la tabla
        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Carrera"}, 0);
        table = new JTable(model);

        // Botones
        JButton btnCargar = new JButton("Cargar Estudiantes");
        JButton btnBuscar = new JButton("Buscar Estudiante");
        JButton btnOrdenar = new JButton("Ordenar por Apellido ");

        // Panel de botones
        JPanel panel = new JPanel();
        panel.add(btnCargar);
        panel.add(btnBuscar);
        panel.add(btnOrdenar);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Acción: cargar estudiantes desde SQL
        btnCargar.addActionListener(e -> cargarEstudiantes());

        // Acción: búsqueda secuencial (O(n))
        btnBuscar.addActionListener(e -> buscarEstudiante());

        // Acción: ordenar con Burbuja (O(n²))
        btnOrdenar.addActionListener(e -> ordenarBurbuja());
    }

    // Clase interna para manejar objetos estudiante
    class Estudiante {
        int id;
        String nombre, apellido, carrera;

        Estudiante(int id, String nombre, String apellido, String carrera) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.carrera = carrera;
        }
    }

    private void cargarEstudiantes() {
        listaEstudiantes.clear();
        model.setRowCount(0);

        String url = "jdbc:sqlserver://localhost:1433;"
                   + "databaseName=mi primera vez;"
                   + "user=sa;"
                   + "password=1234;"
                   + "encrypt=false;";

        try (Connection conn = DriverManager.getConnection(url)) {
            String query = "SELECT id, nombre, apellido, carrera FROM Estudiantes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Estudiante est = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("carrera")
                );
                listaEstudiantes.add(est);
                model.addRow(new Object[]{est.id, est.nombre, est.apellido, est.carrera});
            }

            JOptionPane.showMessageDialog(this, "Estudiantes en listaaa");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "hay un error, nose pq " + ex.getMessage());
        }
    }

    private void buscarEstudiante() {
        String nombreBuscado = JOptionPane.showInputDialog(this, "Ingrese el nombre a buscar:");
        if (nombreBuscado == null || nombreBuscado.isEmpty()) return;

        // Búsqueda secuencial (O(n))
        for (Estudiante est : listaEstudiantes) {
            if (est.nombre.equalsIgnoreCase(nombreBuscado)) {
                JOptionPane.showMessageDialog(this, 
                        "Encontrado: " + est.nombre + " " + est.apellido + " - " + est.carrera +
                        "jijii");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No se encontró el estudiante.");
    }

    private void ordenarBurbuja() {
        if (listaEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero carga los estudiantes.");
            return;
        }

        // Algoritmo Burbuja para ordenacionn (O(n²))
        int n = listaEstudiantes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listaEstudiantes.get(j).apellido.compareToIgnoreCase(listaEstudiantes.get(j + 1).apellido) > 0) {
                    // Intercambiar
                    Estudiante temp = listaEstudiantes.get(j);
                    listaEstudiantes.set(j, listaEstudiantes.get(j + 1));
                    listaEstudiantes.set(j + 1, temp);
                }
            }
        }

        // Refrescar JTable
        model.setRowCount(0);
        for (Estudiante est : listaEstudiantes) {
            model.addRow(new Object[]{est.id, est.nombre, est.apellido, est.carrera});
        }

    // los joption son la pantallita quete suelta por presion
        JOptionPane.showMessageDialog(this, "Estudiantes ordenados por apellido");
    }
//para que el frame pueda mostrarse "true"
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstudiantesFrame().setVisible(true));
    }
}
        
