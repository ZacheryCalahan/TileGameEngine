package src.engine.mapengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MapLoader {
    String mapName;
    int mapData[][];
    int row, col;
    MapFile currentMap;

    public MapLoader() {

    }

    public void saveMap(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            //Saves the map dimensions
            writer.write(col);
            writer.write(row);

            // Saves the map
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    writer.write(mapData[i][j]);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public MapFile readMap(String filename) {
        int map[][];

        try {    
            BufferedReader br = new BufferedReader( new FileReader(filename));
            // Read Dimensions
            col = br.read();
            row = br.read();
            map = new int[col][row];

            // Read Mapdata
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    map[i][j] = br.read();
                }
            }
            br.close();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        currentMap = new MapFile(col, row, map);
        return currentMap;
    }
}
