package myProject.Utils;

import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

import myProject.Model.Jugador;

/**
 * This class is used for reading and writing of the avalaible words that are
 * inside the .txt file that
 * are saved in an ArrayList to take random words from this.
 * 
 * @autor William Velasco - 2042577 william.velasco@correounivalle.edu.co
 * @version v.1.0.0 date:08/02/2022
 */
public class FileManager {
    public static final String PATHDICCIONARIO = "src/myProject/files/diccionario.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    /* Singleton Pattern */
    private static FileManager instance;

    private FileManager() {
    }

    /* Instancia para FileManager */
    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    /**
     * @bref Toma las palabras del diccionario en un ArrayList
     * @return
     */
    public ArrayList<String> lecturaFile() {
        ArrayList<String> palabrasTomadas = new ArrayList<String>();

        try {
            fileReader = new FileReader(PATHDICCIONARIO);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                palabrasTomadas.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palabrasTomadas;
    }

    /**
     * @bref HashMap con la primera posición del nombre, segunda posición el nivel del jugador
     * @return
     */
    public HashMap<String, Integer> getPlayers() {
        HashMap<String, Integer> Players = new HashMap<>();
        try {
            fileReader = new FileReader(Jugador.PATHJUGADORES);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                String parts[] = line.split(" ");
                Players.put(parts[0], Integer.parseInt(parts[1]));
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Players;
    }

    /**
     * Obtener la lista de jugadores para no escribir usuarios repetidos en el archivo .txt
     * @param userRemove
     * @return
     */
    public ArrayList<String[]> getPlayersList(String userRemove) {
        ArrayList<String[]>  Players = new ArrayList<>();
        try {
            fileReader = new FileReader(Jugador.PATHJUGADORES);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                String parts[] = line.split(" ");
                if(parts[0].equals(userRemove)){
                    
                }else{
                    Players.add(parts);
                }
                line = input.readLine();
            }
            /*Borrar Contenido*/
            PrintWriter writer = new PrintWriter(Jugador.PATHJUGADORES);
            writer.println("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Players;
    }

    /**
     * @braf Guarda el usuario en el archivo .txt siempre y cuando no exista, en caso de existir lo actualiza
     * @param nivel
     * @param nombre
     * @param existeUsuario
     */
    public void saveUser(int nivel, String nombre, boolean existeUsuario) {
        try {
            if (!existeUsuario) {
                if (!nombre.equals(" ") && nombre != null) {
                    fileWriter = new FileWriter(Jugador.PATHJUGADORES, true);
                    output = new BufferedWriter(fileWriter);
                    output.write(nombre + " " + nivel);
                    output.newLine();
                }
            } else {
                //Actualizar
                fileWriter = new FileWriter(Jugador.PATHJUGADORES, true);
                output = new BufferedWriter(fileWriter);
                ArrayList<String[]> playersList = getPlayersList(nombre);
                for(String [] s : playersList){
                    if(s[0].equals(nombre)){
                        s[1] = String.valueOf(nivel);
                    }
                    output.write(s[0] + " " + s[1]);
                    output.newLine();
                }
                output.write(nombre + " " + nivel);
                output.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException n) {
                
            }
        }

    }

    /**
     * Verifica si el nombre del usuario ya existe
     * @param userName
     * @return boolean
     */
    public boolean existeUserName(String userName) {
        try {
            fileReader = new FileReader(Jugador.PATHJUGADORES);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                String parts[] = line.split(" ");
                if (userName.equals(parts[0])) {
                    return true;
                }
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
