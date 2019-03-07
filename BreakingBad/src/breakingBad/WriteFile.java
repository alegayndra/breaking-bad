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
//    private Game game;
//    
//    public WriteFile(Game game) {
//        this.game = game;
//    }
    
    public void writeFile(Game game) {

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

            file = new FileWriter("src/breakingbad/archivo.txt");
            
            //player
//            file.write(game.getPlayer().getX() + "\n" + game.getPlayer().getWidth() + "\n" + game.getPlayer().getX()  + "\n");
            file.write(game.getPlayer().getX() + "\n");
            file.write(game.getPlayer().getWidth() + "\n");
            file.write(game.getPlayer().getHeight()  + "\n");

            System.out.println(game.getPlayer().getX() + "\n" + game.getPlayer().getWidth() + "\n" + game.getPlayer().getHeight()  + "\n");

            //bricks
            for (int i = 0; i < game.getBricks().size(); i++) {
                if (game.getBricks().get(i).isDestroyed() ) {
                    file.write("1\n");
                } else {
                    file.write("0\n");
                }
                System.out.println(""+ game.getBricks().get(i).isDestroyed());
            }

            //ball
            file.write(game.getBall().getX() + "\n" + game.getBall().getY() + "\n" + game.getBall().getWidth() + "\n" + game.getBall().getHeight() + "\n" + game.getBall().getDirectionX() + "\n" + game.getBall().getDirectionY() + "\n");
            System.out.println(game.getBall().getX() + "\n" + game.getBall().getY() + "\n" + game.getBall().getWidth() + "\n" + game.getBall().getHeight() + "\n" + game.getBall().getDirectionX() + "\n" + game.getBall().getDirectionY() + "\n");
            
            //powerUps
            for (int i = 0; i < game.getPowerUps().size(); i++) {
                PowerUps power = game.getPowerUps().get(i);
                file.write(power.getX() + "\n" + power.getY() + "\n" + power.getWidth() + "\n" + power.getHeight() + "\n" + power.getType()  + "\n");
                System.out.println(power.getX() + " " + power.getY() + " " + power.getWidth() + " " + power.getHeight() + " " + power.getType() + " " + "\n");
            }
            
                //pollos
                for (int i = 0; i < game.getPollos().size(); i++) {
                    PowerUps power = game.getPollos().get(i);
                    file.write(power.getX() + "\n" + power.getY() + "\n" + power.getWidth() + "\n" + power.getHeight() + "\n" + power.getType()  + "\n");
                    System.out.println(power.getX() + " " + power.getY() + " " + power.getWidth() + " " + power.getHeight() + " " + power.getType() + " " + "\n");
                }
            
            //game
            file.write(game.getScore() + "\n" + game.getLives() + "\n" + (game.isEndGame() ? "1" : "0") + "\n");
            System.out.println(game.getScore() + " " + game.getLives() + " " + (game.isEndGame() ? "1" : "0") + "\n");
            
            file.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }
}
