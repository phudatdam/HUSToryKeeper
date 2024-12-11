package object;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_Claw extends Entity{
    public OBJ_Claw(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		name = "Móng rùa";
		solidArea = new Rectangle (16, 16, 32, 32);
		
		description = "[ "+ name +" ]\nCực kì cứng cáp\nphủ lên khung nỏ mà bắn thì hết\nnước chấm";
		stackeable = true;
		down1 = setup("/objects/claw", 32, 32);
		collision = true;
		image1 = setup("/objects/claw", gp.tileSize, gp.tileSize);
	}
}
