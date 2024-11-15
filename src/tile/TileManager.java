package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile; // mảng chứa tile
    public int mapTileNum[][][]; // mảng 3 chiều chứa thứ tự bản đồ và tọa độ tile của mỗi bản đồ

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[70]; // set số lượng tile <= 70
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt", 1);
        loadMap("/maps/world02.txt", 2);
        loadMap("/maps/world03.txt", 3);
    }

    public void getTileImage() {
    	// grass
    	setup(0, "grass_1", false); // cỏ loại 1
    	setup(1, "grass_2", false); // cỏ loại 2
    	// resources
    	setup(10, "bush", true); // bụi cây
    	setup(11, "rock", true); // đá
    	setup(12, "tree_chopped", false); // cây gỗ đã bị chặt
    	setup(13, "iron_ore_mined", false); // sắt đã bị đào
    	setup(14, "tree_1", true); // cây gỗ
    	// path
    	setup(20, "path_vertical", false); // đường dọc
    	setup(21, "path_horizontal", false); // đường ngang
    	setup(22, "path_intersection", false); // đường giao nhau
    	setup(23, "path_corner_north_east", false); // góc đường hướng đông bắc
    	setup(24, "path_corner_north_west", false); // góc đường hướng tây bắc
    	setup(25, "path_corner_south_east", false); // góc đường hướng đông nam
    	setup(26, "path_corner_south_west", false); // góc đường hướng tây nam
    	// water
    	setup(30, "water", true); // nước
    	setup(31, "water_edge_east", true); // cạnh nước hướng đông
    	setup(32, "water_edge_west", true); // cạnh nước hướng tây
    	setup(33, "water_edge_south", true); // cạnh nước hướng nam
    	setup(34, "water_edge_north", true); // cạnh nước hướng bắc
    	setup(35, "water_corner_north_east", true); // góc nước hướng đông bắc
    	setup(36, "water_corner_north_west", true); // góc nước hướng tây bắc
    	setup(37, "water_corner_south_east", true); // góc nước hướng đông nam
    	setup(38, "water_corner_south_west", true); // góc nước hướng tây nam
    	setup(39, "water_inner_corner_north_east", true); // mép nước trong hướng đông bắc
    	setup(40, "water_inner_corner_north_west", true); // mép nước trong hướng tây bắc
    	setup(41, "water_inner_corner_south_east", true); // mép nước trong hướng đông nam
    	setup(42, "water_inner_corner_south_west", true); // mép nước trong hướng tây nam
    	// wall
    	setup(50, "wall_east", true); // tường hướng đông
    	setup(51, "wall_west", true); // tường hướng tây
    	setup(52, "wall_north", true); // tường hướng bắc
    	setup(53, "wall_south", true); // tường hướng nam
    	setup(54, "wall_corner_north_east", true); // tường hướng đông bắc
    	setup(55, "wall_corner_north_west", true); // tường hướng tây bắc
    	setup(56, "wall_corner_south_east", true); // tường hướng đông nam
    	setup(57, "wall_corner_south_west", true); // tường hướng tây nam
    	setup(58, "wall_inner_corner_north_east", true); // góc tường trong hướng đông bắc
    	setup(59, "wall_inner_corner_north_west", true); // góc tường trong hướng tây bắc
    	setup(60, "wall_inner_corner_south_east", true); // góc tường trong hướng đông nam
    	setup(61, "wall_inner_corner_south_west", true); // góc tường trong hướng tây nam
    	
    }
    
    // Công cụ vẽ hình tile
    public void setup(int index, String imagePath, boolean collision) {
    	
    	UtilityTool uTool = new UtilityTool();
    	try {
    		// Tạo tile mới trong mảng chứa tile
    		tile[index] = new Tile();
    		
    		// Fetch ảnh gốc vào tile
    		tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")); 
    		
    		// Gán ảnh cho tile rồi scale ảnh gốc về kích thước tile
    		tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    		
    		// Gán thuộc tính va chạm cho tile
    		tile[index].collision = collision;
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }

    public void loadMap(String filePath, int map){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        // Bổ sung tile lề thêm bằng cách tăng số lượng tile được vẽ
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Vẽ tile nằm trong phạm vi màn hình và mở rộng thêm tile ngoài viền để tránh "sọc"
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize) {

                // Vẽ tile nếu nằm trong màn hình hoặc nằm trong lề được mở rộng
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
