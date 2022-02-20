package myProject.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import myProject.Utils.FileManager;

/**
 * This class is used for take the set of words that are used for the develompment of the game
 * @autor William Velasco - 2042577 william.velasco@correounivalle.edu.co
 * @version v.1.0.0 date:08/02/2022
 */
public class Diccionario{

    private ArrayList<String> diccionario = new ArrayList<String>();

    /**
     * @bref Constructor de la clase
     */
    public Diccionario() {
        FileManager fileManager = FileManager.getInstance();
        diccionario = fileManager.lecturaFile();
    }

    /**
     * @bref Toma una palabra al azar del ArrayList diccionario
     */
    public String getPalabra() {
        Random aleatorio = new Random();
        return diccionario.get(aleatorio.nextInt(diccionario.size()));
    }

    /**
     * Desordena la lista que se le mostrará al usuario en la fase de interacción
     * @param listaOrdenada
     * @return
     */
    public ArrayList<String> getRandomListMostrar(ArrayList<String> listaOrdenada){
        Collections.shuffle(listaOrdenada);
        return listaOrdenada;
    }

}
