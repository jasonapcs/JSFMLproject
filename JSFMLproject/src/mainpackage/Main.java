package mainpackage;

import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

public class Main {

	public static RenderWindow app;

	
	public static void main(String[] args) {

		app = new RenderWindow(VideoMode.getDesktopMode(), "JSFML project");
		
		app.setFramerateLimit(60);
		
		// load up things, etc.
		

		
		// Main Loop
		while (app.isOpen()){
			
			// Event processing
			for (Event event : app.pollEvents()){
				if (event.type == Event.Type.CLOSED) 
					app.close();
			}
			
			// Drawing and things
			
			app.clear(Color.RED);
			
			// Draw things
			
			app.display();
		}
	}

}
