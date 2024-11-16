package entity;

import main.GamePanel;

public class NPC_LeLoi extends Entity {

	public NPC_LeLoi(GamePanel gp) {
		super(gp);
		woodneed = 6;
		ironneed = 7;
		type = TYPE_NPC;
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
        up1 = setup("/npc/le_loi_up_1", gp.tileSize, gp.tileSize); 
		up2 = setup("/npc/le_loi_up_2", gp.tileSize, gp.tileSize);
		up3 = setup("/npc/le_loi_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/le_loi_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/le_loi_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/le_loi_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/le_loi_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/le_loi_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/le_loi_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/le_loi_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/le_loi_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/le_loi_right_2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
    	super.setAction();
    }
	public void setDialogue()
	{
		dialogues[0][0] = "Lê Lợi :\n Chào chàng trai trẻ. Ngoài kia có rất nhiều quân\nđịch hung bạo.";
		dialogues[0][1] = "Lê Lợi :\n Hết sức cẩn trọng nhé !";
	}

	public void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
