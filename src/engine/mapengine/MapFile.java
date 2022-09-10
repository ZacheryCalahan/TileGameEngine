package src.engine.mapengine;

public class MapFile {
    public int map[][];
    public int cols, rows;

    public MapFile(int cols, int rows, int[][] map) {
        this.cols = cols;
        this.rows = rows;
        this.map = map;
    }
}
