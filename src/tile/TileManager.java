package src.tile;


// external
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

// internal
import src.engine.GamePanel;
import src.engine.UtilityTool;
import src.engine.mapengine.MapFile;
import src.engine.mapengine.MapLoader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    MapLoader mLoader = new MapLoader();
    MapFile currentMap;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMapDat("res/maps/map01.dat");
        //loadMap("res/maps/map02.txt");
        
    }

    public void getTileImage() {
        // This is where tiles are initiated for use.
        setup(0, "tile_grass", false);
        setup(1, "tile_brick", true);
        setup(2, "tile_water", true);
        setup(3, "tile_dirt", false);
        setup(4, "tile_tree", true);
        setup(5, "tile_sand", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        // This is a helper function for setting up tile images and scaling them automagically.
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+ imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            //gets the current tile to draw
            int tileNum = mapTileNum[worldCol][worldRow];

            // camera calculations to draw current tile
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // draw current tile if on screen and ready for next tile
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            
            worldCol++;

            // when tile is at max col, return to next row
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
    }

    @Deprecated
    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void loadMapDat(String mapPath) {
        currentMap = mLoader.readMap(mapPath);
        gp.maxWorldCol = currentMap.cols;
        gp.maxWorldRow = currentMap.rows;
        gp.setWorldData();
    }
}
