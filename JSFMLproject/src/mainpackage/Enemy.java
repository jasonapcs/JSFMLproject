package mainpackage;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Enemy implements Drawable {
	private Texture enemyTexture;
	private Sprite enemySprite;
	public Vector2f pos;
	
	Enemy(String pathToFrontTexture) throws IOException {
		enemySprite = new Sprite();
		enemyTexture = new Texture();
		enemyTexture.loadFromFile(Paths.get(pathToFrontTexture));
		enemySprite.setTexture(enemyTexture);
		enemySprite.setOrigin(60.f, 65.f);
		pos = new Vector2f(600.f, 300.f);
		enemySprite.setPosition(pos);
	}
	
	public void changeRot(float f) {
		enemySprite.setRotation(enemySprite.getRotation() + f);
	}
	
	public void setRot(float f) {
		enemySprite.setRotation(f);
	}
	
	public void setPos(float x, float y) {
		pos = new Vector2f(x, y);
		enemySprite.setPosition(pos);
	}
	
	public void changePos(float deltaX, float deltaY) {
		pos = new Vector2f(pos.x + deltaX, pos.y + deltaY);
		enemySprite.setPosition(pos);
	}

	@Override
	public void draw(RenderTarget arg0, RenderStates arg1) {
		arg0.draw(enemySprite);
	}
}
