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
    
    public void writeFile() {

        /** FORMA 1 DE ESCRITURA **/
        
        /*
        player
        bricks
        ball
        powerUps
        game
        */
        FileWriter file = null;
        try {

            file = new FileWriter("archivo.txt");
            
            //player
            file.write(game.getPlayer().getX() + " " + game.getPlayer().getY() + " " + game.getPlayer().getWidth() + " " + game.getPlayer().getX()  + "\n");

            //bricks
            for (int i = 0; i < game.getBricks().size(); i++) {
                if (game.getBricks().get(i).isDestroyed() ) {
                    file.write("1\n");
                } else {
                    file.write("0\n");
                }
            }

            //ball
            file.write(game.getBall().getX() + " " + game.getBall().getY() + " " + game.getBall().getWidth() + " " + game.getBall().getHeight() + " " + game.getBall().getDirectionX() + " " + game.getBall().getDirectionY() + " " + "\n");
            
            //powerUps
            for (int i = 0; i < game.getPowerUps().size(); i++) {
                PowerUps power = game.getPowerUps().get(i);
                file.write(power.getX() + " " + power.getY() + " " + power.getWidth() + " " + power.getHeight() + " " + power.getType() + " " + "\n");
            }
            
            //game
            file.write(game.getScore() + " " + game.getLives() + " " + (game.isEndGame() ? "1" : "0") + "\n");
            
            file.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }
}
