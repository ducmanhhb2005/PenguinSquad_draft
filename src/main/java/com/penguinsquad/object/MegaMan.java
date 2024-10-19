package com.penguinsquad.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MegaMan {
 private float posX;
 private float posY;
 
 private float width;
 private float height;
 private float mass;
 private float speedX;
 private float speedY;
 public static int DIR_LEFT;
 public static int DIR_RIGHT;
 
 private int direction;
 GameWorld gameWorld;
public MegaMan(float posX, float posY, float width, float height, float mass, GameWorld gameWorld) {
	this.posX = posX;
	this.posY = posY;
	this.width = width;
	this.height = height;
	this.mass = mass;
	this.gameWorld = gameWorld;
}
public Rectangle getBoundForCollisionWithMap() { //
	Rectangle bound = new Rectangle();
	bound.x = (int) (getPosX()-(getWidth()/2));
	bound.y = (int) (getPosY()-(getHeight())/2);
	bound.width = (int) getWidth();
	bound.height = (int) getHeight();
	return bound;
}
public void draw (Graphics2D g2) {
	g2.setColor(Color.red);
	g2.fillRect((int) (posX - getWidth()/2), (int) (posY - getHeight()/2) , (int) width, (int) height);
	g2.setColor(Color.BLACK);
	g2.fillRect((int) posX, (int) posY, 2, 2);
}
// ve o vuong do co cham den ơ giua
public void update() {
	setPosX(getPosX()+speedX);
    Rectangle futureRect = getBoundForCollisionWithMap() ;
    futureRect.y += getSpeedY();
    Rectangle rectLand = gameWorld.physicalMap.haveCollisionWithLand(futureRect);
	if (rectLand!= null) setPosY(rectLand.y -getHeight()/2);
	else {	setPosY(getPosY()+speedY);
	setSpeedY (getSpeedY() + mass);
	}
	}

public void setSpeedY(float f) {
	this.speedY = f;
	
}
private float getSpeedY() {
	// TODO Auto-generated method stub
	return this.speedY;
}
public float getPosX() {
	return posX;
}
public void setPosX(float posX) {
	this.posX = posX;
}
public float getPosY() {
	return posY;
}
public void setPosY(float posY) {
	this.posY = posY;
}
public float getWidth() {
	return width;
}
public void setWidth(float width) {
	this.width = width;
}
public float getHeight() {
	return height;
}
public void setHeight(float height) {
	this.height = height;
}
public float getMass() {
	return mass;
}
public void setMass(float mass) {
	this.mass = mass;
}
 //Game Panel hr lam
public void setSpeedX(int keyCode) {
	// TODO Auto-generated method stub
	this.speedX = keyCode;
	
}
public void setDirection(int keyCode) {
	// TODO Auto-generated method stub
	this.direction = keyCode;
}

}

