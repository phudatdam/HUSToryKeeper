package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class Monster extends Entity {
	protected boolean rangedAttack = false;

	public Monster(GamePanel gp) {
		super(gp);
		type = TYPE_MONSTER;
		solidArea = new Rectangle(12, 24, 40, 40);
		motion1_duration = 5;
		motion2_duration = 25;
	}
	
	protected void getImage(String name) {
		up1 = setup("/monsters/" + name + "_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/" + name + "_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/" + name + "_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/" + name + "_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/" + name + "_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/" + name + "_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/" + name + "_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/" + name + "_right_2", gp.tileSize, gp.tileSize);
	}
	
	protected void getAttackImage(String name) {
		attackUp1 = setup("/monsters/" + name + "_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/monsters/" + name + "_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/monsters/" + name + "_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/monsters/" + name + "_attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/monsters/" + name + "_attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/monsters/" + name + "_attack_left_2", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/monsters/" + name + "_attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/monsters/" + name + "_attack_right_2", gp.tileSize * 2, gp.tileSize);		
	}
	
	protected void attacking() {
    	spriteCounter++;
    	
    	if (spriteCounter <= motion1_duration) {
    		spriteNum = 1;
    	}
    	if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
    		spriteNum = 2;
    		
    		// Save the current worldX, worldY, solidArea
    		int currentWorldX = worldX;
    		int currentWorldY = worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		
    		// Adjust player worldX/worldY for the attackArea
    		switch(direction) {
    			case("up"): worldY -= attackArea.height; 
    			case("down"): worldY += attackArea.height; 
    			case("left"): worldY -= attackArea.width;
    			case("right"): worldY += attackArea.width; 
    		}
    		
    		// Change solidArea to the attackArea
    		solidArea.width = attackArea.width;
    		solidArea.height = attackArea.height;
    		
    		if (gp.cChecker.checkPlayer(this) == true) {
    			damagePlayer(attack);
    		}
    		
    		if (rangedAttack == true) {
    			// Shoot a projectile
    			checkShoot(30);
    		}
    		
    		// Restore the original data
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth; 
    		solidArea.height = solidAreaHeight;
    	}
    	if (spriteCounter > motion2_duration) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }
	
	private void checkShoot(int shotInterval) {
    	if(projectile.alive == false) {
    		projectile.set(worldX, worldY, direction, true, this);
    		gp.projectileList.add(projectile);
    	}
	}
	
	private void checkStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}			
		}
	}
	
	private void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}	
		}
	}
	
	private void checkAttackOrNot(int rate, int vertical, int horizontal) {
		boolean targetInRange = false;
		int xDis = getXDistance(gp.player);	
		int yDis = getYDistance(gp.player);
		
		switch(direction) {
		case("up"):
			if (gp.player.worldY < worldY && yDis < vertical && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case("down"):
			if (gp.player.worldY > worldY && yDis < vertical && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case("left"):
			if (gp.player.worldX < worldX && yDis < vertical && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case("right"):
			if (gp.player.worldX > worldX && yDis < vertical && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		}
		
		if (targetInRange == true) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
			}
		}
	}
	
	private void searchPath(int goalCol, int goalRow) {
		int startCol = (worldX + solidArea.x) / gp.tileSize;
		int startRow = (worldY + solidArea.y) / gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if (gp.pFinder.search() == true) {
			// Next worldX and worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			// Entity solidArea's position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			} else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				// left or right
				if (enLeftX > nextX) {
					direction = "left";
				}
				if (enLeftX < nextX) {
					direction = "right";
				}
			} else if (enTopY > nextY && enLeftX > nextX) {
				// up or left
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			} else if (enTopY > nextY && enLeftX < nextX) {
				// up or right
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
			} else if (enTopY < nextY && enLeftX > nextX) {
				// down or left
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			} else if (enTopY < nextY && enLeftX < nextX) {
				// down or right
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
			}
		}
	}
	
	public void setAction() {
		if (onPath == true) {
			// Check if it stops chasing
			checkStopChasingOrNot(gp.player, 15, 30);				
			// Search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));		
		} else {
			// Check if it starts chasing
			checkStartChasingOrNot(gp.player, 5, 30);
			// Get a random direction
			getRandomDirection();
		}
		
		if (attacking == false) {
			if (rangedAttack == true) {
				checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize * 4);
			} else {
				checkAttackOrNot(30, gp.tileSize, gp.tileSize);
			}
		}
    }

	public void damageReaction() {
		actionLockCounter = 0;
	}
}
