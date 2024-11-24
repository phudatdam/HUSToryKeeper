package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Crossbow extends Entity{
	
	public OBJ_Crossbow(GamePanel gp) {
		super(gp);

		name = "Nỏ thần";

//		solidArea = new Rectangle(16, 16, 32, 32);
		down2 = setup("/misc/crossbow", 40, 40);

		description = "[ " + name + " ]\nBắn một phát chết hàng vạn quân giặc";
		
		image1 = setup("/objects/crossbow", gp.tileSize, gp.tileSize);
	}
}