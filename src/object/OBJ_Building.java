package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Building extends Entity {
	
	public OBJ_Building(GamePanel gp, String imagePath, int width, int height) {
		super(gp);
		name = "Well";
		down1 = setup(imagePath, width, height);
		
		solidArea = new Rectangle (0, 0, width, height);
		collision = true;
	}
}
