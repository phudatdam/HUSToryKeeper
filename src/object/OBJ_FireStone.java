package object;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_FireStone extends Entity{
    public OBJ_FireStone(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		name = "Đá lửa";
		solidArea = new Rectangle (16, 16, 32, 32);
		
		description = "[ "+ name +" ]\nLưu giữ ngọn lửa ý chí\nđược bao bọc bởi lớp đá để cất trong\n túi quần mà không cháy";
		stackeable = true;
		down1 = setup("/objects/firestone", 32, 32);
		collision = true;
		image1 = setup("/objects/firestone", gp.tileSize, gp.tileSize);
	}
}