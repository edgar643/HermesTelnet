package utilidades;
import javax.swing.*;

/**
 * Clase que genera Herramientas de visualización e interacción.
 * Muy útil para generar dichas herramientas con menos líneas de código
 * @author Ing. Manuel Pérez P.
 * @version 2.0
 */
public class Advertencia {
//    ConvertidorHTML CH = new ConvertidorHTML();
    /** 
     * Método que crea una ventana de advertencia al usuario
     * @param mensaje Texto que se desea mostrar en la ventana de advertencia
     *
     */
  
    public void advertencia(String mensaje) {
         JFrame adv = new JFrame();         
           adv.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          
       JOptionPane.showMessageDialog(adv,mensaje,"Advertencia",JOptionPane.WARNING_MESSAGE);
            adv.setVisible(true);
           
    }

    /**
     * Método que permite crear una ventana al estilo 'Aceptar' 'Cancelar'
     * @param mensaje Texto que se desea mostrar en la ventana donde se va a
     * realizar la ocnsulta
     * @param opciones texto de los botones de la ventana. Por defecto el programa selecciona el boton ubicado en la posición '1'
     * @return true si el usuario selecciona el boton principal (posición '0')
     */
    public boolean Pregunta(String mensaje, String[] opciones){
        boolean respuesta=false;
        JFrame preg=new JFrame();
   preg.setLocationRelativeTo(null);
int respondido=JOptionPane.showOptionDialog(preg,""+mensaje,"¡Atención!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[1]);
         respuesta =(respondido==0)? true: false;
        //preg.setVisible(true);

        return respuesta;
    }

    /**
     * Método que crea una ventana de información al usuario
     * @param mensaje Texto que se desea mostrar en la ventana de información
     */
    public void Información(String mensaje) {
         JFrame adv = new JFrame();
        adv.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            JOptionPane.showMessageDialog(adv,(mensaje),"Información",JOptionPane.INFORMATION_MESSAGE);            
    }
    
 public void warning(String message){
 JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame,
    message,
    "Advertencia",
    JOptionPane.WARNING_MESSAGE);
 
 }
    

    /**
     * Método que permite crear una ventana de advertencia con un formato especial, muy útil para alertar las excepciones
     * @param mensaje Texto que indica el tipo de excepción capturada
     * @param ex Texto capturado por la excepción
     * @param clase Clase u objeto que lanza la excepción
     */
    public void Excepcion(String mensaje, String ex, String clase){
//        advertencia(clase+"\n"+CH.eHTML(mensaje+": "+CH.fuente(ex, "RED")));
    }

    /**
     * Método que permite solicitar al usuario que ingrese un valor
     * @param mensaje Texto que contiene la solicitud de datos al usuario
     * @return la información ingresada por el usuario
     */
    public String Solicitud(String mensaje) {
         JFrame adv = new JFrame();
     
        adv.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            String respondido=JOptionPane.showInputDialog(adv,mensaje,"Atención",JOptionPane.QUESTION_MESSAGE);

            //adv.setVisible(true);

            return respondido;
    }
}
