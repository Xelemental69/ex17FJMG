/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

/**
 *
 * @author fcoj
 */
import java.util.*;

public class Baraja {
    
    Map <Integer, ArrayList <Carta>> cartas;

    public Baraja() {
        ArrayList <Carta> cartasPorValor = new ArrayList<>();
        
        cartas = new TreeMap<>();
        
        for(int i = 2; i < 15; i++){
            
            for(int j = 0; j < 4; j++){
                
                cartasPorValor.add(new Carta (asignarPalo(j), i));
                
            }
            
            cartas.put(i, cartasPorValor);
            
            cartasPorValor = new ArrayList<>();
            
        }
        
    }    
    
    private Palos asignarPalo(int posicion){
        
        switch(posicion){
            
            case 0:
                return Palos.PICAS;
            
            case 1:
                return Palos.ROMBOS;
                
            case 2:
                return Palos.TREBOLES;
                
            default:
                return Palos.CORAZONES;
            
        }
        
    }

    public Map<Integer, ArrayList<Carta>> getCartas() {
        return cartas;
    }

    public void setCartas(Map<Integer, ArrayList<Carta>> cartas) {
        this.cartas = cartas;
    }
    
    private String getBarajaMap(){
        
        String devolver = "";
        
        for (Integer key : cartas.keySet()) {
            
            devolver += ("Fila " + key + ": " + cartas.get(key) + "\n");
            
        }
        
        return devolver;
        
    }

    @Override
    public String toString() {
        
        return "Baraja{\n" + "cartas=" + getBarajaMap() + '}';
        
    }
        
}
