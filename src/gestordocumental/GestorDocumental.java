package gestordocumental;



import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
//import lombok.Cleanup;

/**
 * Clase que crea e imprime informacion en log y genera lista temporal con
 * archivo txt.
 *
 * @author edgar.garcia
 */
public class GestorDocumental {

    /**
     * Metodo para la lectura de propiedades
     *
     * @param proper
     * @return
     */
    
    private final String NOMB_ARCH_PROP = "";
    private final String NOMB_ARCH_PROP2 = "";
    public String leerProperties(final int Opcion, final String proper) {
        String propiedad;
        final Properties prop = new Properties();
        final InputStream input_stream;
        try {

            if (Opcion == 1) {
                input_stream = new FileInputStream(NOMB_ARCH_PROP);
            } else {
                input_stream = new FileInputStream(NOMB_ARCH_PROP2);
            }

            prop.load(input_stream);
            input_stream.close();
        } catch (IOException ex) {
            this.imprimir(0, ex.toString());
        }
        propiedad = prop.getProperty(proper, "");
        prop.clear();
        return propiedad;
    }

     /**
     * Metodo que devuelve D(D)/MM/AAAA (Si Paso 0 devuelve dia de hoy, si paso
     * 1 devuelve dia anterior, si paso 2 devuelve 2 dias atras)
     *
     * @param dia
     * @return
     */
    public String obtenerFecha(final int dia) {
        // dia = 0 hoy , dia = 1 diaAnterior
        final StringBuilder retorno = new StringBuilder();
        final long Hoy = System.currentTimeMillis();
        final Date now = new Date();
        if (dia == 0) {
            now.setTime(Hoy); // le resto un dia
        } else {
            now.setTime(Hoy - 86400000 * ((long) dia)); // le resto dia(s)
        }
        final SimpleDateFormat fdefault = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        final String diaReturn = fdefault.format(now);
        retorno.append(diaReturn);
        return retorno.toString();
    }

    /**
     * Metodo que permite obtener la fecha de consulta
     *
     * @return
     */
    public String obtenerFechaConsulta() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.YEAR)) + "-" + Integer.toString(capturar_fecha.get(Calendar.MONTH)) + "-" + Integer.toString(capturar_fecha.get(Calendar.DATE));
    }

    /**
     * Metodo que permite obtener la fecha actual
     *
     * @return
     */
    public String obtenerFechaActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.YEAR)) + agregarCerosIzquierda(Integer.toString((capturar_fecha.get(Calendar.MONTH)) + 1)) + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.DATE)));
    }

    /**
     * Metodo que permite obtener la hora actual
     *
     * @return
     */
    public String obtenerHoraActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.HOUR_OF_DAY)) + ":" + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.MINUTE))) + ":" + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.SECOND)));
    }

    /**
     * Metodo que permite obtener la hora actual, sin precisar los segundos
     *
     * @return
     */
    public String obtenerHHMMActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.HOUR_OF_DAY)) + ":" + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.MINUTE)));
    }

    /**
     * Metodo que agrega ceros a la izquierda hasta que se cumpla el tamaño
     * deseado
     *
     * @param diames
     * @return
     */
    public String agregarCerosIzquierda(final String diames) {
        final StringBuilder retorno = new StringBuilder();
        if (Integer.parseInt(diames) < 10) {
            final String add = "0" + diames;
            retorno.append(add);
        } else {
            retorno.append(diames);
        }
        return retorno.toString();
    }

    /**
     * Metodo que permite generar el nombre de documento respecto al tipo
     * indicado
     *
     * @param tipo
     * @return
     */
    private String generarNombreDocumento(final int tipo) {
        final StringBuilder retorno = new StringBuilder();
        if (tipo == 0) {
            final String add = obtenerFechaActual() + ".txt";
            retorno.append(add);
        }
        if (tipo == 1) {
            final String add = obtenerFechaActual() + "-LogErrores.txt";
            retorno.append(add);
        }
        if (tipo == 2) {
            final String add = obtenerFechaActual() + "-LogEventos.txt";
            retorno.append(add);
        }
        return retorno.toString();
    }

    /**
     * Metodo que permite guardar el archivo txt deseado
     *
     * @param tipo
     * @param aImprimir
     */
    public void imprimir(final int tipo, final String aImprimir) {
        synchronized (this) {
            try (final FileWriter file_writer = new FileWriter(generarNombreDocumento(tipo), true);
                    final BufferedWriter buffer_writer = new BufferedWriter(file_writer);) {
                buffer_writer.write(aImprimir);
                buffer_writer.newLine();
            } catch (Exception ex) {
                Logger.getLogger(GestorDocumental.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
