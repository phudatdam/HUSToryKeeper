package entity;

import main.GamePanel;
import object.OBJ_Crossbow;
import object.OBJ_GoldSword;
import object.OBJ_IronHorse;

public class NPC_KimQuy extends Entity {
	int mapNum;
	
    public NPC_KimQuy(GamePanel gp, int mapNum) {
		super(gp);
		
		this.mapNum = mapNum;
		
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
		//map1
		dialogues[1][0] = "Rùa Vàng :\n Vậy cậu là người được chọn để bảo vệ lịch sử ư ?";
		dialogues[1][1] = "Bạn :\n ngài là người thợ rèn ư ? ";
		dialogues[1][2] = "Rùa vàng:\n Thế cậu nghĩ ai là người đủ phép thuật để sửa\n nỏ thần ? Nào cậu có đủ đồ chưa ?";
		//map2
		dialogues[2][0] = "Rùa Vàng :\n oh, chúng ta lại gặp nhau à ?";
		dialogues[2][1] = "Bạn :\n sao lại là ngài nữa, tôi tưởng ngựa sắt là do\n triều đình làm ?";
		dialogues[2][2] = "Rùa Vàng :\n Triều đình nhận deal rồi order cho ta làm.\n Vậy câu có đủ đồ chưa ?";
		//map3
		dialogues[3][0] = "Bạn :\n sao tôi không còn bất ngờ khi gặp ngài nhỉ ?";
		dialogues[3][1] = "Rùa Vàng :\n người gặp ta cũng là chuyện bình thường.\n Dù sao ta cũng là người cung cấp vũ khí mạnh\n nhất lịch sử.";
		dialogues[3][2] = "Rùa Vàng :\n Hơn nữa việc làm thêm npc nữa tốn thời\n gian lắm. Nào, đủ nguyên liệu chưa chàng trai ?";
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
	public void speak() {
		//super.speak();
		facePlayer();
		dialogueSet=gp.currentMap;
		startDialogue(this, dialogueSet);
        if(diaEnd)
        {
            if(gp.player.iron >= gp.npc[gp.currentMap][0].ironneed && gp.player.wood >= gp.npc[gp.currentMap][0].woodneed)
            {
            	dialogueSet=4;
				dialogueIndex=0;
				if(gp.player.hasDivineWeapon == false) {
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
					
					gp.player.iron -= gp.npc[gp.currentMap][0].ironneed; // giảm số lượng đang có
					gp.player.wood -= gp.npc[gp.currentMap][0].woodneed;
					int index = gp.player.SearchItemInInventory("Sắt");
					{
						if(gp.player.inventory.get(index).amount > gp.npc[gp.currentMap][0].ironneed){
							gp.player.inventory.get(index).amount-= gp.npc[gp.currentMap][0].ironneed;
						}
						else
						{
							gp.player.inventory.remove(index); // xóa trên innventory
						}
					}
					index = gp.player.SearchItemInInventory("Gỗ");
					{
						if(gp.player.inventory.get(index).amount > gp.npc[gp.currentMap][0].woodneed){
							gp.player.inventory.get(index).amount-= gp.npc[gp.currentMap][0].woodneed;
						}
						else
						{
							gp.player.inventory.remove(index);
						}
					}
					
				}
              
            } 
            else {
                dialogueSet=5;
                dialogueIndex=0;
            }
        }
	}
}
