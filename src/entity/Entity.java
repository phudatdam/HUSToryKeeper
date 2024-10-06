package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity { // lớp cha cho các lớp khác: nhân vật, NPC, monster, ...
    public int worldX, worldY;
    public int speed;

    // Khai báo dữ liệu ảnh
    public BufferedImage up1, up2, up3, down1, down2, down3, left1,
                        left2, left3, right1, right2, right3;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
