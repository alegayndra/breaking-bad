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

    private int direction;
    private int width;      // to know the width of the player
    private int height;     // to know the height of the player
    private Game game;      // to be able to use the KeyManager
    private int speedX;     // to store the speed the player travels in the x axis
    private int speedY;     // to store the speed the player travels in the y axis
    private int collided;    // to know if the player collided with the edges of the screen and how many time it still needs to be drawn with a different image
    
    /**
     * to create direction, width, height, and game and set the player is not moving and has not collided
     * @param x to set the x of the player
     * @param y to set the y of the player
     * @param direction to set the direction of the player
     * @param width to set the width of the window
     * @param height  to set the height of the window
     * @param game to set the game of the player
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speedX = 0;
        this.speedY = 0;
        this.collided = 0;
    }

    /**
     * To get the direction of the player
     * @return an <code>int</code> value with the direction
     */
    public int getDirection() {
        return direction;
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
     * To get the quantity of frames it still needs to be treated as collided 
     * @return an <code>int</code> value with the quantity of frames 
     */
    public int getCollided() {
        return collided;
    }

    /**
     * To set the direction of the player
     * @param direction to set the direction of the player 
     */
    public void setDirection(int direction) {
        this.direction = direction;
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
     * To set the quantity of frames the player needs to be treated as collided
     * @param collided to set the quantity of frames
     */
    public void setCollided(int collided) {
        this.collided = collided;
    }

   public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersecta(Object obj) {
        return ((obj instanceof Enemy) && (getPerimetro().intersects(((Enemy) obj).getPerimetro())));
     }
    
    @Override
    public void tick() {
        /*
        // moving player depending on flags
        if (game.getKeyManager().up) {
           setY(getY() - getSpeedY());
        }
        if (game.getKeyManager().down) {
           setY(getY() + getSpeedY());
        }
        if (game.getKeyManager().left) {
           setX(getX() - getSpeedX());
        }
        if (game.getKeyManager().right) {
           setX(getX() + getSpeedX());
        }
        }*/
//        if (game.getMouseManager().isIzquierdo() && getPerimetro().contains(game.getMouseManager().getX(), game.getMouseManager().getY())) {
//            this.setX(game.getMouseManager().getX() - (getHeight() / 2));
//            this.setY(game.getMouseManager().getY() - (getHeight() / 2));
//            game.getMouseManager().setIzquierdo(false);                  
//        }
        /*
        if (game.getKeyManager().increaseUp) {
            if (getSpeedX() != 0)  {
                setSpeedY(getSpeedX());
                if(getSpeedY() > 0) {
                    setSpeedY(getSpeedY() * -1);
                }
                setSpeedX(0);
            } else {
                setSpeedY(getSpeedY()-1);
                if(getSpeedY() ==  0) {
                    setSpeedY(-1);
                }
            }
        }
        if (game.getKeyManager().increaseDown) {
            if (getSpeedX() != 0)  {
                setSpeedY(getSpeedX());
                if(getSpeedY() <  0) {
                    setSpeedY(getSpeedY() * -1);
                }
                setSpeedX(0);
            } else {
                setSpeedY(getSpeedY()+1);
                if(getSpeedY() == 0) {
                    setSpeedY(1);
                }
            }
        }
        if (game.getKeyManager().increaseLeft) {
            if (getSpeedY() != 0) {
                setSpeedX(getSpeedY());
                if (getSpeedX() > 0) {
                    setSpeedX(getSpeedX() * -1);
                }
                setSpeedY(0);
            } else {
                setSpeedX(getSpeedX()-1);
                if(getSpeedX() == 0) {
                    setSpeedX(-1);
                }
            }
        }
        if (game.getKeyManager().increaseRight) {
             if (getSpeedY() != 0) {
                setSpeedX(getSpeedY());
                if (getSpeedX() < 0) {
                    setSpeedX(getSpeedX() * -1);
                }
                setSpeedY(0);
            } else {
                setSpeedX(getSpeedX()+1);
                if(getSpeedX() == 0) {
                    setSpeedX(1);
                }
            }
        }
        */
        
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
        
        // if colision the speed gets inverted
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - this.getWidth());
            /*
            setSpeedX(getSpeedX() * -1);
            setCollided(25);
            */
        }
        else if (getX() <= -30) {
            setX(0);
            /*
            setSpeedX(getSpeedX() * -1);
            setCollided(25);
            */
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - this.getHeight());
            /*
            setSpeedY(getSpeedY() * -1);
            setCollided(25);
            */
        }
        else if (getY() <= -20) {
            setY(0);
            /*
            setSpeedY(getSpeedY() * -1);
            setCollided(25);
            */
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.paddle, getX(), getY(), getWidth(), getHeight(), null);
    }
}
