package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Pickaxe;
import object.OBJ_Sword;

public class NPC_AnDuongVuong extends Entity {
	
	public NPC_AnDuongVuong(GamePanel gp) {
		super(gp);
		woodneed = 2;
		ironneed = 2;
		type = TYPE_NPC;
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
        up1 = setup("/npc/an_duong_vuong_up_1", gp.tileSize, gp.tileSize); 
		up2 = setup("/npc/an_duong_vuong_up_2", gp.tileSize, gp.tileSize);
		up3 = setup("/npc/an_duong_vuong_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/an_duong_vuong_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/an_duong_vuong_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/an_duong_vuong_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/an_duong_vuong_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/an_duong_vuong_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/an_duong_vuong_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/an_duong_vuong_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/an_duong_vuong_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/an_duong_vuong_right_2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
    	super.setAction();
    }
	public void setDialogue()
	{
		dialogues[0][0] = "Nvc :\n Đây là đâu vậy?";
		dialogues[0][1] = "An Duong Vuong :\n Chào chàng trai trẻ mặc long bào, ";
		dialogues[0][2] = "Adv :\n Ta là Thục Phán aka An Dương Vương. ";
		dialogues[0][3] = "Adv :\n Cậu là người được ma thuật thần bí lựa chọn để bảo\n vệ lịch sử.";
		dialogues[0][4] = "Nvc :\n Có hơi kì lạ không khi mà ngài biết chữ quốc ngữ ở\n thời này?";
		dialogues[0][5] = "Adv :\n Cậu vừa được xuyên không về lịch sử nên điều này\n cũng bình thường thôi.";
		dialogues[0][6] = "Nvc :\n Hợp lý.";
		dialogues[0][7] = "Adv :\n Dù sao, quân địch bằng một cách thần kì đã biết\n được về sức mạnh của nỏ thần và phá hủy nó.";
		dialogues[0][8] = "Adv :\n Cậu hay nhận lấy thanh kiếm này, Nhấn J để tấn\n công. Quân địch ở ngoài đó nên hãy cẩn thận.";
		dialogues[0][9] = "Nvc :\n Kiếm thần ư?";
		dialogues[0][10] = "Adv :\n Không phải, nếu là kiếm thần thì ta đã dùng rồi.\n Cậu hãy tiến lên tìm đủ gỗ và sắt, giao nó cho\n người thợ rèn để sửa lại nỏ thần.";
		dialogues[0][11] = "Nvc :\n Dùng tay không phá đá chặt cây ạ? Đây có phải\n Minecraft đâu.";
		dialogues[0][12] = "Adv :\n Ta quên mất. Khi phá đá hãy dùng cây cuốc này này.\n Chặt cây hãy dùng chiếc rìu này.";
		dialogues[0][13] = "Adv :\n Hãy kiếm đủ " + woodneed + " gỗ và " + ironneed + " sắt. Chúc may mắn!";
	}

	public void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
		if( gp.player.axe == 0 && gp.player.sword == 0 && gp.player.pickaxe == 0)
		{
			gp.player.inventory.add(new OBJ_Axe(gp));
			//gp.player.inventory.add(new OBJ_Sword(gp));
			gp.player.inventory.add(new OBJ_Pickaxe(gp));
			gp.player.axe=1;
			gp.player.sword=1;
			gp.player.pickaxe=1;
	
		}
	}
}