package org.sistemasdistribuidos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iMyUBER extends Remote {
    /*
     * Registro: permite al usuario registrarse
     * Entradas: Nombre del usuario y Telefono
     * Salidas: verdadero, en caso de que este registrado, Falso, si no lo esta
     */
    public Boolean registrarUsuario(String nombre, long telefono) throws RemoteException;
    /*
     * Consultar tipos de servicio:
     */
    public String consutarTiposServicio() throws RemoteException;
    /*
     * Solicitar taxi:
     */
    public String solicitarTaxi(int posXUsr, int posYUsr) throws RemoteException;
}
