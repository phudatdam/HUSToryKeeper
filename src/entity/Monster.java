package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class Monster extends Entity {
	public boolean rangedAttack = false;

	public Monster(GamePanel gp) {
		super(gp);
		type = TYPE_MONSTER;
		solidArea = new Rectangle(12, 24, 40, 40);
	}
	
	public void attacking() {
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
	
	public void checkShoot(int shotInterval) {
    	if(projectile.alive == false) {
    		projectile.set(worldX, worldY, direction, true, this);
    		gp.projectileList.add(projectile);
    	}
	}
	
	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}			
		}
	}
	
	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}	
		}
	}
	
	public void checkAttackOrNot(int rate, int vertical, int horizontal) {
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
	
	public void searchPath(int goalCol, int goalRow) {
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
			
			// If reached the goal, stop the search
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if (nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}

}
