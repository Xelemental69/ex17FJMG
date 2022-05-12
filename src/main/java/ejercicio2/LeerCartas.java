/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2;

/**
 *
 * @author fcoj
 */
import java.util.*;
import ejercicio1.*;
import java.io.*;

public class LeerCartas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<ArrayList <Carta>> rondas = new ArrayList<>();
        
        // Fichero a leer:
        String idFichero = "escaleraColor.txt";

        // Variables para guardar cada carta:
        String[] tokens;
        String[] aux = new String[2];
        String linea;
        boolean tipoCarta = false;
        ArrayList <Carta> tmp = new ArrayList<>();
        int contador = 1; //Para mostrar las rondas al final

        System.out.println("Leyendo el fichero: " + idFichero);

        //Iniciamos el flujo...
        try (Scanner datosFichero = new Scanner(new File(idFichero), "us-ascii")) {
            
            while (datosFichero.hasNextLine()) {                                              
                
                linea = datosFichero.nextLine();
                
                tokens = linea.split(" ");
                
                for (String string : tokens) {
                    //System.out.print(string + " ");
                    
                    if(!tipoCarta){
                        
                        aux[0] = string;
                        tipoCarta = true;
                        
                    }else{
                        
                        aux[1] = string;
                        tmp.add(new Carta(leerPalo(aux[1]),
                                leerValor(aux[0])));
                        
                        tipoCarta = false;
                        
                    }
                                                        
                }
                //System.out.println();
                
                rondas.add(tmp);
                tmp = new ArrayList<>();
                
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for(ArrayList <Carta> r : rondas){
            
            System.out.printf("Ronda " + contador + ": ");
            for(Carta c : r){
                
                System.out.printf("%s, ", c);
                
            }
            
            System.out.println("");
            contador++;
            
        }
                
    }
    
    public static Palos leerPalo(String recipiente){
        
        switch(recipiente){
            
            case "P":
                return Palos.PICAS;
            
            case "D":
                return Palos.ROMBOS;//Rombos = Diamantes
                
            case "T":
                return Palos.TREBOLES;
                
            default:
                return Palos.CORAZONES;
            
        }
        
    }
    
    public static int leerValor(String recipiente){
        
        switch(recipiente){
            
            case "J":
                return 11;
            
            case "Q":
                return 12;
                
            case "K":
                return 13;
                
            case "A":
                return 14;
                
            default:
                return Integer.parseInt(recipiente);
            
        }
        
    }
    
}
