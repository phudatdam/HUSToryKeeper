package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
	
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		type = TYPE_axe;
		name = "Rìu";
		description = "[ "+ name +" ]\nchỉ để chặt gỗ";
		attackValue = 1;
		image1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
	}
}
