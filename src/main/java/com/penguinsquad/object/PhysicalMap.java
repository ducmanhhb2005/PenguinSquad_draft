package com.penguinsquad.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.penguinsquad.effect.CacheDataLoader;

public class PhysicalMap {
    public int[][] phys_map; // gom cac o vuong
    private int titleSize; // do lon canh hinh vuong
    public float posX , posY;
    public PhysicalMap(float x, float y) { // toa do
        this.posX = x;
        this.posY = y;
    	this.titleSize = 30;
    	phys_map =  ((CacheDataLoader) CacheDataLoader.getInstance()).getPhysicalMap(); //???
    	
    }
    //Ham ve Physical Map
    public void draw (Graphics2D g2) {
    	g2.setColor(Color.GRAY);
    	for (int i = 0; i< phys_map.length; i++) {
    		for (int j = 0; j< phys_map[0].length; j++) {
    			if (phys_map[i][j]!=0) g2.fillRect((int) posX + j*titleSize, (int) posY + i*titleSize, titleSize, titleSize);  			
    		}
    	}
    }
    //Ve luoi cac o vuong co mau xam
    //Kiem tra xem moi diem trong ma tran co va cham voi rect khong
     public Rectangle haveCollisionWithLand(Rectangle rect) { //rect co cham thang hinh vuong nao hay khong
    	 
    	 int posX1 = rect.x/titleSize; // toa do cua diem canh theo don vi la titleSize
    	 posX1 -= 2;
    	 int posX2 = (rect.x + rect.width)/titleSize; // toa diem diem canh mo rong theo chieu ben phai
    	 posX2 += 2;
    	 int posY = (rect.y + rect.height)/titleSize; // tọa do theo chieu doc
    	 if (posX1 < 0) posX1 = 0;
    	 if (posX2 >= phys_map[0].length) posX2 = phys_map[0].length-1;
    	 for (int y = posY; y< phys_map.length; y++) {
    		 for (int x = posX1; x<= posX2; x++) {
    			 if (phys_map[y][x] == 1) {
    				 Rectangle r = new Rectangle((int) posX + x* titleSize, (int) posY + y*titleSize, titleSize, titleSize );
    				 if (rect.intersects(r)) return r;
    			 }
    		 }
    	 }
    	 return null;
     }
    //Graphic la mot cay viet ve len JPanle, JFrame 
}

