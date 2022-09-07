package src.entity;

import java.util.Random;

// External

// Internal
import src.engine.GamePanel;

public class NpcDummy extends Entity {

    GamePanel gp;

    public NpcDummy(GamePanel gp) {
        super(gp);
 
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        downStill = setup("npc/dummy");
    }

    public void setDialogue() {
        dialogues[0] = "Hello, lad.";
    }

    @Override
    public void setAction() {
        // Where the behavior is determined.

        actionLockCounter++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(20) + 1;

            if (i == 1) {
                direction = "up";
            } else if (i == 2) {
                direction = "left";
            } else if (i == 3) {
                direction = "right";
            } else if (i == 4) {
                direction = "down";
            } else {
                direction = "still";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        gp.ui.currentDialogue = dialogues[0];
    }
}
