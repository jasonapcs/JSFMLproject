package mainpackage;

import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
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
		Sprite Bigfoot_front;
		
		// load up things, etc.
		try {
			BigfootFrontTexture.loadFromFile(Paths.get("resources/bigfoot_front.png"));
			Bigfoot_front = new Sprite(BigfootFrontTexture);
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
				if (event.type == Event.Type.CLOSED) 
					app.close();
			}
			
			// Drawing and things
			
			app.clear(Color.WHITE);
			
			// Draw things
			app.draw(Bigfoot_front);
			
			
			app.display();
		}
		
		scanner.nextLine();
	}
}
