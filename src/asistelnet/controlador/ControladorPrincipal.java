/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistelnet.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar J García L
 */
public class ControladorPrincipal {

    public String imprimirAlServer(final String aImprimir, final String HOST, final int PUERTO) {
        try (Socket newClient = new Socket(HOST, PUERTO);
                final PrintWriter out = new PrintWriter(newClient.getOutputStream(), true);) {
            out.println(aImprimir);
            String respuesta = leerRespuestaServer(HOST, PUERTO, newClient);
            
            return respuesta;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getGlobal().log(Level.SEVERE, ex.toString());

            return null;
        }
    }

    public String leerRespuestaServer(final String HOST, final int PUERTO,Socket NewClient) {
        String trama = "SIN RESPUESTA TIME OUT EXCEDIDO";
        try {
          
            NewClient.setSoTimeout(15000);
            int character;
            final BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(NewClient.getInputStream()));
            final StringBuilder string_builder = new StringBuilder();
            while (true) {
                character = buffer_reader.read();
                string_builder.append((char) character);
                if (character == 126 || character == 13) {
                    break;
                } else if (character == -1) {
                    break;
                }
            }
            trama = string_builder.toString();
        } catch (IOException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return trama;
        }
        return trama;
    }
}
