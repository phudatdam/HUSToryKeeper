package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Forest extends Entity {
	
	public OBJ_Forest(GamePanel gp, String imagePath, int width, int height) {
		super(gp);
		down1 = setup(imagePath, width, height);
		
		//collision = false;
	}
}
