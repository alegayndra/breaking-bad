/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage paddle;     // to store the player image
    public static BufferedImage pollos;
    public static BufferedImage blueFlask;
    public static BufferedImage greenFlask;
    public static BufferedImage ball;
    public static BufferedImage brick;
    //public static SoundClip bomb;           // to store the sound of the bomb
    
    //to store flask color
    public static BufferedImage colorF;
    public static BufferedImage colors[];
 
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/desert.jpg");
        paddle = ImageLoader.loadImage("/images/paddle.png");
        pollos = ImageLoader.loadImage("/images/breakingbad-lospolloshermanosrug.png");
        blueFlask = ImageLoader.loadImage("/images/flask2.png");
        greenFlask = ImageLoader.loadImage("/images/flask1.png");
        ball = ImageLoader.loadImage("/images/ball.png");
        brick = ImageLoader.loadImage("/images/brick.png");
        //bomb = new SoundClip("/images/mono.wav");
        colorF = ImageLoader.loadImage("/images/flask_spritesheet.jpg");
        
        //creating array of images
        SpreadSheet spritesheet = new SpreadSheet(colorF);
        colors = new BufferedImage[3];
        
        
        
    }
    
}


