package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_AnDuongVuong extends Entity {
	public NPC_AnDuongVuong(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;

		getImage();
	}
	
	public void getImage() {
        up1 = setup("/npc/an_duong_vuong_up_1");
		up2 = setup("/npc/an_duong_vuong_up_2");
		up3 = setup("/npc/an_duong_vuong_up_2");
		down1 = setup("/npc/an_duong_vuong_down_1");
		down2 = setup("/npc/an_duong_vuong_down_2");
		down3 = setup("/npc/an_duong_vuong_down_2");
		left1 = setup("/npc/an_duong_vuong_left_1");
		left2 = setup("/npc/an_duong_vuong_left_2");
		left3 = setup("/npc/an_duong_vuong_left_2");
		right1 = setup("/npc/an_duong_vuong_right_1");
		right2 = setup("/npc/an_duong_vuong_right_2");
		right3 = setup("/npc/an_duong_vuong_right_2");
    }
    
    public void setAction() {
    	actionLockCounter++;
    	if (actionLockCounter == 120) {
    		Random random = new Random();
        	int i = random.nextInt(100) + 1;
        	
        	if (i <= 25) {
        		direction = "up";  		
        	}
        	else if ((i > 25)&&(i <= 50)) {
        		direction = "down";
        	}
        	else if ((i > 50)&&(i <= 75)) {
        		direction = "left";
        	}
        	else if ((i > 75)&&(i <= 100)) {
        		direction = "right";
        	}
        	actionLockCounter = 0;
    	}
    }
}
