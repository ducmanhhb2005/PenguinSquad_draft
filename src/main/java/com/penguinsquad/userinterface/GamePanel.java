package com.penguinsquad.userinterface;
import com.penguinsquad.effect.Animation;
import com.penguinsquad.effect.CacheDataLoader;
import com.penguinsquad.effect.FrameImage;
import com.penguinsquad.object.GameWorld;
import com.penguinsquad.object.MegaMan;
import com.penguinsquad.object.PhysicalMap;

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
    private BufferedImage bufImage;
    private Graphics2D  bufG2D;
    public GameWorld gameWorld;
   // FrameImage frame1;
    
  //  Animation anim1;
    //MegaMan megaman = new MegaMan(300,300,100,100,0.1f);
    //PhysicalMap physicalMap = new PhysicalMap(0,0);
    public GamePanel(){
    	gameWorld = new GameWorld();   	
        inputManager = new InputManager(gameWorld);
        //frame1 = CacheDataLoader.getInstance().getFrameImage("idle?");
       // anim1 = CacheDataLoader.getInstance().getAnimation("idle??");
       // anim1.flipAllImage(); //quay nguoc anh
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
       
    }
    
    @Override
    public void paint(Graphics g) {
       g.drawImage(bufImage,0 ,0 ,this);
        
       // Graphics2D g2 = (Graphics2D) g;
     //   frame1.draw(g2, 130, 130);
       // anim1.draw(300,300,g2);
    }
    
    public void UpdateGame() {
    	gameWorld.Update();
    	//megaman.update();
    }
    public void RenderGame() {
    	if (bufImage == null) bufImage =  new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    if (bufImage != null) bufG2D = (Graphics2D) bufImage.getGraphics();
    if (bufG2D != null) {  bufG2D.setColor(Color.BLACK);
    bufG2D.fillRect(0 , 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
    // ve objects game
    //megaman.draw(bufG2D);
   // physicalMap.draw(bufG2D);
    gameWorld.Render(bufG2D);
    //bufG2D.setColor(Color.red);
    //bufG2D.fillRect(40, 50, 100, 100);	
    }
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
                       
                UpdateGame();
                RenderGame();
         //   anim1.Update(System.nanoTime());
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
