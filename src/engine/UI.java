package src.engine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;

import src.object.ObjHeart;
import src.object.SuperObject;


public class UI {
    
    GamePanel gp;
    Font arial_40;
    Graphics2D g2;

    BufferedImage heart_full, heart_half, heart_empty;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        // HUD
        SuperObject heart = new ObjHeart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            drawPlayerLife();
        }
        
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawPauseScreen() {
        // calc center of screen
        String text = "Paused";
        int y = gp.screenHeight / 2;
        int x = getXforCenteredText(text);
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 7;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
        
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(156, 234, 239));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Game Title";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        //main color
        g2.setColor(Color.GRAY);
        g2.drawString(text, x, y);

        // Character image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize *2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "PLAY";
        x = getXforCenteredText(text);
        y+= gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "OPTIONS";
        x = getXforCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawPlayerLife() {

        int offset = 4;
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize + offset;
        }

        //reset values to draw current life
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize + offset;
        }


    }

    public void drawSubWindow(int x, int y, int width, int height) {
        
        Color c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length/2;
        return x;
        
    }
}
