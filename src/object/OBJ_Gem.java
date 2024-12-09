package object;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_Gem extends Entity{
    public OBJ_Gem(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		name = "Linh Thạch";
		solidArea = new Rectangle (16, 16, 32, 32);
		
		description = "[ "+ name +" ]\nChứa sức mạnh phép thuật\nRèn vũ khí vừa khỏe vừa đẹp";
		stackeable = true;
		down1 = setup("/objects/gem", 32, 32);
		collision = true;
		image1 = setup("/objects/gem", gp.tileSize, gp.tileSize);
	}
}
