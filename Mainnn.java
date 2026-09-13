import java.util.Scanner;

// Sistema de Turnos - Alquiler de Bicicletas del Valle
// Estructura: Cola Estática circular (arreglo fijo con frente/final)
public class Main {

    static class Cliente {
        String nombre;
        Cliente(String nombre) { this.nombre = nombre; }
    }

    static final int CAPACIDAD_FILA = 5;
    static Cliente[] fila = new Cliente[CAPACIDAD_FILA];
    static int frente = 0, finalCola = 0, cantidadEnFila = 0;
    static int bicicletasDisponibles = 3;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n1. Solicitar bicicleta  2. Devolver bicicleta  3. Ver estado  4. Demostración  5. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();
            switch (opcion) {
                case 1 -> { System.out.print("Nombre: "); solicitar(sc.nextLine()); }
                case 2 -> devolver();
                case 3 -> verEstado();
                case 4 -> demostracion();
            }
        } while (opcion != 5);
    }

    // Operación central: si hay bici libre se asigna directo; si no, se encola (FIFO)
    static void solicitar(String nombre) {
        if (bicicletasDisponibles > 0) {
            bicicletasDisponibles--;
            System.out.println("Bicicleta asignada a " + nombre + ". Disponibles: " + bicicletasDisponibles);
        } else if (encolar(nombre)) {
            System.out.println(nombre + " entra a la fila de espera (" + cantidadEnFila + "/" + CAPACIDAD_FILA + ")");
        } else {
            System.out.println("Fila llena. " + nombre + " no pudo registrarse.");
        }
    }

    // Al devolver, si hay alguien en fila se le asigna automáticamente (FIFO)
    static void devolver() {
        if (cantidadEnFila > 0) {
            Cliente c = desencolar();
            System.out.println("Bicicleta asignada automáticamente a " + c.nombre);
        } else {
            bicicletasDisponibles++;
            System.out.println("Bicicleta devuelta. Disponibles: " + bicicletasDisponibles);
        }
    }

    static boolean encolar(String nombre) {
        if (cantidadEnFila == CAPACIDAD_FILA) return false;
        fila[finalCola] = new Cliente(nombre);
        finalCola = (finalCola + 1) % CAPACIDAD_FILA;
        cantidadEnFila++;
        return true;
    }

    static Cliente desencolar() {
        if (cantidadEnFila == 0) return null;
        Cliente c = fila[frente];
        frente = (frente + 1) % CAPACIDAD_FILA;
        cantidadEnFila--;
        return c;
    }

    static void verEstado() {
        System.out.println("Bicicletas disponibles: " + bicicletasDisponibles);
        System.out.println("Fila de espera (" + cantidadEnFila + "/" + CAPACIDAD_FILA + "):");
        for (int i = 0; i < cantidadEnFila; i++) {
            System.out.println((i + 1) + ". " + fila[(frente + i) % CAPACIDAD_FILA].nombre);
        }
    }

    // Caso normal + caso límite
    static void demostracion() {
        System.out.println("\n--- CASO NORMAL: bicicleta disponible ---");
        solicitar("Ana"); // hay disponibles -> se asigna directo

        System.out.println("\n--- CASO LÍMITE: fila de espera llena ---");
        bicicletasDisponibles = 0;      // se agota la flota
        for (int i = 0; i < CAPACIDAD_FILA; i++) solicitar("Cliente" + i); // llena la fila
        solicitar("Extra"); // debe rechazarse: fila llena
        verEstado();
    }
}
