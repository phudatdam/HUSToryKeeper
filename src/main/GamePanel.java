package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;

import ai.PathFinder;

import java.awt.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{
    // Cài đặt màn hình
    final int originalTileSize = 32; // 32 x 32 tile
    final int scale = 2;

    public final int tileSize = originalTileSize * 2; // 64 x 64 tile
    // public final int tileSize1 = originalTileSize * 2;
    public final int maxScreenCol = 16; // dài 16 ô
    public final int maxScreenRow = 10; // rộng 12 ô
    public final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    public final int screenHeight = maxScreenRow * tileSize;// 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50; // bản đồ dài 50 ô
    public final int maxWorldRow = 50; // bản đồ rộng 50 ô
    public final int maxMap = 4;
    public int currentMap = 0;


    // FPS : set Thời gian lặp lại
    int FPS = 60;

    // SYSTEM
    public TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public KeyHandler keyH = new KeyHandler(this);
    public AssetSetter aSetter  = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public CutsceneManager csManager = new CutsceneManager(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][30];
    public Entity npc[][] = new Entity[maxMap][5];
    public Entity monster[][] = new Entity[maxMap][10];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][200];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameoverState = 6;
    public final int cutsceneState = 7;

    //other
    public boolean cutsceneStart = false;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonsters();
        aSetter.setInteractiveTile();
        playMusic(0);
        // stopMusic();

        gameState = titleState;
    }

    public void retry(){
        player.setbackBegin();
        aSetter.setNPC();
        aSetter.setMonsters();
    }

    public void restart()
    {
        player.setDefaultValues();
        player.wood = 0;
        player.iron = 0;
        player.coin = 0;
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonsters();
        aSetter.setInteractiveTile();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Game loop

        double drawInterval = 1000000000 / FPS; // vẽ screen: cứ sau 1/60s để có thể vẻ màn hình 60 lần
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            // 1. Cập nhật: vị trí nhân vật,...
            update();

            // 2. Vẽ: vẽ lại screen with the update information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // trả lại thời gian cho lần vẽ tiếp theo
                remainingTime = remainingTime / 1000000; // chuyển từ nano -> mili

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; // 1/60s tiếp theo sẽ thực hiện tiếp tiếp tiếp...

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){ // update nhân vật sau mỗi vòng loop
        if(gameState == playState){
            // PLAYER
            player.update();

            // NPC
            for(int i = 0; i < npc[currentMap].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }

            // MONSTERS
            for(int i = 0; i < monster[currentMap].length; i++){
                if(monster[currentMap][i] != null){
                	if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                		monster[currentMap][i].update();
                	}
                	if(monster[currentMap][i].alive == false){
                		monster[currentMap][i] = null;
                	}
                }
            }

            // PROJECTILE
            for(int i = 0; i < projectileList.size(); i++)
            {
                if(projectileList.get(i) != null) {
                    if(projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            
            // INTERACTIVE TILES
            for(int i = 0; i < iTile[currentMap].length; i++){
                if(iTile[currentMap][i] != null){
                	iTile[currentMap][i].update();
                }
            }
        }

        if(gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){ // vẽ lại screen
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }

        // OTHERS
        else{
            // TILE
            tileM.draw(g2);
            
            // INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
            	if (iTile[currentMap][i] != null) {
            		iTile[currentMap][i].draw(g2);
            	}
            }

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITY LIST
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // EMPTY ENTITY LIST
            entityList.clear();

            

            // UI
            ui.draw(g2);

            // cutscene
            csManager.draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}