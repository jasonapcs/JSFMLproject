package mainpackage;

import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

import java.math.*;

public class Main {
	
	public static RenderWindow app;
	
	public static View appview = new View();
	
	public static void main(String[] args) {
		
		final int windowWidth = 1000;
		final int windowHeight = 784;
		
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
		
		app = new RenderWindow(new VideoMode(windowWidth, windowHeight), title);
		
		app.setFramerateLimit(60);
		
		appview = (View) app.getDefaultView();
		
		app.setView(appview);
		
		// Declare textures and sprites
		Player player;
		Enemy arrEnemy[] = new Enemy[4];
		
		
		Texture backgroundImageTexture = new Texture();
		Sprite backgroundImage;
		
		Texture rubberDuckTexture = new Texture();
		Sprite rubberDuck;

		
		// load up things, etc.
		try {
			player = new Player("resources/bigfoot_back.png", "resources/bigfoot_front.png");
			
			for (int i = 0; i < arrEnemy.length; i++) {
				arrEnemy[i] = new Enemy("resources/bigfoot_zombie_front.png", 300.f + 150.f * i, 300.f);
			}
			
			backgroundImageTexture.loadFromFile(Paths.get("resources/battlemapdesert.png"));
			backgroundImage = new Sprite(backgroundImageTexture);
			
			rubberDuckTexture.loadFromFile(Paths.get("resources/Rubber_duck.png"));
			rubberDuck = new Sprite(rubberDuckTexture);
		}
		catch (IOException e){
			e.printStackTrace();
			app.close();
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
				
				case RESIZED:
					appview.setSize(
							new Vector2f(event.asSizeEvent().size.x, event.asSizeEvent().size.y));
					app.setView(appview);
					break;
					
				case KEY_PRESSED:
					KeyEvent keyEvent = event.asKeyEvent();
					
					switch (keyEvent.key) {
					case F1:
						RenderWindow duckWindow = new RenderWindow(new VideoMode(1006, 1092), "DUCK", WindowStyle.NONE);
						duckWindow.clear();
						duckWindow.draw(rubberDuck);
						duckWindow.display();
						Event tempe;
						boolean tempb = true;
						do {
							tempe = duckWindow.waitEvent();
							if (tempe.type == Event.Type.KEY_PRESSED) {
								tempb = false;
							}
						}
						while (tempb);
						duckWindow.close();
						break;
					
					case ESCAPE:
						player.toggleFacingDir();
						break;
					
					case W:
						if (keyEvent.shift) {
							player.changePos(0, -50);
						}
						else {
							player.changePos(0, -5);
						}
						break;
						
					case A:
						if (keyEvent.shift) {
							player.changePos(-50, 0);
						}
						else {
							player.changePos(-5, 0);
						}
						break;
						
					case S:
						if (keyEvent.shift) {
							player.changePos(0, 50);
						}
						else {
							player.changePos(0, 5);
						}
						break;
						
					case D:
						if (keyEvent.shift) {
							player.changePos(50, 0);
						}
						else {
							player.changePos(5, 0);
						}
						break;
						
					case RIGHT:
						if (keyEvent.shift) {
							player.changeRot(10);
						}
						else {
							player.changeRot(1);
						}
						break;
							
					case LEFT:
						if (keyEvent.shift) {
							player.changeRot(-10);
						}
						else {
							player.changeRot(-1);
						}
						break;
					
					}
					
				}
			}
			
			for (int i = 0; i < arrEnemy.length; i++) {
				if (player.isTouching(arrEnemy[i].enemySprite))
					player.loseHealth(1);
			}
			
			player.setSpriteColor(Color.WHITE);
			
			// Drawing and things
			
			app.clear(Color.WHITE);
			
			// Draw things
			app.draw(backgroundImage);
			
			for (int i = 0; i < arrEnemy.length; i++) {
				app.draw(arrEnemy[i]);
			}
			
			app.draw(player);
			
			
			app.display();
		}
	}
}
