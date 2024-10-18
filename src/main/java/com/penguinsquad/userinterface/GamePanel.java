package com.penguinsquad.userinterface;
import com.penguinsquad.effect.Animation;
import com.penguinsquad.effect.FrameImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    private Thread thread;    
    
    private boolean isRunning;
    
    private InputManager inputManager;
        
    public GamePanel(){
        inputManager = new InputManager();
                    
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0 , 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
                
    }
    
    public void startGame(){
        if(thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    @Override
    public void run() {
       
        long FPS = 80;
        long period = 1000*1000000/FPS;
        long beginTime;
        long sleepTime;
                     
        beginTime = System.nanoTime();
        
        while(isRunning){
                       
                //Update
                //Render
                
            repaint(); 
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            try {
                if(sleepTime>0)
                    Thread.sleep(sleepTime/1000000);
                else Thread.sleep(period/2000000);
            } catch (InterruptedException ex) {}
            
            beginTime = System.nanoTime();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { //callback method
        inputManager.processKeyPressed(e.getKeyCode());
        }
    
    @Override
    public void keyReleased(KeyEvent e) {
            inputManager.processKeyReleased(e.getKeyCode());
    
    }
}

