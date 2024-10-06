package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        this.screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        this.screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(6, 12, 20, 20);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 48;
        speed = 3;
        direction = "up";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player-up-2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/player-up-3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player-down-2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/player-down-3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player-left-2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/player-left-3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player-right-2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/player-right-3.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){ // được gọi 60 lần trong 1s

        if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if (keyH.rightPressed){
                direction = "right";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }

            // Check va chạm
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Nếu collision = false, player có thể di chuyển
            if(collisionOn == false){
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 7){ // hình ảnh được thay đổi sau 8 khung hình
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up3;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up1;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down3;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down1;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right3;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right1;
                }
                break;
            default:
                if(spriteNum == 1){
                    image = left3;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left1;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}