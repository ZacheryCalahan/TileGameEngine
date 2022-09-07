package src.entity;

// external
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// internal
import src.engine.GamePanel;
import src.engine.KeyHandler;



public class Player extends Entity {

    KeyHandler keyH;

    // Screen Vars
    public final int screenX;
    public final int screenY;    

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;


        // Camera pos
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Collision boundary
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // this is the spawn point
        worldX = gp.tileSize * 27;
        worldY = gp.tileSize * 11;
        speed = 4;
        direction = "down";
    }

    public void update() {
        // Detect movement
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Direction and Movement
            if(keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";                
            } if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // Collision
                //Tile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

                //Obj Collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check for NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

                //if col is false, can move
            if (collisionOn == false) {
                if (direction == "up") {
                    worldY -= speed;
                } else if (direction == "down") {
                    worldY += speed;
                } if (direction == "left") {
                    worldX -= speed;
                } else if (direction == "right") {
                    worldX += speed;
                }
            }

            // Animation
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

            
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "up":
            if (spriteNum == 1) {
                image = up1;
            } 
            if (spriteNum == 2) {
                image = up2;
            }

                break;
            case "down":
            if (spriteNum == 1) {
                image = down1;
            } 
            if (spriteNum == 2) {
                image = down2;
            }
                break;
            case "left":
            if (spriteNum == 1) {
                image = left1;
            } 
            if (spriteNum == 2) {
                image = leftStill;
            }
                break;
            case "right":
            if (spriteNum == 1) {
                image = right1;
            } 
            if (spriteNum == 2) {
                image = rightStill;
            }
                break;
            default:
                image = downStill;
        }

        g2.drawImage(image, screenX, screenY, null);
    }

    public void getPlayerImage() {
        up1 =           setup("player/player_up_1");
        up2 =           setup("player/player_up_2");
        upStill =       setup("player/player_up_still");
        down1 =         setup("player/player_down_1");
        down2 =         setup("player/player_down_2");
        downStill =     setup("player/player_down_still");
        left1 =         setup("player/player_left_1");
        leftStill =     setup("player/player_left_still");
        right1 =        setup("player/player_right_1");
        rightStill =    setup("player/player_right_still");
    }

    public void interactNPC(int i) {
        if (i != 999) {
            gp.gameState = gp.dialogueState;
            System.out.println("state set. index is " + i);
            gp.npc[i].speak();
            System.out.println("speak complete");
        }
    }
}
