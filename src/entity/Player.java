package src.entity;

// external
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// internal
import src.engine.GamePanel;
import src.engine.KeyHandler;



public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Keys: "+hasKey);
                    break;
                case "Chest":
                if (hasKey > 0) {
                    gp.obj[i] = null;
                    hasKey--;
                    System.out.println("Lost one key. Keys: "+hasKey);
                    break;
                }
            }
            
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void getPlayerImage() {
        try {
            up1 =           ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_1.png"));
            up2 =           ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_2.png"));
            upStill =       ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_still.png"));
            down1 =         ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_1.png"));
            down2 =         ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_2.png"));
            downStill =     ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_still.png"));
            left1 =         ImageIO.read(getClass().getResourceAsStream("/res/player/player_left_1.png"));
            leftStill =     ImageIO.read(getClass().getResourceAsStream("/res/player/player_left_still.png"));
            right1 =        ImageIO.read(getClass().getResourceAsStream("/res/player/player_right_1.png"));
            rightStill =    ImageIO.read(getClass().getResourceAsStream("/res/player/player_right_still.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
