package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);
		name = "Đồng xu";
		description = "[ " + name + " ]\nHãy ném xuống giếng thần\n và đợi điều kì diệu";
		
		image1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
	}
}
