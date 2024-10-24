package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Iron;
public class NPC_KimQuy extends Entity{
    public NPC_KimQuy(GamePanel gp) {
		super(gp);
		
		type = TYPE_NPC;
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
        up1 = setup("/npc/kim_quy_up_1", gp.tileSize, gp.tileSize); 
		up2 = setup("/npc/kim_quy_up_2", gp.tileSize, gp.tileSize);
		up3 = setup("/npc/kim_quy_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/kim_quy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/kim_quy_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/kim_quy_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/kim_quy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/kim_quy_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/kim_quy_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/kim_quy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/kim_quy_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/kim_quy_right_2", gp.tileSize, gp.tileSize);
    }
    public void setAction()
    {
        super.setAction();
    }
	public void setDialogue()
	{
		dialogues[0][0] = "Rùa Vàng :\n Vậy cậu là người được chọn để bảo vệ lịch sử ư ?";
		dialogues[0][1] = "Nvc :\n ngài là người thợ rèn ư ? ";
		dialogues[0][2] = "Rùa vàng:\n Thế cậu nghĩ ai là người đủ phép thuật để sửa\n nỏ thần ? Nào cậu có đủ đồ chưa ?";
		dialogues[1][0] = "Rùa Vàng:\nĐược rồi,việc của cậu đã xong , hãy tiếp tục\n cuộc hành trình phía trước";
		dialogues[2][0] = "Rùa Vàng:\nNày vẫn chưa đủ đâu, cố gắng lên.";
	}

	public void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
        if(diaEnd)
        {
            if( gp.player.iron >= 1 && gp.player.wood >=1)
            {
                dialogueSet=1;
                dialogueIndex=0;
                //startDialogue(this, dialogueSet);
                gp.player.iron-=1;
                gp.player.wood-=1;
                //gp.player.inventory.remove());

            }
            else
            {
                dialogueSet=2;
                dialogueIndex=0;
                //startDialogue(this, dialogueSet);
                //gp.player.iron-=1;
                //gp.player.wood-=1;
                //gp.player.inventory.remove());

            }
        }
	}
}
