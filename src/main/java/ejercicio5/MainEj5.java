/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

/**
 *
 * @author pikac
 */
import ejercicio4.*;
import java.util.*;

public class MainEj5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        List<Precipitaciones> listaPrecipitaciones = LecturaJSON.leerJSON("./precipitacionesBadajoz.json", Precipitaciones.class);

        //Apartado A
        mapEstacionTemp(listaPrecipitaciones).forEach((k, v) -> System.out.println(k + "-> " + v));

        //Apartado B
        leidasDias1020(listaPrecipitaciones);

        //Apartado C
        double media = calcularMedia1020(listaPrecipitaciones);
        System.out.println(media);

    }

    private static Map<String, Double> mapEstacionTemp(List<Precipitaciones> precipitaciones) {

        Map<String, Double> mapEstacionTemp = new HashMap<>();
        precipitaciones.forEach(precipitacion -> {
            mapEstacionTemp.put(precipitacion.getEstacionMeteorologica(), precipitacion.getPrecipitacion());
        });

        return mapEstacionTemp;

    }

    private static void leidasDias1020(List<Precipitaciones> precipitaciones) {

        precipitaciones.stream()
                .filter(precipitacion -> precipitacion.getFecha().getDayOfMonth() >= 10 && precipitacion.getFecha().getDayOfMonth() <= 20)
                .forEach(System.out::println);

    }

    private static double calcularMedia1020(List<Precipitaciones> precipitaciones) {

        long numeroPrecipitaciones = precipitaciones.stream()
                .filter(precipitacion -> precipitacion.getFecha().getDayOfMonth() >= 10 && precipitacion.getFecha().getDayOfMonth() <= 20)
                .count();

        double sumaPrecipitaciones = precipitaciones.stream()
                .filter(precipitacion -> precipitacion.getFecha().getDayOfMonth() >= 10 && precipitacion.getFecha().getDayOfMonth() <= 20)
                .map(Precipitaciones::getPrecipitacion)
                .reduce(Double::sum).get();

        return sumaPrecipitaciones / numeroPrecipitaciones;

    }

}
