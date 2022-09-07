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


#TODO!

you left off with no npc collisions


# for Ryi:

Exception in thread "Thread-0" java.lang.NullPointerException: Cannot read field "ui" because "this.gp" is null
        at src.entity.NpcDummy.speak(NpcDummy.java:57)
        at src.entity.Player.interactNPC(Player.java:173)
        at src.entity.Player.update(Player.java:77)
        at src.engine.GamePanel.update(GamePanel.java:120)
        at src.engine.GamePanel.run(GamePanel.java:104)
        at java.base/java.lang.Thread.run(Thread.java:833)
