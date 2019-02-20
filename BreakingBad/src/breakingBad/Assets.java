/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage colision; // to store the player image when it crashes with the edges of the screen
    public static BufferedImage endGame; // to store image for when the game ends
    public static SoundClip bomb;           // to store the sound of the bomb

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/sky.png");
        player = ImageLoader.loadImage("/images/star.png");
        colision = ImageLoader.loadImage("/images/photo.png");
        endGame = ImageLoader.loadImage("/images/game over bueno.png");
        bomb = new SoundClip("/images/mono.wav");        
    }
    
}


