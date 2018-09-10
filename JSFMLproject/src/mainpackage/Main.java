package mainpackage;

import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

import java.util.Scanner;
import java.math.*;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	
	public static RenderWindow app;
	
	
	public static void main(String[] args) {
		
		String title;
		
		switch ((int)((Math.random() * 100) % 6)){
		case 0:
			title = "The Adventures of Bigfoot";
			break;
		case 1:
			title = "BIGFOOT SMASH!";
			break;
		case 2:
			title = "Hello, Bigfoot";
			break;
		case 3:
			title = "Fun times with Mr. Bigfoot";
			break;
		case 4:
			title = "Run bigfoot, run!";
			break;
		case 5:
			title = "Furry bigfoot mania";
			break;
		default:
			title = "BIGFOOT SMASH!";
			break;
		}
		
		app = new RenderWindow(VideoMode.getDesktopMode(), title);
		
		app.setFramerateLimit(60);
		
		// Declare textures and sprites
		Texture BigfootFrontTexture = new Texture();
		Texture BigfootBackTexture = new Texture();
		Sprite bigfootSprite;
		
		boolean Bigfootfront = true;
		
		// load up things, etc.
		try {
			BigfootFrontTexture.loadFromFile(Paths.get("resources/bigfoot_front.png"));
			BigfootBackTexture.loadFromFile(Paths.get("resources/bigfoot_back.png"));
			bigfootSprite = new Sprite(BigfootFrontTexture);
			bigfootSprite.setOrigin(60.f, 65.f);
			bigfootSprite.setPosition(100.f, 100.f);
		}
		catch (IOException e){
			e.printStackTrace();
			app.close();
			scanner.nextLine();
			return;
		}
		
		// Main Loop
		while (app.isOpen()){
			
			// Event processing
			for (Event event : app.pollEvents()){
				switch (event.type) {
				case CLOSED:
					app.close();
					break;
				
				case KEY_PRESSED:
					KeyEvent keyEvent = event.asKeyEvent();
					
					Vector2f Spritepos = bigfootSprite.getPosition();
					
					float Spriterot = bigfootSprite.getRotation();
					
					switch (keyEvent.key) {
					case ESCAPE:
						if (Bigfootfront) {
							bigfootSprite.setTexture(BigfootBackTexture);
							Bigfootfront = false;
						}
						else {
							bigfootSprite.setTexture(BigfootFrontTexture);
							Bigfootfront = true;
						}
						break;
					
					case W:
						if (keyEvent.shift) {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x, Spritepos.y - 50));
						}
						else {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x, Spritepos.y - 5));
						}
						break;
						
					case A:
						if (keyEvent.shift) {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x - 50, Spritepos.y));
						}
						else {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x - 5, Spritepos.y));
						}
						break;
						
					case S:
						if (keyEvent.shift) {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x, Spritepos.y + 50));
						}
						else {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x, Spritepos.y + 5));
						}
						break;
						
					case D:
						if (keyEvent.shift) {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x + 50, Spritepos.y));
						}
						else {
							bigfootSprite.setPosition(new Vector2f(Spritepos.x + 5, Spritepos.y));
						}
						break;
						
					case RIGHT:
						if (keyEvent.shift) {
							bigfootSprite.setRotation(Spriterot + 10);
						}
						else {
							bigfootSprite.setRotation(Spriterot + 1);
						}
						break;
							
					case LEFT:
						if (keyEvent.shift) {
							bigfootSprite.setRotation(Spriterot - 10);
						}
						else {
							bigfootSprite.setRotation(Spriterot - 1);
						}
						break;
					
					}
					
				}
			}
			
			// Drawing and things
			
			app.clear(Color.WHITE);
			
			// Draw things
			app.draw(bigfootSprite);
			
			
			app.display();
		}
	}
}
