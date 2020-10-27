/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scec;

import java.util.Formatter;

/**
 * Clase que peermite añadir código html a un String para mejorar la visualización en componentes swing
 * @version 0.1 (negritas, cursivas y cambio de color de fuente)
 * @author Ing. Manuel Pérez P.
 */
public class ConvertidorHTML {

    /**
     * Método que convierte a negritas un texto a ser representado en un componente Swing
     * @param cadena cadena a convertir
     * @return cadena en negritas para Swing
     */
    public String negrita(String cadena){

        cadena="<b>"+cadena+"</b>";

        return cadena;

    }

    /**
     * Método que convierte a cursivas un texto a ser representado en un componente Swing
     * @param cadena cadena a convertir
     * @return cadena en cursivas para Swing
     */
    public String cursiva(String cadena){

        cadena="<i>"+cadena+"</i>";

        return cadena;

    }

    /**
     * Método que convierte a negritas y cursivas un texto a ser representado en un componente Swing
     * @param cadena cadena a convertir
     * @return cadena en negritas cursivas para Swing
     */
    public String negritaCursiva(String cadena){

        cadena="<b><i>"+cadena+"</i></b>";

        return cadena;

    }

    /**
     * Método que modifica el color de la fuente de un texto a representarse en un componente Swing
     * @param cadena cadena a convertir
     * @param color color de la fuente
     * @return cadena con un formato de color para swing
     */
    public String fuente(String cadena, String color){
cadena="<font color='"+color+"'>"+cadena+"</font>";  
return cadena;
    }
public String fuenteH(String cadena, int color){


int colorF = (color & 0x00ffffff); 
String colorHex = Integer.toHexString(colorF);
int c = colorHex.length();
if(c == 4){
colorHex ="00"+colorHex;
}
if(c == 5){
colorHex ="0"+colorHex;
}
//System.out.println(Integer.toHexString(colorF));

cadena="<font color = #"+colorHex+">"+cadena+"</font>";

        return cadena;    }
    /**
     * Método que añade las etiquetas html a un texto
     * @param cadena Cadena a convertir
     * @return cadena con etiquetas html
     */
    public String eHTML(String cadena){
      

cadena="<html>"+cadena+"</html>";
return cadena;
    }

}
