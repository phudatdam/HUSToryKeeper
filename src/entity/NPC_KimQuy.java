package entity;

import main.GamePanel;
import object.OBJ_Crossbow;
import object.OBJ_GoldSword;
import object.OBJ_IronHorse;

public class NPC_KimQuy extends Entity {
	private int mapNum;
	
    public NPC_KimQuy(GamePanel gp, int mapNum) {
		super(gp);
		this.mapNum = mapNum;
		type = TYPE_NPC;
		speed = 1;
		
		getImage("/npc/kim_quy");
		setDialogue();
	}
    
	private void setDialogue() {
		//map1
		dialogues[1][0] = "Rùa Vàng :\n Vậy ra cậu là người được chọn để bảo vệ lịch sử ư?";
		dialogues[1][1] = "Bạn :\n Yes sir, ngài là người thợ rèn sao? ";
		dialogues[1][2] = "Rùa vàng:\n Thế cậu nghĩ ai là người đủ phép thuật để sửa\n nỏ thần? Nào cậu có đủ đồ chưa?";
		//map2
		dialogues[2][0] = "Rùa Vàng :\n Oh, chúng ta lại gặp nhau à?";
		dialogues[2][1] = "Bạn :\n Sao lại là ngài nữa, tôi tưởng ngựa sắt là do\n triều đình làm?";
		dialogues[2][2] = "Rùa Vàng :\n Triều đình nhận deal rồi order cho ta làm.\n Vậy cậu có đủ đồ chưa?";
		//map3
		dialogues[3][0] = "Bạn :\n Sao tôi không còn bất ngờ khi gặp ngài nhỉ?";
		dialogues[3][1] = "Rùa Vàng :\n Người gặp ta cũng là chuyện bình thường.\n Dù sao ta cũng là người cung cấp vũ khí mạnh\n nhất lịch sử.";
		dialogues[3][2] = "Rùa Vàng :\n Hơn nữa việc làm thêm npc nữa tốn thời\n gian lắm. Nào, đủ nguyên liệu chưa chàng trai?";
		//Đủ nguyên liệu ko
		dialogues[4][0] = "Rùa Vàng :\n Được rồi, nguyên liệu đã kiếm đủ, bây giờ ta\nsẽ chế tạo vũ khí thần...";
		switch(mapNum) {
		case 1:
			dialogues[4][1] = "Rùa Vàng :\n Hãy đem vũ khí này trao cho An Dương Vương giúp\nta.";
			break;
		case 2:
			dialogues[4][1] = "Rùa Vàng :\n Hãy đem vũ khí này trao cho chàng trai tên\nGióng giúp ta.";
			break;
		case 3:
			dialogues[4][1] = "Rùa Vàng :\n Hãy đem vũ khí này trao cho Lê Lợi giúp ta.";
			break;
		}
		//thiếu
		dialogues[5][0] = "Rùa Vàng :\n Này vẫn chưa đủ đâu, cố gắng lên.";
	}
	
	void speak() {
		//super.speak();
		facePlayer();
		dialogueSet = gp.currentMap;
		startDialogue(this, dialogueSet);
        if(diaEnd)
        {
            if(gp.player.iron >= gp.npc[gp.currentMap][0].ironneed && gp.player.wood >= gp.npc[gp.currentMap][0].woodneed
				&& gp.player.spitem[gp.currentMap] >= gp.npc[gp.currentMap][0].spitem[gp.currentMap])
            {
            	dialogueSet = 4;
				dialogueIndex = 0;
				if(gp.player.hasDivineWeapon == false) {
					// Player receives divine weapon
					gp.player.hasDivineWeapon = true;
					Entity finalWeapon = null;
					switch(gp.currentMap) {
					case 1:
						gp.ui.addMessage("Bạn nhận được nỏ thần");
						finalWeapon = new OBJ_Crossbow(gp);
						break;
					case 2:
						gp.ui.addMessage("Bạn nhận được ngựa sắt");
						finalWeapon = new OBJ_IronHorse(gp);
						break;
					case 3:
						gp.ui.addMessage("Bạn nhận được gươm thần");
						finalWeapon = new OBJ_GoldSword(gp);
						break;
					}
					gp.player.inventory.add(finalWeapon);
					gp.ui.finalWeapon = finalWeapon;
					
					// Remove requirements
					gp.player.iron -= gp.npc[gp.currentMap][0].ironneed; // giảm số lượng đang có
					gp.player.removeItemFromInventory("Sắt", gp.npc[gp.currentMap][0].ironneed);
					
					gp.player.wood -= gp.npc[gp.currentMap][0].woodneed;
					gp.player.removeItemFromInventory("Gỗ", gp.npc[gp.currentMap][0].woodneed);
					
					gp.player.spitem[gp.currentMap] -= gp.npc[gp.currentMap][0].spitem[gp.currentMap];
					switch(gp.currentMap) {
						case 1:
							gp.player.removeItemFromInventory("Móng rùa", 1);
							break;
						case 2:
							gp.player.removeItemFromInventory("Đá lửa", 2);
						break;
						case 3:
							gp.player.removeItemFromInventory("Linh thạch", 3);
						    break;
					}
				}
            } 
            else {
                dialogueSet = 5;
                dialogueIndex = 0;
            }
        }
	}
}
