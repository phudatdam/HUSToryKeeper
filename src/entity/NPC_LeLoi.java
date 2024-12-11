package entity;

import main.GamePanel;
import object.OBJ_Coin;

public class NPC_LeLoi extends Entity {

	public NPC_LeLoi(GamePanel gp) {
		super(gp);
		woodneed = 10;
		ironneed = 8;
		spitem[3] = 3;
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
		dialogues[0][0] = "Lê Lợi :\n Xin chào người anh hùng trẻ tuổi. có vẻ cậu là\n người đc chọn để bảo vệ lịch sử.";
		dialogues[0][1] = "Lê Lơi :\n Hãy giúp ta thu thập lấy gỗ sắt và giao nó\n cho người thợ rèn.";
		dialogues[0][2] = "Lê Lợi :\n Hãy cẩn thận với quân địch vì giờ là màn cuối\n nên chúng đang ở đỉnh cao sức mạnh";
		dialogues[0][3] = "Lê Lợi :\n Chúc may mắn. Hãy kiếm đủ " + woodneed + " gỗ và " + ironneed + " sắt nhé.\n Đừng quên cả những viên ngọc phép";
		
		dialogues[1][0] = "Lê Lợi :\n Thanh gươm này thật sắc bén. Đa tạ cậu.";
		dialogues[1][1] = "Lê Lợi :\n Hãy nhận lấy đồng xu này, đi tìm một cái giếng và\nthả nó vào đó, sẽ có điều kì diệu xảy ra.";
		dialogues[1][2] = "Lê Lợi :\n Chúc cậu thượng lộ bình an.";
	}

	public void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
		
		if(gp.player.hasDivineWeapon == true) {
			dialogueSet = 1;
			dialogueIndex = 0;
			
			int index = gp.player.SearchItemInInventory("Gươm thần");
			int wp = gp.player.SearchItemInInventory("Kiếm");
			gp.player.currentWeapon = gp.player.inventory.get(wp);
			gp.player.getPlayerAttackImage();
			gp.player.inventory.remove(index);
			gp.player.hasDivineWeapon = false;
			
			if(gp.player.coin == 0){
				gp.ui.addMessage("Bạn nhận được 1 Đồng xu");
				gp.player.coin = 1;
				gp.player.inventory.add(new OBJ_Coin(gp));
			}
		}
	}
}
