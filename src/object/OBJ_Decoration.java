package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Decoration extends Entity {
	
	public OBJ_Decoration(GamePanel gp, String imagePath, int width, int height, double col, double row) {
		super(gp);
		down1 = setup(imagePath, width, height);
		
		solidArea = new Rectangle (0, 0, width, height);
		collision = true;
		
		this.worldX = (int) (gp.tileSize * col);
		this.worldY = (int) (gp.tileSize * row);
	}
}
