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
// extiende el jframe que es la ventanita
public class EstudiantesFrame extends JFrame {
//inicializa la tabla y contenido
    private final  JTable table;
    private final DefaultTableModel model;
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

    public EstudiantesFrame() {
        setTitle("Gestión de Estudiantes");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modelo de la tabla
        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Carrera"}, 0);
        table = new JTable(model);

        // Botones
        JButton btnCargar = new JButton("Cargar Estudiantes");
        JButton btnBuscar = new JButton("Buscar Estudiante");
        JButton btnOrdenar = new JButton("Ordenar por Apellido ");
        JButton btnOrden = new JButton("Ordenar por Nombre");
        JButton btnMergeSort = new JButton("Ordenar por Nombre (MergeSort)");
        JButton btnMezclaEquilibrada = new JButton("Mezcla Equilibrada Múltiple");

        // Panel de botones
        JPanel panel = new JPanel(new GridLayout(1,0,10,10));
        panel.add(btnCargar);
        panel.add(btnBuscar);
        panel.add(btnOrdenar);
        panel.add(btnOrden);
        panel.add(btnMergeSort);
        panel.add(btnMezclaEquilibrada);

        
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
//tenemos los algortismo que utilice pa esta vez
        // accion para cargar estudiantes desde SQL
        btnCargar.addActionListener(e -> cargarEstudiantes());

        //accion para búsqueda secuencial (O(n)) primer noombre
        btnBuscar.addActionListener(e -> buscarEstudiante());

        //accion para ordenar con Burbuja (O(n²)) primer apellido
        btnOrdenar.addActionListener(e -> ordenarBurbuja());
        
        //accion para ordenar con insercion, segundo nombre
        btnOrden.addActionListener(e -> ordenarInsercion());
        
        //accion para el direct merge sort, nombre sort
        btnMergeSort.addActionListener(e -> ordenarMergeSort());
        
        //mezcla equilibrada multiple
        btnMezclaEquilibrada.addActionListener(e -> ordenarMezclaEquilibrada());
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

        // Algoritmo Burbuja (O(n²))
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

        JOptionPane.showMessageDialog(this, "Estudiantes ordenados por apellido");
    }
    
    private void ordenarInsercion() {
    if (listaEstudiantes.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Primero carga los estudiantes.");
        return;
    }

    // Algoritmo Inserción (O(n²)), ordena por nombre
    for (int i = 1; i < listaEstudiantes.size(); i++) {
        Estudiante actual = listaEstudiantes.get(i);
        int j = i - 1;

        // Comparar por nombre
        while (j >= 0 && listaEstudiantes.get(j).nombre.compareToIgnoreCase(actual.nombre) > 0) {
            listaEstudiantes.set(j + 1, listaEstudiantes.get(j));
            j--;
        }
        listaEstudiantes.set(j + 1, actual);
    }
    
        // Refrescar JTable
    model.setRowCount(0);
    for (Estudiante est : listaEstudiantes) {
        model.addRow(new Object[]{est.id, est.nombre, est.apellido, est.carrera});
    }

    JOptionPane.showMessageDialog(this, "Estudiantes ordenados por nombre (Inserción).");
}
    //direct merge sort contrstuctor para ordenacion externa solo invoca
    private void ordenarMergeSort() {
        //mensaje por si no carga los datos
    if (listaEstudiantes.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Primero carga los estudiantes.");
        return;
    }

    listaEstudiantes = mergeSort(listaEstudiantes); // aplica merge sort

    // Refrescar JTable y mensaje de confirmacion 
    model.setRowCount(0);
    for (Estudiante est : listaEstudiantes) {
        model.addRow(new Object[]{est.id, est.nombre, est.apellido, est.carrera});
    }

    JOptionPane.showMessageDialog(this, "Estudiantes ordenados por nombre (Merge Sort).");
}
    
    //realizacion del merge sort
    private ArrayList<Estudiante> mergeSort(ArrayList<Estudiante> lista) {
    if (lista.size() <= 1) {
        return lista; // caso base
    }

    int mid = lista.size() / 2;
    ArrayList<Estudiante> izquierda = new ArrayList<>(lista.subList(0, mid));
    ArrayList<Estudiante> derecha = new ArrayList<>(lista.subList(mid, lista.size()));

    izquierda = mergeSort(izquierda);
    derecha = mergeSort(derecha);

    return merge(izquierda, derecha);
}

private ArrayList<Estudiante> merge(ArrayList<Estudiante> izquierda, ArrayList<Estudiante> derecha) {
    ArrayList<Estudiante> resultado = new ArrayList<>();
    int i = 0, j = 0;

    // Combinar en orden por nombre
    while (i < izquierda.size() && j < derecha.size()) {
        if (izquierda.get(i).nombre.compareToIgnoreCase(derecha.get(j).nombre) <= 0) {
            resultado.add(izquierda.get(i));
            i++;
        } else {
            resultado.add(derecha.get(j));
            j++;
        }
    }

    // Agregar lo que quede
    while (i < izquierda.size()) {
        resultado.add(izquierda.get(i));
        i++;
    }
    while (j < derecha.size()) {
        resultado.add(derecha.get(j));
        j++;
    }

    return resultado;
}

    private void ordenarMezclaEquilibrada() {
    if (listaEstudiantes.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Primero carga los estudiantes.");
        return;
    }

    // Crear runs iniciales (de 1 en 1 en este caso)
    ArrayList<ArrayList<Estudiante>> runs = new ArrayList<>();
    for (Estudiante est : listaEstudiantes) {
        ArrayList<Estudiante> run = new ArrayList<>();
        run.add(est);
        runs.add(run);
    }

    // Mezcla equilibrada: fusionar runs hasta que quede 1
    while (runs.size() > 1) {
        ArrayList<ArrayList<Estudiante>> newRuns = new ArrayList<>();

        for (int i = 0; i < runs.size(); i += 2) {
            if (i + 1 < runs.size()) {
                newRuns.add(mergeRuns(runs.get(i), runs.get(i + 1)));
            } else {
                newRuns.add(runs.get(i)); // run sobrante
            }
        }
        runs = newRuns;
    }

    // Guardar el resultado ordenado
    listaEstudiantes.clear();
    listaEstudiantes.addAll(runs.get(0));

    // Refrescar JTable
    model.setRowCount(0);
    for (Estudiante est : listaEstudiantes) {
        model.addRow(new Object[]{est.id, est.nombre, est.apellido, est.carrera});
    }

    JOptionPane.showMessageDialog(this, "Estudiantes ordenados por Mezcla Equilibrada Múltiple (por nombre)");
}

// Método auxiliar para fusionar dos runs
private ArrayList<Estudiante> mergeRuns(ArrayList<Estudiante> run1, ArrayList<Estudiante> run2) {
    ArrayList<Estudiante> merged = new ArrayList<>();
    int i = 0, j = 0;

    while (i < run1.size() && j < run2.size()) {
        if (run1.get(i).nombre.compareToIgnoreCase(run2.get(j).nombre) <= 0) {
            merged.add(run1.get(i++));
        } else {
            merged.add(run2.get(j++));
        }
    }

    while (i < run1.size()) merged.add(run1.get(i++));
    while (j < run2.size()) merged.add(run2.get(j++));

    return merged;
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstudiantesFrame().setVisible(true));
    }
}
        //acomodar en clases java para una meor organizacion
//netbeans  b









