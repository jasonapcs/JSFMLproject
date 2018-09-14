package mainpackage;

import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.*;

public class Player implements Drawable {
	
	private boolean facingBack = false;
	private Texture playerTextureBack;
	private Texture playerTextureFront;
	private Sprite playerSprite;
	public Vector2f pos;
	
	Player(String pathToBackTexture, String pathToFrontTexture) throws IOException {
		playerTextureBack = new Texture();
		playerTextureFront = new Texture();
		playerSprite = new Sprite();
		playerTextureBack.loadFromFile(Paths.get(pathToBackTexture));
		playerTextureFront.loadFromFile(Paths.get(pathToFrontTexture));
		playerSprite.setTexture(playerTextureFront);
		this.toggleFacingDir();
		playerSprite.setOrigin(60.f, 65.f);
		pos = new Vector2f(384.f, 400.f);
		playerSprite.setPosition(pos);
	}
	
	public void changeRot(float f) {
		playerSprite.setRotation(playerSprite.getRotation() + f);
	}
	
	public void setRot(float f) {
		playerSprite.setRotation(f);
	}
	
	public void setPos(float x, float y) {
		pos = new Vector2f(x, y);
		playerSprite.setPosition(pos);
	}
	
	public void changePos(float deltaX, float deltaY) {
		pos = new Vector2f(pos.x + deltaX, pos.y + deltaY);
		playerSprite.setPosition(pos);
	}
	
	public void toggleFacingDir() {
		if (facingBack) {
			playerSprite.setTexture(playerTextureFront);
			facingBack = false;
		}
		else {
			playerSprite.setTexture(playerTextureBack);
			facingBack = true;
		}
	}

	@Override
	public void draw(RenderTarget arg0, RenderStates arg1) {
		arg0.draw(playerSprite);
	}

}
