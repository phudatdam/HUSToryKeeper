package entity;

import main.GamePanel;

public class Projectile extends Entity{
	
	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive; //
		this.user = user;
		this.life = this.maxLife;
	}
	
	public void update() {
		
		if(user == gp.player) {
			// nếu player có thể bắn xa
		}
		
		if(user != gp.player) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(contactPlayer == true && gp.player.invincible == false) {
				damagePlayer(attack); // gây damage
				alive = false;        // rồi biến mất
			}
		}
		
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
		
		life--;
		if(life <= 0) {    // sau maxLife vòng lặp
			alive = false; // thì biến mất
		}
		
		spriteCounter++; // 
        if(spriteCounter > 7) { // hình ảnh được thay đổi sau 8 khung hình
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
	}

}
