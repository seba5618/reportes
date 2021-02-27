package ar.com.bambu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * <p>Title: Conversi&oacute;n de datos</p>
 * <p>Description: paquete de funciones que permiten convertir entre los
 * tipos de datos usados en POS y los usados en otros servicios.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Invel Latinoamercana S.A.</p>
 * @author not attributable
 * @version 1.2
 */
public class ConversorDatos {

  public static final int DDMMAAAA = 0x01;
  public static final int MMDDAAAA = 0x02;
  public static final int AAAAMMDD = 0x03;
  public static final int DDMMAAAA_SIN_SEP = 0x04;
  public static final int YYYYMMDD_SIN_SEP = 0x05;
  public static final int AAAMMDD_CON_GUIONES_MEDIOS = 0x06;

  /**
   * Verifica que la fecha btrieve tenga un formato v&aacute;lido.
   * @param fecha Valor short con la fecha codificada
   * @return true si la fecha codificada es correcta
   */
  public static boolean chequearFechaInvel(short fecha) {
    boolean result = true;
    int anio = ((fecha & 0xFE00) >> 9) + 1980;
    int mes = ((fecha & 0x01E0) >> 5);
    int dia = (fecha & 0x001F);
    if (dia > 31 || dia <= 0 || mes > 12 || mes <= 0 || anio > 2099 ||
        anio <= 0) {
      result = false;
    }
    return result;
  }

  /**
   * Convierte una fecha btrieve a Date de java.
   */
  public static Date fechaInvelADate(short fecha) {
    GregorianCalendar cal = new GregorianCalendar(
    	getAnnoFechaInvel(fecha),
        getMesFechaInvel(fecha)-1,   //Month value is 0-based. e.g., 0 for January.
        getDiaFechaInvel(fecha)
        );
    return cal.getTime();
  }

  /**
   * Convierte una fecha btrieve a texto. Los formatos DDMMAAAA y MMDDAAAA
   * llevan '/' como separadores (es decir, en realidad es DD/MM/AAAA o
   * MM/DD/AAAA) mientras que AAAAMMDD no lleva separaci&oacute;n alguna.
   * Se agrega el formato DDMMAAAA_SIN_SEP como DDMMAAAA sin separador.
   * @param fecha la fecha codificada
   * @param formato alguno de los formatos almacenados en ConversorDatos, a
   *                saber: DDMMAAAA, MMDDAAAA, AAAAMMDD o DDMMAAAA_SIN_SEP.
   * @return un String con la fecha en el formato indicado.
   */
  public static String fechaInvelATexto(short fecha, int formato) {
    String result = null;
    if (!ConversorDatos.chequearFechaInvel(fecha)) {
      switch (formato) {
        case ConversorDatos.AAAAMMDD:
          result = new String("19800101");
          break;
        case ConversorDatos.DDMMAAAA_SIN_SEP:
          result = new String("01011980");
          break;
        case ConversorDatos.AAAMMDD_CON_GUIONES_MEDIOS:
          result = new String("1980-01-01");
          break;
        case ConversorDatos.DDMMAAAA:
        case ConversorDatos.MMDDAAAA:
        default:
          result = new String("01/01/1980");
          break;
      }
    }
    else {
      int anio = ((fecha & 0xFE00) >> 9) + 1980;
      int mes = ((fecha & 0x01E0) >> 5);
      int dia = (fecha & 0x001F);
      switch (formato) {
        case ConversorDatos.MMDDAAAA:
          result = (mes < 10 ? "0" : "") + String.valueOf(mes).trim() +
              "/" + (dia < 10 ? "0" : "") + String.valueOf(dia).trim() +
              "/" + String.valueOf(anio).trim();
          break;
        case ConversorDatos.AAAAMMDD:
          result = String.valueOf(anio).trim() + (mes < 10 ? "0" : "") +
              String.valueOf(mes).trim() + (dia < 10 ? "0" : "") +
              String.valueOf(dia).trim();
          break;
        case ConversorDatos.DDMMAAAA_SIN_SEP:
          result = (dia < 10 ? "0" : "") + String.valueOf(dia).trim() +
              (mes < 10 ? "0" : "") + String.valueOf(mes).trim() +
              String.valueOf(anio).trim();
          break;
        case ConversorDatos.AAAMMDD_CON_GUIONES_MEDIOS:
          result =String.valueOf(anio).trim() + (mes < 10 ? "0" : "") +
                  "-" +String.valueOf(mes).trim() + (dia < 10 ? "0" : "") +
                  "-" +String.valueOf(dia).trim();
          break;
        case ConversorDatos.DDMMAAAA:
        default:
          result = (dia < 10 ? "0" : "") + String.valueOf(dia).trim() +
              "/" + (mes < 10 ? "0" : "") + String.valueOf(mes).trim() +
              "/" + String.valueOf(anio).trim();
          break;
      }
    }
    return result;
  }

  /**
   * Obtiene el dd&iacute;a a partir de una fecha codificada.
   * @param fecha La fecha codificada
   * @return El d&iacute; representado en esa fecha
   */
  public static int getDiaFechaInvel(short fecha) {
    int result = -1;
    if (ConversorDatos.chequearFechaInvel(fecha)) {
      result = (fecha & 0x001F);
    }
    return result;
  }

  /**
   * Obtiene el mes a partir de una fecha codificada.
   * @param fecha La fecha codificada
   * @return El mes representado en esa fecha
   */
  public static int getMesFechaInvel(short fecha) {
    int result = -1;
    if (ConversorDatos.chequearFechaInvel(fecha)) {
      result = ((fecha & 0x01E0) >> 5);
    }
    return result;
  }

  /**
   * Obtiene el a&ntilde;o a partir de una fecha codificada.
   * @param fecha La fecha codificada
   * @return El a&ntilde;o representado en esa fecha
   */
  public static int getAnnoFechaInvel(short fecha) {
    int result = -1;
    if (ConversorDatos.chequearFechaInvel(fecha)) {
      result = ((fecha & 0xFE00) >> 9) + 1980;
    }
    return result;
  }

  /**
   * Convierte la fecha pasada en la cadena como argumento a un formato
   * de dos bytes num&eacute;ricos representativos del formato btrieve. Los
   * formatos DDMMAAAA y MMDDAAAA suponen alg&uacute;n caracter separador
   * entre los campos. El separador por defecto es '/'.
   * @param fecha la fecha como String
   * @param formato alguno de los formatos almacenados en ConversorDatos, a
   *                saber: DDMMAAAA, MMDDAAAA, AAAAMMDD o DDMMAAAA_SIN_SEP.
   * @return Un entero de dos bytes con la fecha codificada
   */
  public static short fechaTextoAInvel(String fecha, int formato) {
    int dia, anio, mes;
    switch (formato) {
      case ConversorDatos.MMDDAAAA: // ej. 03/21/2002
        mes = Integer.parseInt(fecha.substring(0, 2));
        dia = Integer.parseInt(fecha.substring(3, 5));
        anio = Integer.parseInt(fecha.substring(6, 10));
        break;
      case ConversorDatos.AAAAMMDD: // ej. 20020321
        anio = Integer.parseInt(fecha.substring(0, 4));
        mes = Integer.parseInt(fecha.substring(4, 6));
        dia = Integer.parseInt(fecha.substring(6, 8));
        break;
      case ConversorDatos.DDMMAAAA_SIN_SEP: // ej. 21032002
        dia = Integer.parseInt(fecha.substring(0, 2));
        mes = Integer.parseInt(fecha.substring(2, 4));
        anio = Integer.parseInt(fecha.substring(4, 8));
        break;
      case ConversorDatos.DDMMAAAA: // ej. 21/03/2002
      default:
        dia = Integer.parseInt(fecha.substring(0, 2));
        mes = Integer.parseInt(fecha.substring(3, 5));
        anio = Integer.parseInt(fecha.substring(6, 10));
        break;
    }
    return ConversorDatos.crearFechaInvel(dia, mes, anio);
  }

  /**
   * Crea una fecha btrieve a partir del d&iacute;a, mes y a&ntilde;o
   * @param dia El par&aacute;metro d&iacute;a
   * @param mes El par&aacute;metro mes
   * @param anio El par&aacute;metro a&ntilde;o
   * @return Entero de dos bytes con la fecha codificada
   */
  public static short crearFechaInvel(int dia, int mes, int anio) {
    int fecha = 0x1f80;
    if (dia > 31 || dia <= 0 || mes > 12 || mes <= 0 || anio > 2099) {
      fecha = 0;
    }
    else {
      fecha = (anio - 1980) << 9;
      fecha += (mes << 5);
      fecha += dia;
    }
    return (short)fecha;
  }

  /**
   * Fecha de hoy, en formato Invel.
   * @return fecha codificada
   */
  public static short getFechaHoyInvel() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
        "yyyyMMdd", new Locale("es"));
    return ConversorDatos.fechaTextoAInvel(dateFormat.format(new Date()),
        ConversorDatos.AAAAMMDD);
  }

  /**
   * Fecha de hoy, en formato String.
   * @return fecha como texto AAAAMMDD
   */
  public static String getFechaHoyString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
        "yyyyMMdd", new Locale("es"));
    return dateFormat.format(new Date());
  }

  /**
   * Hora actual, como String HHMMSS
   * @return hora como texto
   */
  public static String getHoraAhoraString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
        "HHmmss", new Locale("es"));
    return dateFormat.format(new Date());
  }

  /**
   * Hora actual, como String HH:MM:SS
   * @return hora como texto
   */
  public static String getHoraAhoraString2P() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
        "HH:mm:ss", new Locale("es"));
    return dateFormat.format(new Date());
  }

  /**
   * Transforma un String de hora del formato HHMMSS a HH:MM:SS.
   * @param hr La hora en formato HHMMSS
   * @return La hora en formato HH:MM:SS
   */
  public static String horaAgregar2P(String hr) {
    return (hr.substring(0, 2) + ":" + hr.substring(2,
        4) + ":" + hr.substring(4, 6));
  }

  /**
   * Transforma un String de hora del formato HH:MM:SS a HHMMSS.
   * @param hr La hora en formato HH:MM:SS
   * @return La hora en formato HHMMSS
   */
  public static String horaQuitar2P(String hr) {
    return (hr.substring(0, 2) + hr.substring(3, 5) + hr.substring(6, 8));
  }

  /**
   * Convierte un short con hora en formato invel al formato "hhmmss".
   * @param hr short con la hora en formato invel.
   * @return String con la hora como texto.
   */
  public static String HoraInvel2String(int hr) {
    String result = String.valueOf(hr);
    while (result.length() < 6) {
      result = '0' + result;
    }
    return result;
  }

  /**
   * Convierte un string con hora en formato "hhmmss" a un short con formato
   * invel.
   * @param hr String en formato "hhmmss".
   * @return short con hora en formato invel.
   */
  public static int HoraString2Invel(String hr) {
    return Integer.parseInt(hr);
  }

}
