package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GoldSword extends Entity{
	
	public OBJ_GoldSword(GamePanel gp) {
		super(gp);

		name = "Gươm thần";
		description = "[ " + name + " ]\nGươm Thuận Thiên, đánh đâu thắng đó";
		
		image1 = setup("/objects/gold_sword", gp.tileSize, gp.tileSize);
		image2 = setup("/misc/gold_sword", 96, 96);
	}
}