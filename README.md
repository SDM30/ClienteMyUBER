# Introducción a los sistemas distribuidos: Taller 1 - Cliente

**Autores:** Juan Luis Ardila, Simón Díaz y Melissa Ruíz

## Autores

- [@Juan Luis Ardila](https://github.com/jardila20)
- [@Simon Diaz](https://github.com/SDM30)
- [@Melissa Ruiz](https://github.com/mfruiz1025)

## Descripción

Este proyecto implementa el cliente para una aplicación distribuida myUBER utilizando Java RMI. El cliente permite a los usuarios:

1. Registrarse en el sistema con nombre y teléfono
2. Consultar los tipos de servicio disponibles (normal, express, excursión)
3. Solicitar un taxi proporcionando ubicación en coordenadas (x,y)

## Cómo ejecutar el programa

### Ejecutar código desde IDE

Descargar el código fuente del cliente y ejecutarlo en el IDE de preferencia.

### Ejecutar como JAR

1. Descarga el código fuente y dirígete al directorio `ClienteMyUBER/target`.
2. Ejecuta el jar con el siguiente comando:

   ```
   java -jar ClienteMyUBER-1.0-SNAPSHOT-jar-with-dependencies.jar 
   ```

### Ejecutar como proceso

No modificar el código fuente y ejecutarlo directamente. Asegúrate de que el [servidor](https://github.com/SDM30/ServidorMyUBER) esté en ejecución.

### Ejecutar en varias máquinas

1. En el código fuente, entra a la clase `MainCliente`.
2. Modifica la línea:

   ```java
   Registry registry = LocateRegistry.getRegistry("0.0.0.0", 1099);
   ```

   Por la dirección IP del servidor:

   ```java
   Registry registry = LocateRegistry.getRegistry("IP_DEL_SERVIDOR", 1099);
   ```

3. Ejecuta el código en el IDE o vuelve a crear el JAR con el comando:

   ```
   mvn clean package 
   ```

## Funcionalidades del Cliente

### 1. Registrar usuario
Permite a un nuevo usuario registrarse en el sistema proporcionando su nombre y número de teléfono.

### 2. Consultar tipos de servicio
Muestra los diferentes tipos de servicio disponibles con sus respectivos costos y descripciones.

### 3. Solicitar taxi
Permite al usuario solicitar un taxi indicando su ubicación mediante coordenadas X e Y en una matriz de 10x10. El sistema asigna el taxi más cercano.

## Requisitos

- Java 17 o superior
- Conexión a la red para comunicarse con el servidor
- El servidor debe estar en ejecución

## Estructura del proyecto

- `iMyUBER.java`: Interfaz que define los servicios remotos
- `MainCliente.java`: Clase principal que implementa la interfaz de usuario
