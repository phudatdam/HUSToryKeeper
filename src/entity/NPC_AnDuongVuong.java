package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Pickaxe;
import object.OBJ_Sword;

public class NPC_AnDuongVuong extends Entity {
	
	public NPC_AnDuongVuong(GamePanel gp) {
		super(gp);
		woodneed = 14;
		ironneed = 6;
		spitem[1] = 1;
		type = TYPE_NPC;
		speed = 1;
		
		getImage("/npc/an_duong_vuong");
		setDialogue();
	}
    
	private void setDialogue() {
		dialogues[0][0] = "Bạn :\n Đây là đâu vậy?";
		dialogues[0][1] = "An Dương Vương :\n Chào chàng trai trẻ mặc long bào,";
		dialogues[0][2] = "An Dương Vương :\n Ta là Thục Phán aka An Dương Vương.";
		dialogues[0][3] = "An Dương Vương :\n Cậu là người được ma thuật thần bí lựa chọn để\n bảo vệ lịch sử.";
		dialogues[0][4] = "Bạn :\n Có hơi kì lạ không khi mà ngài biết chữ quốc ngữ ở\n thời này?";
		dialogues[0][5] = "An Dương Vương :\n Cậu vừa được xuyên không về lịch sử nên điều này\n cũng bình thường thôi.";
		dialogues[0][6] = "Bạn :\n Hợp lý.";
		dialogues[0][7] = "An Dương Vương :\n Dù sao, sau khi xây xong tòa thành vững chắc này\n quân địch đã có tin leak về nỏ thần\n Chúng chắc chắn sẽ ngăn cản việc hoàn thành nó";
		dialogues[0][8] = "An Dương Vương :\n Cậu hãy nhận lấy thanh kiếm này. Quân địch ở\n ngoài đó nên hãy cẩn thận.";
		dialogues[0][9] = "Bạn :\n Kiếm thần ư?";
		dialogues[0][10] = "An Dương Vương :\n Không phải, nếu là kiếm thần thì ta đã dùng rồi.\n Cậu hãy tiến lên tìm đủ gỗ và sắt, giao nó cho\n người thợ rèn để sửa lại nỏ thần.";
		dialogues[0][11] = "Bạn :\n Dùng tay không phá đá chặt cây ạ? Đây có phải\n Minecraft đâu.";
		dialogues[0][12] = "An Dương Vương :\n Ồ, quên mất, sori sori. Khi phá đá hãy dùng cây cuốc\n này. Còn chặt cây thì hãy dùng chiếc rìu này nhé.";
		dialogues[0][13] = "An Dương Vương :\n Hãy kiếm đủ " + woodneed + " gỗ và " + ironneed + " sắt. Chúc may mắn!\n Ồ đừng quên vật phẩm đặc biệt nhé.";
		
		dialogues[1][0] = "An Dương Vương :\n Đây chính là nỏ thần ư? Đa tạ cậu.";
		dialogues[1][1] = "An Dương Vương :\n Hãy nhận lấy đồng xu này, đi tìm một cái giếng và\nthả vào đó, sẽ có điều kì diệu xảy ra.";
		dialogues[1][2] = "An Dương Vương :\n Chúc cậu thượng lộ bình an...";
	}

	void speak() {
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
			gp.player.giveDivineWeapon("Nỏ thần");
		}
	}
}