# Tile Game Engine (OppoTile)


## Introduction
    This is a tile engine created by RyiSnow and improved on myself.

## Engine Customizability

### Tiles
    Tiles can be added by placing them into to res/tiles folder, and then adding them to the array in /src/tile/TileManager.java file.

### Player Movement
    Player movement is built in the src/engine/entity/Player.java class. Its sprite is located in the res/player folder.
    Spawn is set in the player class

### Objects (Due for change)
    Each individual object gets its own class, due to each item having differing uses and abilities. Object classes go in the object package in the src folder,  its sprite goes in objects in the res folder, and it's placement belongs in the AssetSetter class.

