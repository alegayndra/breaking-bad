/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author betin
 */
public class ReadFile {
    private Game game;
    
    public ReadFile(Game game) {
        this.game = game;
    }
    
    public void readFile() {
        // Fichero del que queremos leer
        File file = new File("archivo.txt");
        Scanner s = null;
        
        int x, y, width, height, directionX, directionY, type, score, lives;
        
        /*
        player
        bricks
        ball
        powerUps
        game
        */
        try {
            // Leemos el contenido del fichero
            System.out.println("... Leemos el contenido del fichero ...");
            s = new Scanner(file);
            String linea = s.nextLine();
            
            //player
            x = Integer.parseInt(linea);
//            file.write(game.getPlayer().getX() + " " + game.getPlayer().getY() + " " + game.getPlayer().getWidth() + " " + game.getPlayer().getX()  + "\n");

            //bricks
            for (int i = 0; i < game.getBricks().size(); i++) {
                Enemy brick = game.getBricks().get(i);
                x = Integer.parseInt(s.nextLine());
                if (x == 1) {
                    brick.setDestroyed(true);
                } else {
                    brick.setDestroyed(false);
                }
            }

            //ball
//            file.write(game.getBall().getX() + " " + game.getBall().getY() + " " + game.getBall().getWidth() + " " + game.getBall().getHeight() + " " + game.getBall().getDirectionX() + " " + game.getBall().getDirectionY() + " " + "\n");
            
            //powerUps
//            for (int i = 0; i < game.getPowerUps().size(); i++) {
//                PowerUps power = game.getPowerUps().get(i);
//                file.write(power.getX() + " " + power.getY() + " " + power.getWidth() + " " + power.getHeight() + " " + power.getType() + " " + "\n");
//            }
            
            //game
//            file.write(game.getScore() + " " + game.getLives() + " " + (game.isEndGame() ? "1" : "0") + "\n");

            // Leemos linea a linea el fichero
            while (s.hasNextLine()) {
//                String linea = s.nextLine(); 	// Guardamos la linea en un String
                System.out.println(linea);      // Imprimimos la linea
            }

        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null)
                s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }	
    }
}
