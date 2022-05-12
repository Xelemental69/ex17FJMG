/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.util.*;

/**
 *
 * @author fcoj
 */
public class LeerPrecipitaciones {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapeador = new ObjectMapper();
        mapeador.registerModule(new JavaTimeModule());
        
        ArrayList<Precipitaciones> llovizna = mapeador.readValue(new File("precipitacionesBadajoz.json"),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class, Precipitaciones.class));
        System.out.println("---- Predicción ----");
        
        for (Precipitaciones precipitacion : llovizna) {
            
            System.out.println(precipitacion);
            
        }

    }

}
