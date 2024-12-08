package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wallet extends Entity{
    public OBJ_Wallet(GamePanel gp)
    {
        super(gp);
        type = TYPE_sword;
		name = "Ví tiền";
		description = "[ "+ name +" ]\nVí nhìn sang nhưng chỉ còn 50k";
		attackValue = 0;
        defValue = 0;
        spdValue = 0;
		image1 = setup("/objects/wallet", gp.tileSize, gp.tileSize);

    }
}
