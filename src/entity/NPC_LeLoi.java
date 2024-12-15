package entity;

import main.GamePanel;

public class NPC_LeLoi extends Entity {

	public NPC_LeLoi(GamePanel gp) {
		super(gp);
		woodneed = 12;
		ironneed = 12;
		spitem[3] = 3;
		type = TYPE_NPC;
		speed = 1;
		
		getImage("/npc/le_loi");
		setDialogue();
	}
    
	private void setDialogue() {
		dialogues[0][0] = "Lê Lợi :\n Xin chào người anh hùng trẻ tuổi. Có vẻ cậu là\n người được chọn để bảo vệ lịch sử.";
		dialogues[0][1] = "Lê Lơi :\n Hãy giúp ta thu thập gỗ, sắt và giao nó\n cho người thợ rèn.";
		dialogues[0][2] = "Lê Lợi :\n Hãy cẩn thận với quân địch vì giờ là màn cuối\n nên chúng đang ở đỉnh cao sức mạnh.";
		dialogues[0][3] = "Lê Lợi :\n Chúc may mắn. Hãy kiếm đủ " + woodneed + " gỗ và " + ironneed + " sắt nhé.\n Đừng quên cả những viên ngọc phép.";
		
		dialogues[1][0] = "Lê Lợi :\n Thanh gươm này thật sắc bén. Đa tạ cậu.";
		dialogues[1][1] = "Lê Lợi :\n Hãy nhận lấy đồng xu này, đi tìm một cái giếng và\nthả nó vào đó, sẽ có điều kì diệu xảy ra.";
		dialogues[1][2] = "Lê Lợi :\n Chúc cậu thượng lộ bình an.";
	}

	void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
		
		if(gp.player.hasDivineWeapon == true) {
			dialogueSet = 1;
			dialogueIndex = 0;
			gp.player.giveDivineWeapon("Gươm thần");
		}
	}
}
