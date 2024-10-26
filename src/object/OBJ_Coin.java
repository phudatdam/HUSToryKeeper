package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);
		
		name = "Coin";
		description = "[ " + name + " ]\n(test)";
		
		down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
	}
}
