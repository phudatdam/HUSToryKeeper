package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Coin extends Entity{
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);

		name = "Đồng xu";

		solidArea = new Rectangle(16, 16, 32, 32);
		down2 = setup("/objects/coin", 40, 40);

		description = "[ " + name + " ]\nHãy ném xuống giếng thần\n và đợi điều kì diệu";
		
		image1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
	}
}
