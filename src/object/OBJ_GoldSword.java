package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GoldSword extends Entity{
	
	public OBJ_GoldSword(GamePanel gp) {
		super(gp);
		type = TYPE_sword;
		name = "Gươm thần";
		description = "[ " + name + " ]\n Kiếm kỹ +100, nhuệ khí +1000\n Oách xà lách vô cùng";
		attackValue = 100;
		defValue = 0;
		spdValue = 0;
		image1 = setup("/objects/gold_sword", gp.tileSize, gp.tileSize);
		image2 = setup("/misc/gold_sword", 96, 96);
	}
}