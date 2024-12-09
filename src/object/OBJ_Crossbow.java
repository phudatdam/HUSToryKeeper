package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Crossbow extends Entity{
	
	public OBJ_Crossbow(GamePanel gp) {
		super(gp);

		name = "Nỏ thần";
		description = "[ " + name + " ]\n Chúng sinh bình đẳng\n Một bắn bình thiên hạ";
		
		image1 = setup("/objects/crossbow", gp.tileSize, gp.tileSize);
		image2 = setup("/misc/crossbow", 96, 96);
	}
}