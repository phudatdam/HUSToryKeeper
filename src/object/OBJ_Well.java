package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Well extends Entity {
	
	public OBJ_Well(GamePanel gp) {
		super(gp);
		name = "Well";
		down1 = setup("/objects/well", gp.tileSize * 2, gp.tileSize * 2);
		
		solidArea = new Rectangle (0, 0, gp.tileSize * 2, gp.tileSize * 2);
		collision = true;
	}
}
