package src.entity;

import java.util.Random;

// External

// Internal
import src.engine.GamePanel;

public class NpcDummy extends Entity {

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
        dialogues[0] = "You look silly talking to a dummy. \n silly...";
        dialogues[1] = "Are you having fun?";
        dialogues[2] = "The code writer is sadistic.";
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
        // This is left for character specific stuffs.

        super.speak();
    }
}
