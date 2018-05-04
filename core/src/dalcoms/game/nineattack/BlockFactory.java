package dalcoms.game.nineattack;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by hslee on 2018-04-29.
 */

public class BlockFactory {
    final NineAttack game;
    final GameScreen gameScreen;

    private boolean flag_run = false;

    private Array<Block> blocks;
    private Array<Block> blockBank;

    private float timeBlockOnScreen = 10f;
    private float timeForNewBlock = timeBlockOnScreen / 25;
    private float secForNewBlockTimer = 0f;

    private float screenHeight;
    private float[] trackLocationXs;

    private MapCreator mapCreator;


    public BlockFactory(NineAttack game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        blocks = new Array<Block>();
        blockBank = new Array<Block>();
        screenHeight = game.gameConfig.getViewportHeight();
        trackLocationXs = game.gameConfig.getTrackLocationXs();

        mapCreator = new MapCreator(9, 500, 4);

        prepareBlockBank(50);
    }

    public void render(float delta) {
        if (isFactoryRunning()) {
            if (isTimeToCheckNewBlock(delta)) {
                checkNewBlocks();
            }
            renderBlocks(delta);
        }
    }

    private void renderBlocks(float delta) {
        for (Iterator<Block> it = blocks.iterator(); it.hasNext(); ) {
            it.next().render(delta);
        }
    }


    private boolean isTimeToCheckNewBlock(float delta) {
        boolean ret = false;
        secForNewBlockTimer += delta;
        if (secForNewBlockTimer > getTimeForNewBlock()) {
            secForNewBlockTimer = 0;
            ret = true;
        }
        return ret;
    }

    private void checkNewBlocks() {

        ItemTag[] temp = mapCreator.getMapArray().get(0);
        mapCreator.getMapArray().removeIndex(0);
        for (int i = 0; i < 9; i++) {
            if(temp[i].getItemKind()==Item.ITEM_BLOCK){
                Block tempBlock = getBlockFromBlockBank();
                tempBlock.show(true);
                tempBlock.setLocation(trackLocationXs[i], screenHeight);
                tempBlock.moveY(screenHeight, 0, timeBlockOnScreen);
                blocks.add(tempBlock);
            }
        }
    }

    public void runFactory(boolean flagRun) {
        flag_run = flagRun;
    }

    public void runFactory() {
        flag_run = true;
    }

    public void stopFactory() {
        flag_run = false;
    }

    public boolean isFactoryRunning() {
        return flag_run;
    }

    private void prepareBlockBank(int blockCNum) {
        for (int i = 0; i < blockCNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    addBlockToBlockBank();
                }
            });
        }
    }

    private void addBlockToBlockBank() {
        blockBank.add(new Block(game.resourcesManager.getTexture_block(),
                0,
                game.gameConfig.getViewportHeight())
                .setSpriteBatch(game.spriteBatch)
                .setResizeFactor(game.gameConfig.getResizeFactor())
                .setBaseColor(GameColor.blockBase));
    }


    private Block getBlockFromBlockBank() {
        if (blockBank.size != 0) {
            return blockBank.pop();
        } else {
            return new Block(game.resourcesManager.getTexture_block(),
                    0,
                    game.gameConfig.getViewportHeight())
                    .setSpriteBatch(game.spriteBatch)
                    .setResizeFactor(game.gameConfig.getResizeFactor())
                    .setBaseColor(GameColor.blockBase);
        }
    }

    public float getTimeBlockOnScreen() {
        return timeBlockOnScreen;
    }

    public void setTimeBlockOnScreen(float timeBlockOnScreen) {
        this.timeBlockOnScreen = timeBlockOnScreen;
        setTimeForNewBlock(timeBlockOnScreen / 25f);
    }

    public float getTimeForNewBlock() {
        return timeForNewBlock;
    }

    public void setTimeForNewBlock(float timeForNewBlock) {
        this.timeForNewBlock = timeForNewBlock;
    }
}
