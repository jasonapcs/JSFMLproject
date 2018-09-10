package mainpackage;

import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

import java.util.Scanner;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	
	public static RenderWindow app;
	
	
	public static void main(String[] args) {
		
		app = new RenderWindow(VideoMode.getDesktopMode(), "JSFML project");
		
		app.setFramerateLimit(60);
		
		// Declare textures and sprites
		Texture BigfootFrontTexture = new Texture();
		Texture BigfootBackTexture = new Texture();
		Sprite bigfootSprite;
		
		// load up things, etc.
		try {
			BigfootFrontTexture.loadFromFile(Paths.get("resources/bigfoot_front.png"));
			BigfootBackTexture.loadFromFile(Paths.get("resources/bigfoot_back.png"));
			bigfootSprite = new Sprite(BigfootFrontTexture);
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
					case W:
						bigfootSprite.setPosition(new Vector2f(Spritepos.x, Spritepos.y - 5));
						break;
						
					case A:
						bigfootSprite.setPosition(new Vector2f(Spritepos.x - 5, Spritepos.y));
						break;
						
					case S:
						bigfootSprite.setPosition(new Vector2f(Spritepos.x, Spritepos.y + 5));
						break;
						
					case D:
						bigfootSprite.setPosition(new Vector2f(Spritepos.x + 5, Spritepos.y));
						break;
						
					case RIGHT:
						bigfootSprite.setRotation(Spriterot + 1);
						break;
							
					case LEFT:
						bigfootSprite.setRotation(Spriterot - 1);
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
