package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron extends Entity{
	
	public OBJ_Iron(GamePanel gp) {
		super(gp);
		
		name = "Sắt";
		description = "[ "+ name +" ]\nChỉ là sắt thôi, bạn mong chờ gì.";
		
		down1 = setup("/objects/iron", gp.tileSize, gp.tileSize);
	}
}
