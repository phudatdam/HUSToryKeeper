package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Coin;
import object.OBJ_Pickaxe;
import object.OBJ_Sword;

public class NPC_AnDuongVuong extends Entity {
	
	public NPC_AnDuongVuong(GamePanel gp) {
		super(gp);
		woodneed = 8;
		ironneed = 4;
		spitem[1] = 1;
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
		dialogues[0][0] = "Bạn :\n Đây là đâu vậy?";
		dialogues[0][1] = "An Dương Vương :\n Chào chàng trai trẻ mặc long bào, ";
		dialogues[0][2] = "An Dương Vương :\n Ta là Thục Phán aka An Dương Vương. ";
		dialogues[0][3] = "An Dương Vương :\n Cậu là người được ma thuật thần bí lựa chọn để bảo\n vệ lịch sử.";
		dialogues[0][4] = "An Dương Vương :\n Có hơi kì lạ không khi mà ngài biết chữ quốc ngữ ở\n thời này?";
		dialogues[0][5] = "An Dương Vương :\n Cậu vừa được xuyên không về lịch sử nên điều này\n cũng bình thường thôi.";
		dialogues[0][6] = "Bạn :\n Hợp lý.";
		dialogues[0][7] = "An Dương Vương :\n Dù sao, sau khi xây xong tòa thành vững chắc này\n quân địch đã có tin leak về nỏ thần\n Chúng chắc chắn sẽ ngăn cản việc hoàn thành nó";
		dialogues[0][8] = "An Dương Vương :\n Cậu hay nhận lấy thanh kiếm này. Quân địch ở\n ngoài đó nên hãy cẩn thận.";
		dialogues[0][9] = "Bạn :\n Kiếm thần ư?";
		dialogues[0][10] = "An Dương Vương :\n Không phải, nếu là kiếm thần thì ta đã dùng rồi.\n Cậu hãy tiến lên tìm đủ gỗ và sắt, giao nó cho\n người thợ rèn để sửa lại nỏ thần.";
		dialogues[0][11] = "An Dương Vương :\n Dùng tay không phá đá chặt cây ạ? Đây có phải\n Minecraft đâu.";
		dialogues[0][12] = "An Dương Vương :\n Ta quên mất. Khi phá đá hãy dùng cây cuốc này này.\n Chặt cây hãy dùng chiếc rìu này.";
		dialogues[0][13] = "An Dương Vương :\n Hãy kiếm đủ " + woodneed + " gỗ và " + ironneed + " sắt. Chúc may mắn!\n Ồ đừng quên vật phẩm đặc biệt nhé";
		
		dialogues[1][0] = "An Dương Vương :\n Đây chính là nỏ thần ư ? Đa tạ cậu.";
		dialogues[1][1] = "An Dương Vương :\n Hãy nhận lấy đồng xu này, đi tìm một cái giếng và\nthả nó vào đó, sẽ có điều kì diệu xảy ra.";
		dialogues[1][2] = "An Dương Vương :\n Chúc cậu thượng lộ bình an.";
	}

	public void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
		if( gp.player.axe == 0 && gp.player.sword == 0 && gp.player.pickaxe == 0)
		{
			gp.player.inventory.add(new OBJ_Sword(gp));
			gp.player.inventory.add(new OBJ_Axe(gp));
			gp.player.inventory.add(new OBJ_Pickaxe(gp));
			gp.ui.addMessage("Bạn nhận được 1 Kiếm");
			gp.ui.addMessage("Bạn nhận được 1 Rìu");
			gp.ui.addMessage("Bạn nhận được 1 Cuốc");
			gp.player.axe=1;
			gp.player.sword=1;
			gp.player.pickaxe=1;
		}
		
		if(gp.player.hasDivineWeapon == true) {
			dialogueSet = 1;
			dialogueIndex = 0;
			
			int index = gp.player.SearchItemInInventory("Nỏ thần");
			int wp = gp.player.SearchItemInInventory("Kiếm");
			gp.player.currentWeapon = gp.player.inventory.get(wp);
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