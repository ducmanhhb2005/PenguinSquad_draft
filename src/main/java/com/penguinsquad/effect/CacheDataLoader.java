
package com.penguinsquad.effect;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class CacheDataLoader {
    private static CacheDataLoader instance; //Object duy nhat cua lop CacheDataLoader
	
    private String framefile = "file.txt"; // file nao do
    private String animationfile = "animation.txt";
    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;
    private CacheDataLoader() {
    	
    	
    }
    
    public static CacheDataLoader getInstance() {
    	if (instance == null) instance = new CacheDataLoader();
    	return instance;
    }
     
    public void LoadData() throws IOException{
    	LoadFrame();
    	LoadAnimation();
    	LoadPhysMap();
    }
    //@SuppressWarnings("resource")
	public void LoadFrame() throws IOException{
    	frameImages = new Hashtable<String, FrameImage>();
    	FileReader fr = new FileReader(framefile); //file gi do o day
    	BufferedReader br = new BufferedReader(fr); //doc
    	String line = null; // doc tung dong
    	if (br.readLine() == null) { //tra ve mot String, con tro tu dong den dong tiep theo
    		System.out.print("No data");
    		throw new IOException();
    		}
    	else {
    		fr = new FileReader(framefile); // dua tro lai dau file
    		br = new BufferedReader(fr);
    		while ((line = br.readLine()).equals("")); // readline: tu dong next dong vi doc tung khoi
    		int n = Integer.parseInt(line);
    		for (int i=0; i < n; i++) {
    			FrameImage frame = new FrameImage();
    			while ((line = br.readLine()).equals(""));
    			frame.setName(line);
    			while ((line = br.readLine()).equals(""));
    			String[] str = line.split(" ");
    			String path = str[1];
    			
    			while ((line = br.readLine()).equals(""));
    			str = line.split(" ");
    			int x = Integer.parseInt(str[1]);
    			
    			while ((line = br.readLine()).equals(""));
    			str = line.split(" ");
    			int y = Integer.parseInt(str[1]);
    			
    			while ((line = br.readLine()).equals(""));
    			str = line.split(" ");
    	        int w = Integer.parseInt(str[1]);
    	        
    			while ((line = br.readLine()).equals(""));
    			str = line.split(" ");
    			int h = Integer.parseInt(str[1]);
    			 BufferedImage imageData = ImageIO.read((new File(path)));
    			 BufferedImage image = imageData.getSubimage(x, y, w, h);
    			 frame.setImage(image);
    			 instance.frameImages.put(frame.getName(), frame);
    		}
    	}
    	br.close();
    }
    
    public FrameImage getFrameImage(String name) {
    	FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
    	return frameImage;
    }
    public void LoadAnimation() throws IOException {
    	animations = new Hashtable<String, Animation>();
    	FileReader fr = new FileReader(animation file); //file nao day
    	BufferedReader br = new BufferedReader(fr); //doc
    	String line = null; // doc tung dong
    	if (br.readLine() == null) { //tra ve mot String, con tro tu dong den dong tiep theo
    		System.out.print("No data");
    		throw new IOException();
    	}
    	else {
    		fr = new FileReader(animationfile); // dua tro lai dau file
    		br = new BufferedReader(fr);
    		while ((line = br.readLine()).equals("")); // readline: tu dong next dong vi doc tung khoi
    		int n = Integer.parseInt(line);
    		for (int i=0; i < n; i++) {
    			Animation animation = new Animation();
    			while ((line = br.readLine()).equals(""));
    			animation.setName(line);
    			while ((line = br.readLine()).equals(""));
    			String[] str = line.split(" ");
    			
    			for (int j = 0; j<str.length; j+=2) 
    				animation.add(getFrameImage(str[j]), Double.parseDouble(str[j+1]));
    			
    			 instance.animations.put(animation.getName(), animation);
    		}
    	}
    	br.close();
    }
    
    public Animation getAnimation(String name) {
    	Animation animation = new Animation(instance.animations.get(name));
    	return animation;
    }
	private int[][] phys_map; // map nhi phan
	public int[][] getPhysicalMap(){
		return instance.phys_map;
	}
	public void LoadPhysMap() throws IOException{ // co kha nang phat sinh ngoai le
		
		FileReader fr = new FileReader (???); //file me gi y // doc file truyen vao duong dan
		BufferedReader br = new BufferedReader(fr); // fr tra ve luong du lieu, buff dua tren luong vua doc de khai thac
		String line = null; // doc du lieu
		line = br.readLine();
		int numberOfRows = Integer.parseInt(line);
		line = br.readLine();
		int numberOfColumns = Integer.parseInt(line);
		
		instance.phys_map = new int[numberOfRows][numberOfColumns]; //instance ???? tong 
		
		for (int i = 0; i < numberOfRows; i++) {
			line = br.readLine();
			String[] str = line.split(" ");
			for (int j = 0; j < numberOfColumns; j++) {
				instance.phys_map[i][j] = Integer.parseInt(str[j]);
			}
			
		}
		
		br.close();
	}
	

}
// doc vao so hang, so cot va 
