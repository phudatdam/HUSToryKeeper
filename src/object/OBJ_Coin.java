package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);

		type = TYPE_pickupOnly;
		name = "Đồng xu";
		description = "[ " + name + " ]\nHãy ném xuống giếng thần\n và đợi điều kì diệu";
		value = 1;
		
		down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
	}

	public void use(Entity entity){
		// gp.playSE();
		// gp.player.coin += value;
	}
}
