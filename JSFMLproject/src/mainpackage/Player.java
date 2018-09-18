package mainpackage;

import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.*;

import javax.swing.JOptionPane;

public class Player implements Drawable {
	
	private boolean facingBack = true;
	private Texture playerTextureBack;
	private Texture playerTextureFront;
	private Sprite playerSprite;
	public Vector2f pos;
	public int health;
	private RectangleShape HealthBar;
	private RectangleShape HealthView;
	
	Player(String pathToBackTexture, String pathToFrontTexture) throws IOException {
		playerTextureBack = new Texture();
		playerTextureFront = new Texture();
		playerSprite = new Sprite();
		playerTextureBack.loadFromFile(Paths.get(pathToBackTexture));
		playerTextureFront.loadFromFile(Paths.get(pathToFrontTexture));
		playerSprite.setTexture(playerTextureBack);
		playerSprite.setOrigin(60.f, 65.f);
		pos = new Vector2f(384.f, 400.f);
		playerSprite.setPosition(pos);
		health = 200;
		HealthBar = new RectangleShape();
		HealthBar.setPosition(pos.x - 60, pos.y - 100);
		HealthBar.setFillColor(new Color(22, 21, 19, 214));
		HealthBar.setSize(new Vector2f(120.f, 20.f));
		HealthView = new RectangleShape();
		HealthView.setPosition(pos.x - 60, pos.y - 100);
		HealthView.setFillColor(new Color(89, 244, 66));
		HealthView.setSize(new Vector2f(((float)health / 200.f) * 120.f, 15.f));
		//HealthBar.SetSubRect(new IntRect(0,0,(health/20*120),14));
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
		HealthBar.setPosition(pos.x - 60, pos.y - 100);
		HealthView.setPosition(pos.x - 60, pos.y - 100);
	}
	
	public void changePos(float deltaX, float deltaY) {
		pos = new Vector2f(pos.x + deltaX, pos.y + deltaY);
		playerSprite.setPosition(pos);
		HealthBar.setPosition(pos.x - 60, pos.y - 100);
		HealthView.setPosition(pos.x - 60, pos.y - 100);
		
		health--;
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
	
	private void die(RenderTarget arg0) {
		JOptionPane.showMessageDialog(null, "YOU DIED", 
				"YOU DIED", JOptionPane.OK_OPTION);
		health = 200;
		this.setPos(384.f, 400.f);
		this.setRot(0.f);
		if(!facingBack) toggleFacingDir();
	}

	@Override
	public void draw(RenderTarget arg0, RenderStates arg1) {
		arg0.draw(playerSprite);
		arg0.draw(HealthBar);
		HealthView.setSize(new Vector2f(((float)health / 200.f) * 120.f, 15.f));
		if (health <= 0) {
			health = 0;
			die(arg0);
		}
		if (health < 20) {
			HealthView.setFillColor(new Color(255, 33, 0));
		}
		else if (health <= 100) {
			HealthView.setFillColor(new Color(242, 255, 0 ));
		}
		else if (health <= 200) {
			HealthView.setFillColor(new Color(89, 244, 66));
		}
		arg0.draw(HealthView);
	}

}
