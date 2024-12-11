package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronHorse extends Entity{
	
	public OBJ_IronHorse(GamePanel gp) {
		super(gp);
		type = TYPE_ironHorse;
		name = "Ngựa sắt";
		description = "[ " + name + " ]\n Ngựa sắt cháy phố \n Tán gái còn đổ chứ đừng nói đánh\ngiặc";
		attackValue = 97;
		defValue = 1;
		spdValue = 10;
		image1 = setup("/objects/iron_horse", gp.tileSize, gp.tileSize);
		image2 = setup("/misc/iron_horse", 96, 96);
	}
}