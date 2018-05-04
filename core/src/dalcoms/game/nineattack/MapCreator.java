package dalcoms.game.nineattack;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by hslee on 2018-05-02.
 */

public class MapCreator {
    final static int DIR_FORWARD = 0;
    final static int DIR_LEFT = 1;
    final static int DIR_RIGHT = 2;
    private int preDir = 0;

    boolean[][] safePathMap;
    //    Array<boolean[]> safePathArray; //temp for test
    private int mapXSize = 9;
    Array<ItemTag[]> mapArray;

    public MapCreator(int mapX, int mapY, int initX) {
        mapXSize = mapX;
        this.safePathMap = new boolean[mapX][mapY];
        mapArray = new Array<ItemTag[]>();
        createMap(initX);

    }

    public void createMap(int initX) {
        clearSafeMapArray();
        createSafePath(initX);
        createMapUsingSafePath();
    }

    private void clearSafeMapArray() {
        for (int x = 0; x < this.safePathMap.length; x++) {
            for (int y = 0; y < this.safePathMap[0].length; y++) {
                this.safePathMap[x][y] = false;
            }
        }
    }

    private void createSafePath(int initX) {
        int xPos = initX;
        int yPos = 0;
        int yLen = this.safePathMap[0].length;
        int dir;

        this.safePathMap[xPos][yPos] = true;

        for (int y = 0; y < yLen - 1; y++) {
            dir = getDirection(xPos);
            yPos = y;

            switch (dir) {
                case DIR_FORWARD:// forward
                    this.safePathMap[xPos][yPos + 1] = true;
                    break;
                case DIR_LEFT: // left
                    this.safePathMap[xPos][yPos + 1] = true;
                    this.safePathMap[xPos - 1][yPos] = true;
                    this.safePathMap[xPos - 1][yPos + 1] = true;
                    --xPos;
                    break;
                case DIR_RIGHT: // right
                    this.safePathMap[xPos][yPos + 1] = true;
                    this.safePathMap[xPos + 1][yPos] = true;
                    this.safePathMap[xPos + 1][yPos + 1] = true;
                    ++xPos;
                    break;
            }
        }

    }

    private void createMapUsingSafePath() {
        Random rand = new Random();
        boolean safePathBulletOn = false;

        for (int yy = 0; yy < safePathMap[0].length; yy++) {
            ItemTag[] temp = new ItemTag[mapXSize];

            for (int xx = 0; xx < mapXSize; xx++) {
                if (safePathMap[xx][yy] == true) {
                    if (yy % 31 == 30) {
                        if (safePathBulletOn) {
                            temp[xx] = new ItemTag(Item.ITEM_BULLET, Item.BULLET_1);
                        } else {
                            temp[xx] = new ItemTag(Item.ITEM_BLOCK);
                        }
                        safePathBulletOn = !safePathBulletOn;
                    } else if (yy % 10 < 5) {
                        temp[xx] = new ItemTag(Item.ITEM_COIN);
                    } else {
                        temp[xx] = new ItemTag(Item.ITEM_BLANK);
                    }
                } else {
                    if (rand.nextBoolean()) {
                        if (yy % 10 < 5) {
                            temp[xx] = new ItemTag(Item.ITEM_BLANK);
                        } else {
                            temp[xx] = new ItemTag(Item.ITEM_COIN);
                        }
                    } else {
                        temp[xx] = new ItemTag(Item.ITEM_BLOCK);
                    }
                }
            }
            mapArray.add(temp);
        }
    }

    private int getDirection(int xPos) {

        int xLen = this.safePathMap.length;

        Random rand = new Random();
        int direction;// = rand.nextInt(3); //0 : forward, 1: left, 2: right

        if (rand.nextInt(5) < 2) {
            direction = DIR_FORWARD;
        } else {
            direction = rand.nextBoolean() ? DIR_LEFT : DIR_RIGHT;
        }

        if ((xPos == 0) && (direction == DIR_LEFT)) {
            direction = rand.nextInt(10) < 3 ? 0 : 2;
        }
        if ((xPos == xLen - 1) && (direction == DIR_RIGHT)) {
            direction = rand.nextInt(10) < 3 ? 0 : 1;
        }
        preDir = direction;

        return direction;
    }


    public Array<ItemTag[]> getMapArray() {
        return this.mapArray;
    }

}
