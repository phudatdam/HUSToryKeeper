package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron extends Entity{
	
	public OBJ_Iron(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		name = "Sắt";
		solidArea = new Rectangle (16, 16, 32, 32);
		down2 = setup("/objects/iron", 32, 32);
		
		description = "[ "+ name +" ]\nChỉ là sắt thôi, bạn mong chờ gì.";
		stackeable = true;
		down1 = setup("/objects/iron", 32, 32);
		collision = true;
		image1 = setup("/objects/iron", gp.tileSize, gp.tileSize);
	}
}
