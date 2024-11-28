package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronHorse extends Entity{
	
	public OBJ_IronHorse(GamePanel gp) {
		super(gp);

		name = "Ngựa sắt";
		description = "[ " + name + " ]\nMột con ngựa được làm từ sắt, có thể\nphun ra lửa";
		
		image1 = setup("/objects/iron_horse", gp.tileSize, gp.tileSize);
		image2 = setup("/misc/iron_horse", 96, 96);
	}
}