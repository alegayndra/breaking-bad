/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Set;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int width;      // to know the width of the player
    private int height;     // to know the height of the player
    private Game game;      // to be able to use the KeyManager
    private int speedX;     // to store the speed the player travels in the x axis
    private int speedY;     // to store the speed the player travels in the y axisprivate int collided;    // to know if the player collided with the edges of the screen and how many time it still needs to be drawn with a different image
    
    /**
     * to create direction, width, height, and game and set the player is not moving and has not collided
     * @param x to set the x of the player
     * @param y to set the y of the player
     * @param direction to set the direction of the player
     * @param width to set the width of the player
     * @param height  to set the height of the player
     * @param game to set the game of the player
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speedX = 0;
        this.speedY = 0;
    }

    /**
     * To get the width of the player
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the player
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * To get the speed of the player on the x axis
     * @return an <code>int</code> value with the speed on the axis
     */
    public int getSpeedX() {
        return speedX;
    }
    
    /**
     * To get the speed of the player on the y axis
     * @return an <code>int</code> value with the speed on the axis
     */
    public int getSpeedY() {
        return speedY;
    }
    
    /**
     * To set the width of the player
     * @param width to set the width of the player 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the player
     * @param height to set the height of the player 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * To set the speed of the player on the x axis
     * @param speedX to set the speed of the player on the axis
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    
    /**
     * To set the speed of the player on the y axis
     * @param speedY to set the speed of the player on the axis
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    
    /**
     * To get a rectangle with the position, width, and height of the player
     * @return an <code>Rectangle</code> rectangle with the given position, width, and height
     */
   public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    // Carga la información del objeto desde un string
   /**
     * To set the value of the position in the x axis and the width of the player from the file that was loaded
     * @param datos to set all the variables
     */
    public void loadFromString(String[] datos){
        this.x = Integer.parseInt(datos[0]);
        this.width = Integer.parseInt(datos[1]);
    }
    
    /**
     * To get all the variable that need to be stored in the file as a string
     * @return an <code>String</code> value with all the information of the variables
     */
    public String toString(){
        return (x+" "+width);
    }
    
    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().left) {
           setX(x -= 5);
        }
        if (game.getKeyManager().right) {
           setX(x += 5);
        }
        
        // checks that the object does not goes out of the bounds
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - this.getWidth());
        }
        else if (getX() <= -30) {
            setX(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.paddle, getX(), getY(), getWidth(), getHeight(), null);
    }
}
