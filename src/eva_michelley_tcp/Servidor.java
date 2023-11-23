/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva_michelley_tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author blink
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    ServerSocket servidor;
    Socket cliente;
    Map<String, String> placa;

    public static void main(String[] args) {
        Servidor obj1 = new Servidor();
        obj1.iniciarSocketServidor(100);
    }

    public void iniciarSocketServidor(int puerto) {
        try {
            cargarPlaca();

            DataInputStream entrada;
            DataOutputStream salida;

            System.out.println("Esperando conexion " + puerto);
            servidor = new ServerSocket(puerto);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                entrada = new DataInputStream(cliente.getInputStream());
                String Plac = entrada.readUTF().toUpperCase().trim();
                System.out.println("Palabra recibida: " + Plac);
                
                if (Plac.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha solicitado salir.");
                    break;
                }

                String resultado = placa.get(Plac);

                if (resultado == null) {
                    System.out.println("Placa no encontrada");
                    resultado = "Placa no encontrada";
                }

                salida = new DataOutputStream(cliente.getOutputStream());
                salida.writeUTF(resultado);
                System.out.println("La placa corresponde ah: " + resultado);

                cliente.close();
            }

            servidor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
///==============================
    public void cargarPlaca() {
        placa = new HashMap<>();
        placa.put("A", "Azuay");
        placa.put("B", "Bolivia");
        placa.put("U", "Cañar");
        placa.put("X", "Cotopaxi");
        placa.put("H", "Chimborazo");
        placa.put("O", "El oro");
        placa.put("E", "Esmeraldas");
        placa.put("Q", "Francisco de Orellana");
        placa.put("W", "Galapagos");
        placa.put("G", "Guayas");
        placa.put("I", "Imbabura");
        placa.put("L", "Loja");
        placa.put("R", "Los Rios");
        placa.put("M", "Manabi");
        placa.put("V", "Morona Santiago");
        placa.put("N", "Napo");
        placa.put("S", "Pastaza");
        placa.put("P", "Pichincha");
        placa.put("Y", "Santa Elena");
        placa.put("J", "Santo domingo de los Tsachilas");
        placa.put("K", "Sucumbios");
        placa.put("T", "Tungurahua");
        placa.put("Z", "Zamora Chimchipe");
    }

    public Servidor() {
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
}
