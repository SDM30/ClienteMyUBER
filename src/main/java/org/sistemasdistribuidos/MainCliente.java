package org.sistemasdistribuidos;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * @author Juan Luis Ardila
 * @author Simón Diaz
 * @author Melissa Fernanda Ruiz
 *  
 * Clase principal del cliente para la aplicación myUBER.
 * Implementa la interfaz de usuario por consola que permite interactuar
 * con los servicios remotos a través de RMI.
 */
public class MainCliente {
    /**
     * Método principal que inicia la aplicación cliente.
     * Establece la conexión con el servidor RMI y muestra un menú
     * para acceder a los servicios disponibles.
     * 
     * @param args Argumentos de línea de comando (no utilizados)
     */
    public static void main(String[] args) {
        // Colores CLI
        final String RESET = "\u001B[0m";
        final String ROJO = "\u001B[31m";
        final String VERDE = "\u001B[32m";
        final String AZUL = "\u001B[34m";

        // Inicialización del scanner para leer input del usuario
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        try {
            //Direccion IP del servidor, de lo contrario si se ejecuta como proceso dejar en localhost
            // Conexión al registro RMI en la dirección y puerto especificados
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            iMyUBER interUBER = (iMyUBER) registry.lookup("ObjetoRemotoMyUBER");

           // Bucle principal del menú de la aplicación que continúa hasta que el usuario elija salir
            do {
                // menú
                System.out.println("Bienvenido usuario a myUBER");
                System.out.println("1. Registrar usuario");
                System.out.println("2. Consultar tipos de servicio");
                System.out.println("3. Solicitar taxi");
                System.out.println("4. Salir");
                System.out.print("Opción: ");

                // Verificar que la entrada sea un número entero válido
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea pendiente

                    switch (option) {
                        case 1: // Registro de Usuario
                             // Solicita nombre y teléfono, luego invoca el método remoto
                            System.out.print(AZUL + "Ingresa el nombre del usuario: " + RESET);
                            String nombre = scanner.nextLine();

                            System.out.print(AZUL + "Ingresa el número de teléfono del usuario: " + RESET);
                            if (scanner.hasNextLong()) {
                                long telefono = scanner.nextLong();
                                scanner.nextLine(); // Consumir el salto de línea pendiente

                                // Llamada al método remoto para registrar usuario e informa del resultado
                                if (interUBER.registrarUsuario(nombre, telefono)) {
                                    System.out.println(VERDE + "Registro exitoso" + RESET);
                                } else {
                                    System.out.println(ROJO + "El usuario ya existe o algún dato no corresponde" + RESET);
                                }
                            } else {
                                System.out.println(ROJO + "Número de teléfono inválido." + RESET);
                                scanner.nextLine(); // Limpiar la entrada inválida
                            }
                            break;
                        case 2: // tipos de servicios
                            // Llamada al método remoto para obtener la información de servicios y mostrarla al usuario
                            System.out.println ( VERDE + interUBER.consutarTiposServicio () + RESET);
                            break;
                        case 3: 
                            // solicitud de taxi
                            // Recoge los datos del usuario y coordenadas, luego invoca el método remoto
                            System.out.print(AZUL + "Ingresa el nombre del usuario: " + RESET);
                            String nombreTaxi = scanner.nextLine();

                            System.out.print(AZUL + "Ingresa el número de teléfono del usuario: " + RESET);
                            if (scanner.hasNextLong()) {
                                long telefonoTaxi = scanner.nextLong();
                                scanner.nextLine(); // Consumir el salto de línea pendiente

                                System.out.print(AZUL + "Ingresa la coordenada X del usuario: " + RESET);
                                if (scanner.hasNextInt()) {
                                    int posXUsr = scanner.nextInt();
                                    scanner.nextLine(); // Consumir el salto de línea pendiente

                                    System.out.print(AZUL + "Ingresa la coordenada Y del usuario: " + RESET);
                                    if (scanner.hasNextInt()) {
                                        int posYUsr = scanner.nextInt();
                                        scanner.nextLine(); // Consumir el salto de línea pendiente

                                        // Llamar al método remoto para solicitar un taxi
                                        String resultado = interUBER.solicitarTaxi(nombreTaxi, telefonoTaxi, posXUsr, posYUsr);
                                         // Verificar si la respuesta corresponde a un taxi asignado exitosamente
                                        // El patrón verifica que sea "Taxi asignado: " seguido de dos o tres letras y dos números
                                        if (resultado.matches ("Taxi asignado: [A-Z][A-Z][A-Z]?[0-9][0-9]")) {
                                            System.out.println(VERDE + resultado + RESET);
                                        } else {
                                            System.out.println(ROJO + resultado + RESET);
                                        }
                                    } else {
                                        System.out.println(ROJO + "Coordenada Y inválida." + RESET);
                                        scanner.nextLine(); // Limpiar la entrada inválida
                                    }
                                } else {
                                    System.out.println(ROJO + "Coordenada X inválida." + RESET);
                                    scanner.nextLine(); // Limpiar la entrada inválida
                                }
                            } else {
                                System.out.println(ROJO + "Número de teléfono inválido." + RESET);
                                scanner.nextLine(); // Limpiar la entrada inválida
                            }
                            break;
                        case 4: // salir del programa
                            System.out.println("Saliendo del sistema.");
                            break;
                        default:
                            System.out.println(ROJO + "Opción inválida" + RESET);
                            break;
                    }
                } else {
                    System.out.println(ROJO + "Entrada inválida. Por favor, ingresa un número." + RESET);
                    scanner.nextLine(); // Limpiar la entrada inválida
                }
            } while (option != 4);
             // Manejo de excepciones RMI
            // RemoteException: Ocurre cuando hay problemas de comunicación con el servidor
            // NotBoundException: Ocurre cuando el objeto remoto no está registrado con el nombre especificado
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error: " + e);
        } finally {
            scanner.close();
        }
    }
}
