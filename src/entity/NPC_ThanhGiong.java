package entity;

import main.GamePanel;

public class NPC_ThanhGiong extends Entity {

	public NPC_ThanhGiong(GamePanel gp) {
		super(gp);
		woodneed = 8;
		ironneed = 14;
		spitem[2] = 2;
		type = TYPE_NPC;
		speed = 1;
		
		getImage("/npc/thanh_giong");
		setDialogue();
	}
    
	private void setDialogue() {
		dialogues[0][0] = "Thánh Gióng :\n Xin chào chàng trai trẻ, ta xin tự giới thiệu\n ta là Thánh Gióng.";
		dialogues[0][1] = "Bạn :\n Ngài có vẻ không đô nhưng họ kể.";
		dialogues[0][2] = "Thánh Gióng :\n Cậu biết body shaming bị phạt tiền đúng không.";
		dialogues[0][3] = "Bạn :\n Dạ em xin lỗi ạ.";
		dialogues[0][4] = "Thánh Gióng :\n Quân địch đã đến và chúng đã e sợ sức mạnh của ta.";
		dialogues[0][5] = "Thánh Gióng :\n Thế nên, bằng mưu hèn kế bẩn, thủ đoạn vô biên\n chúng đã ngăn cản việc ta có ngựa sắt.";
		dialogues[0][6] = "Thánh Gióng :\n Vì vậy, hãy giúp ta một tay. Hãy tìm đủ sắt và gỗ\n rồi giao cho người thợ rèn.";
		dialogues[0][7] = "Thánh Gióng :\n Hãy cẩn thận vì giờ quân địch là rank bạc rồi.";
		dialogues[0][8] = "Bạn :\n Việc này có vẻ đơn giản với ngài mà nhỉ?";
		dialogues[0][9] = "Thánh Gióng :\n Nếu ta làm thì game còn gì vui nữa.";
		dialogues[0][10] = "Thánh Gióng :\n Ta cần " + woodneed + " gỗ và " + ironneed + " sắt. Nhanh nhẹn lên nào!\n Nhớ kiếm cả nhiên liệu cho ngựa nữa";
		
		dialogues[1][0] = "Thánh Gióng :\n Con ngựa này thật dũng mãnh. Đa tạ cậu.";
		dialogues[1][1] = "Thánh Gióng :\n Hãy nhận lấy đồng xu này, đi tìm một cái giếng và\nthả nó vào đó, sẽ có điều kì diệu xảy ra.";
		dialogues[1][2] = "Thánh Gióng :\n Chúc cậu thượng lộ bình an.";
	}

	void speak() {
		//super.speak();
		facePlayer();
		startDialogue(this, dialogueSet);
		
		if(gp.player.hasDivineWeapon == true) {
			dialogueSet = 1;
			dialogueIndex = 0;
			gp.player.giveDivineWeapon("Ngựa sắt");
		}
	}
}
