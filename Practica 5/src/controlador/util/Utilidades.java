/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author sebastian
 */
public class Utilidades {

    public static void escribir(File archivo, String contenido) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write(contenido);
        }
    }

    public static Field getField(Class clazz, String attribte) {
        Field[] fields = clazz.getFields();
        Field resp = null;
        //System.out.println(fields.length);
        for (Field f : fields) {
            if (attribte.equalsIgnoreCase(f.getName())) {
                resp = f;
            }
        }
        Field[] fields1 = clazz.getDeclaredFields();
        // System.out.println(fields1.length);
        for (Field f : fields1) {
            if (attribte.equalsIgnoreCase(f.getName())) {
                resp = f;
            }
        }
        return resp;
    }

    public static Object getData(Object clas, String attribte) throws InvocationTargetException, IllegalAccessException {
        Class clazz = clas.getClass();
        Field f = getField(clazz, attribte);
        Object obj = null;
        if (f != null) {
            String auxAttribute = "get" + capitalize(attribte);
            Method method = null;
            for (Method m : clazz.getMethods()) {
                if (m.getName().equalsIgnoreCase(auxAttribute)) {
                    method = m;
                    break;
                }
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().equalsIgnoreCase(auxAttribute)) {
                    method = m;
                    break;
                }
            }
            if (method != null) {
                obj = method.invoke(clas);
            }
        }
        return obj;
    }

    public static String capitalize(String cnd) {
        char[] caracteres = cnd.toCharArray();
        String aux = String.valueOf(caracteres[0]).toUpperCase();
        caracteres[0] = aux.charAt(0);
        return new String(caracteres);
    }

    public static String getDirProject() {
        return System.getProperty("user.dir");
    }

    public static String getOS() {
        return System.getProperty("os.name");
    }

    public static void abrirNavegadorPredeterminadoWindows(String url) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // En Windows
            String bravePath = "C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";
            String comando = "\"" + bravePath + "\" " + url;
            Runtime.getRuntime().exec(comando);
        } else {
            throw new UnsupportedOperationException("Este método solo es compatible con Windows");
        }
    }

    public static void abrirNavegadorPredeterminadoLinux(String url) throws Exception {
//        Runtime.getRuntime().exec("xdg-open " + url);
        Runtime.getRuntime().exec("/usr/bin/brave-browser -new window  " + url); //cambiar google
    }

    public static void abrirNavegadorPredeterminadoMacOsx(String url) throws Exception {
        Runtime.getRuntime().exec("open " + url);
    }

    public static void copiarArchivo(File origen, File destino) throws Exception {
        Files.copy(origen.toPath(),
                (destino).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }

    public static String extension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static double distanciaDosPuntos(double lat1, double lon1, double lat2, double lon2) {
        double lat1rad = Math.toRadians(lat1);
        double lon1rad = Math.toRadians(lon1);
        double lat2rad = Math.toRadians(lat2);
        double lon2rad = Math.toRadians(lon2);

        double difLatitud = lat1rad - lat2rad;
        double difLongitud = lon1rad - lon2rad;

        double a = Math.pow(Math.sin(difLatitud / 2), 2)
                + Math.cos(lat1rad)
                * Math.cos(lat2rad)
                * Math.pow(Math.sin(difLongitud / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double radioTierraKm = 6378.0;
        double distancia = radioTierraKm * c;

        return distancia;
    }

    public static Double redondear(Double x) {
        return Math.round(x * 100) / 100.0;
    }

}
