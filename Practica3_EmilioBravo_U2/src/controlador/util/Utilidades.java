/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.util;

import controlador.lista.Exception.PositionException;
import controlador.lista.Exception.VacioException;
import controlador.lista.LinkedList;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bravo
 */
public class Utilidades {

    public static Field getField(Class clazz, String attribute) {
        Field[] fields = clazz.getFields();
        Field resp = null;

        for (Field f : fields) {
            if (attribute.equalsIgnoreCase(f.getName())) {
                resp = f;
            }
        }
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f : fields1) {
            if (attribute.equalsIgnoreCase(f.getName())) {
                resp = f;
            }
        }
        return resp;

    }

    public static Object getData(Object clas, String attribute) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class clazz = clas.getClass();
        Field f = getField(clazz, attribute);
        Object obj = null;

        if (f != null) {
            String auxAttribute = "get" + capitalize(attribute);
            Method method = null;
            for (Method m : clazz.getMethods()) {
                //System.out.println(m.getName());
                if (m.getName().equalsIgnoreCase(auxAttribute)) {
                    method = m;
                    break;
                }

            }
            for (Method m : clazz.getMethods()) {
                //System.out.println(m.getName());
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

    

}
