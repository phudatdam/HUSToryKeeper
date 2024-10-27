package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Pickaxe;

public class NPC_ThanhGiong extends NPC {

	public NPC_ThanhGiong(GamePanel gp) {
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
        up1 = setup("/npc/thanh_giong_up_1", gp.tileSize, gp.tileSize); 
		up2 = setup("/npc/thanh_giong_up_2", gp.tileSize, gp.tileSize);
		up3 = setup("/npc/thanh_giong_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/thanh_giong_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/thanh_giong_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/thanh_giong_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/thanh_giong_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/thanh_giong_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/thanh_giong_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/thanh_giong_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/thanh_giong_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/thanh_giong_right_2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
    	super.setAction();
    }
	public void setDialogue()
	{
		dialogues[0][0] = "Thánh Gióng :\n Xin chào chàng trai trẻ, ta xin tự giới thiệu\n ta là Thánh Gióng.";
		dialogues[0][1] = "Nvc :\n Ngài có vẻ không đô nhưng họ kể.";
		dialogues[0][2] = "Thánh Gióng :\n Cậu biết body shaming bị phạt tiền đúng không.";
		dialogues[0][3] = "Nvc :\n Dạ em xin lỗi ạ.";
		dialogues[0][4] = "Thánh Gióng :\n Quân địch đã đến và chúng đã e sợ sức mạnh của ta.";
		dialogues[0][5] = "Thánh Gióng :\n Thế nên, bằng mưu hèn kế bẩn, thủ đoạn vô biên\n chúng đã ngăn cản việc ta có ngựa sắt.";
		dialogues[0][6] = "Thánh Gióng :\n Vì vậy, hãy giúp ta một tay. Hãy tìm đủ X sắt Y gỗ\n và giao cho người thợ rèn.";
		dialogues[0][7] = "Thánh Gióng :\n Hãy cẩn thận vì giờ quân địch là rank bạc rồi.";
		dialogues[0][8] = "Nvc :\n Việc này có vẻ đơn giản với ngài mà nhỉ ?";
		dialogues[0][9] = "Thánh Gióng :\n Nếu ta làm thì game còn gì vui nữa.";
		dialogues[0][10] = "Thánh Gióng :\n Hãy kiếm đủ " + woodneed + " gỗ và " + ironneed + " sắt. Chúc may mắn!";
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
