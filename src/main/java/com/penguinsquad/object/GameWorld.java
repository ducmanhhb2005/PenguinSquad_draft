package com.penguinsquad.object;
import com.penguinsquad.object.MegaMan;
import java.awt.Graphics2D;
import com.penguinsquad.object.PhysicalMap;

public class GameWorld {
	public MegaMan megaman;
	public PhysicalMap physicalMap;
	public GameWorld() {
		 megaman = new MegaMan(300,300,100,100,0.1f, this);
	     physicalMap = new PhysicalMap(0,0);
	}
  public void Update() {
	  megaman.update();
  }
  public void Render(Graphics2D g2) {
	  megaman.draw(g2);
	  physicalMap.draw(g2);
	  
  }

}
