package entity;

//import java.util.Random;

import main.GamePanel;

public class NPC_AnDuongVuong extends Entity {
	public NPC_AnDuongVuong(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
        up1 = setup("/npc/an_duong_vuong_up_1");
		up2 = setup("/npc/an_duong_vuong_up_2");
		up3 = setup("/npc/an_duong_vuong_up_2");
		down1 = setup("/npc/an_duong_vuong_down_1");
		down2 = setup("/npc/an_duong_vuong_down_2");
		down3 = setup("/npc/an_duong_vuong_down_2");
		left1 = setup("/npc/an_duong_vuong_left_1");
		left2 = setup("/npc/an_duong_vuong_left_2");
		left3 = setup("/npc/an_duong_vuong_left_2");
		right1 = setup("/npc/an_duong_vuong_right_1");
		right2 = setup("/npc/an_duong_vuong_right_2");
		right3 = setup("/npc/an_duong_vuong_right_2");
    }
    
	public void setDialogue()
	{
		dialogues[0] = "Nvc :\n đây là đâu vậy ?";
		dialogues[1] = "An Duong Vuong :\n Chào chàng trai trẻ mặc long bào, ";
		dialogues[2] = "Adv :\n ta là Thục Phán aka An Dương Vương. ";
		dialogues[3] = "Adv :\n Cậu là người được ma thuật thần bí lựa chọn để\n bảo vệ lịch sử.";
		dialogues[4] = "Nvc :\n Có hơi kì lạ không khi mà ngài biết chữ quốc ngữ\n ở thời này ?";
		dialogues[5] = "Adv :\n Cậu vừa được xuyên không về lịch sử nên điều này\n cũng bình thường thôi.";
		dialogues[6] = "Nvc :\n Hợp lý. ";
		dialogues[7] = "Adv :\n Dù sao, quân địch bằng một cách thần kì đã biết\n được về sức mạnh của nỏ thần và phá hủy nó.";
		dialogues[8] = "Adv :\n Cậu hay nhận lấy thanh kiếm này, Nhấn (nút) để\n tấn công. Quân địch ở ngoài đó nên hãy cẩn thận.";
		dialogues[9] = "Nvc :\n Kiếm thần ư ?";
		dialogues[10] = "Adv :\n Không phải, nếu là kiếm thần thì ta đã dùng rồi.\n Cậu hãy tiến lên tìm đủ X gỗ và Y sắt, giao nó cho\n người thợ rèn để sửa lại nỏ thần.";
		dialogues[11] = "Nvc :\n dùng tay không phá đá chặt cây ạ ? đây có phải\n Minecraft đâu.";
		dialogues[12] = "Adv :\n ta quên mất. khi phá đá hãy dùng cây cuốc này này.\n Chặt cây hãy dung chiếc rìu này. Chúc may may mắn.";
	}

	public void speak() {
		super.speak();
	}

    public void setAction() {
    	super.setAction();
    }
}
