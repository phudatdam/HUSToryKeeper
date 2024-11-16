package entity;

import main.GamePanel;
import object.OBJ_Coin;

public class NPC_KimQuy extends Entity {
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
		//map1
		dialogues[1][0] = "Rùa Vàng :\n Vậy cậu là người được chọn để bảo vệ lịch sử ư ?";
		dialogues[1][1] = "Bạn :\n ngài là người thợ rèn ư ? ";
		dialogues[1][2] = "Rùa vàng:\n Thế cậu nghĩ ai là người đủ phép thuật để sửa\n nỏ thần ? Nào cậu có đủ đồ chưa ?";
		//map2
		dialogues[2][0] = "Rùa Vàng :\n oh, chúng ta lại gặp nhau à ?";
		dialogues[2][1] = "Bạn :\n sao lại là ngài nữa, tôi tưởng ngựa sắt là do\n triều đình làm ?";
		dialogues[2][2] = "Rùa Vàng :\n Triều đình nhận deal rồi order cho ta làm.\n Vậy câu có đủ đồ chưa ?";
		//map3
		dialogues[3][0] = "Rùa Vàng :\n Rất vui khi được gặp lại cậu. Hãy kiếm thêm nguyên\nliệu để chế tạo gươm thần.";
		//Đủ nguyên liệu ko
		dialogues[4][0] = "Rùa Vàng:\nĐược rồi, việc của cậu đã xong, hãy nhận lấy\n đồng xu này";
		dialogues[4][1] = "Rùa Vàng:\nHãy thả đồng xu xuống giếng kia và tiếp tục\n cuộc hành trình phía trước";
		dialogues[5][0] = "Rùa Vàng:\nNày vẫn chưa đủ đâu, cố gắng lên.";
	}
	public void speak() {
		//super.speak();
		facePlayer();
		dialogueSet=gp.currentMap;
		startDialogue(this, dialogueSet);
        if(diaEnd)
        {
            if( gp.player.iron >= gp.npc[gp.currentMap][0].ironneed && gp.player.wood >= gp.npc[gp.currentMap][0].woodneed)
            {
				dialogueSet=4;
				dialogueIndex=0;
				if(gp.player.coin == 0){
					gp.ui.addMessage("Bạn được 1 Đồng xu");
					gp.player.iron-= gp.npc[gp.currentMap][0].ironneed;
					gp.player.wood-= gp.npc[gp.currentMap][0].woodneed;
					gp.player.coin=1;
					gp.player.inventory.add(new OBJ_Coin(gp));
					int index = gp.player.SearchItemInInventoty("Sắt");
					{
						if(gp.player.inventory.get(index).amount > gp.npc[gp.currentMap][0].ironneed){
							gp.player.inventory.get(index).amount-= gp.npc[gp.currentMap][0].ironneed;
						}
						else
						{
							gp.player.inventory.remove(index);
						}
					}
					index = gp.player.SearchItemInInventoty("Gỗ");
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
            else
            {
                dialogueSet=5;
                dialogueIndex=0;
            }
        }
	}
}
