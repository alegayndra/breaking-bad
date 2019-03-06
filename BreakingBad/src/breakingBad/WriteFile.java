/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.io.FileWriter;

/**
 *
 * @author betin
 */
public class WriteFile {
    private Game game;
    
    public WriteFile(Game game) {
        this.game = game;
    }
    
    public static void writeFile() {

        String[] lineas = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "..." };
        String palabra;

        /** FORMA 1 DE ESCRITURA **/
        FileWriter file = null;
        try {

            file = new FileWriter("archivo.txt");

            // Escribimos linea a linea en el fichero
            for (String linea : lineas) {
                file.write(linea + "\n");
            }
            
            for (int i = 0; i < game.getBricks.size(); i++) {
                if (getBricks().get(i).isDestroyed() ) {
                    file.write("1\n");
                } else {
                    file.write("0\n");
                }
                
            }

            file.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }
}
