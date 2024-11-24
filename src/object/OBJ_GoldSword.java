package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GoldSword extends Entity{
	
	public OBJ_GoldSword(GamePanel gp) {
		super(gp);

		name = "Gươm thần";

//		solidArea = new Rectangle(16, 16, 32, 32);
		down2 = setup("/misc/gold_sword", 40, 40);

		description = "[ " + name + " ]\nGươm Thuận Thiên, đánh đâu thắng đó";
		
		image1 = setup("/objects/gold_sword", gp.tileSize, gp.tileSize);
	}
}