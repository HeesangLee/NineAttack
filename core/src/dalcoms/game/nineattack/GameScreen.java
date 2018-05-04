package dalcoms.game.nineattack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-03-20.
 */

public class GameScreen implements Screen {
    final NineAttack game;
    OrthographicCamera camera;

    private boolean flag_gameStart = false;
    private float[] trackLocationX;
    private Array<GameObject> gameObjects;
    private Array<GameObject> holoTitles;
    private final int HOLO_TITLE_CNT = 5;
    private final float CENTER_Y_TITLE = 1610f;

    private GameObject text_nineAttackTitle, text_tabToStart;
    private Me me;
    private InfoImageNum highScore;

    private BlockFactory blockFactory;


    public GameScreen(final NineAttack game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, game.gameConfig.getViewportWidth(), game.gameConfig.getViewportHeight());
    }

    @Override
    public void show() {
        gameObjects = new Array<GameObject>();

        initBlockFactory();
        initGameObjects();

        initInputProcessor();
    }

    @Override
    public void render(float delta) {
        this.clearScreen();
        this.draw(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.resourcesManager.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(44 / 255f, 53 / 255f, 63 / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void draw(float delta) {
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);

        game.spriteBatch.begin();

        drawBackgroundTracks(delta);
        drawGameObjects(delta);
        if (!isGameStarted()) {
            drawHoloTitles(delta);
        }

        blockFactory.render(delta);
        me.render(delta);
        highScore.render(delta);


        game.spriteBatch.end();
    }

    private void drawBackgroundTracks(float delta) {
        for (int i = 0; i < game.gameConfig.TRACK_COUNT; i++) {
            game.spriteBatch.draw(game.resourcesManager.getTexture_track(),
                    trackLocationX[i], 0f);
        }
    }

    private void drawGameObjects(float delta) {
        for (GameObject g : gameObjects) {
            if (g.isShow()) {
                g.render(delta);
                game.spriteBatch.draw(g.getTexture(), g.getLocationX(), g.getLocationY());
            }
        }
    }


    private void drawHoloTitles(float delta) {
        final float xGap = game.gameConfig.getViewportWidth() / (this.HOLO_TITLE_CNT + 1);
        final float radius = xGap / 3;

        for (int i = 0; i < holoTitles.size; i++) {
            if (holoTitles.get(i).isShow()) {
                holoTitles.get(i)
                        .setTempDouble(holoTitles.get(i).getTempDouble()
                                + 2 * Math.PI / (double) holoTitles.get(i).getTempFloat() * (double) delta);

                if (holoTitles.get(i).getTempDouble() > 2 * Math.PI) {
                    holoTitles.get(i).setTempDouble(holoTitles.get(i).getTempDouble() - 2 * Math.PI);
                }

                holoTitles.get(i)
                        .setCenterLocation(
                                (float) Math.cos(holoTitles.get(i).getTempDouble()) * radius + xGap * (i + 1),
                                (float) Math.sin(holoTitles.get(i).getTempDouble()) * radius + this.CENTER_Y_TITLE);
                game.spriteBatch
                        .draw(holoTitles.get(i).getTexture(), holoTitles.get(i).getLocationX(), holoTitles.get(i).getLocationY());
            }
        }
    }

    public boolean isGameStarted() {
        return flag_gameStart;
    }

    public void setGameStartFlag(boolean start) {
        this.flag_gameStart = start;
    }


    private void initBlockFactory() {
        blockFactory = new BlockFactory(this.game, this);
    }

    private void initGameObjects() {
        initTrack();
        initHoloTitles();
        initMe();
        initHighScore();

        text_nineAttackTitle = new GameObject(game.resourcesManager.getTexture_text_nineAttack(),
                0, 0, true);
        text_nineAttackTitle.setCenterLocation(game.gameConfig.getViewportWidth() / 2,
                game.gameConfig.getResizeFactor() * CENTER_Y_TITLE);

        text_tabToStart = new GameObject(game.resourcesManager.getTexture_text_tabToStart(),
                0, 0, true);
        text_tabToStart.setCenterLocation(game.gameConfig.getViewportWidth() / 2,
                game.gameConfig.getViewportHeight() / 2);

        gameObjects.add(text_nineAttackTitle);
        gameObjects.add(text_tabToStart);

    }

    private void initMe() {
        me = new Me(game.resourcesManager.getTexture_me(),
                (game.gameConfig.getViewportWidth() - game.resourcesManager.getTexture_me().getWidth()) / 2,
                0)
                .setSpriteBatch(game.spriteBatch)
                .setTrack(4)
                .setTrackInformation(trackLocationX, game.resourcesManager.getTexture_track().getWidth())
                .setMovingEffectTexture(game.resourcesManager.getTexture_meMovingEffect())
                .setHoloTexture(game.resourcesManager.getTexture_circleMe())
                .setResizeFactor(game.gameConfig.getResizeFactor());

        me.show(true);
        me.moveY(-1 * me.getHeight(), game.gameConfig.getResizeFactor() * 338f, 0.5f);
    }

    private void initHighScore() {
        highScore = new InfoImageNum(game.resourcesManager.getTexture_thumbUp(),
                game.gameConfig.getResizeFactor() * 24f,
                game.gameConfig.getResizeFactor() * 1798f,
                true)
                .setSpriteBatch(game.spriteBatch)
                .setResizeFactor(game.gameConfig.getResizeFactor())
                .setTexureNumArray(game.resourcesManager.getNumInforSmallArray());

        highScore.setGapImageNum(game.gameConfig.getResizeFactor() * 20f);
        highScore.setGapNums(game.gameConfig.getResizeFactor() * 7f);
        highScore.moveX(-1 * highScore.getWidth(), game.gameConfig.getResizeFactor() * 24f, 1f);
        highScore.setNumber(game.getPref_highScore());

    }

    private void initHoloTitles() {
        final float xGap = game.gameConfig.getViewportWidth() / (this.HOLO_TITLE_CNT + 1);
        holoTitles = new Array<GameObject>();
        for (int i = 0; i < HOLO_TITLE_CNT; i++) {
            holoTitles.add(new GameObject(game.resourcesManager.getTexture_circleMe(),
                    0, 0, true));
        }
        for (int i = 0; i < HOLO_TITLE_CNT; i++) {
            holoTitles.get(i)
                    .setCenterLocation(xGap * (i + 1), this.CENTER_Y_TITLE);
        }
        holoTitles.get(0).setTempFloat(6f);
        holoTitles.get(1).setTempFloat(4.5f);
        holoTitles.get(2).setTempFloat(5.5f);
        holoTitles.get(3).setTempFloat(8f);
        holoTitles.get(4).setTempFloat(6f);
    }

    private void initTrack() {
        trackLocationX = game.gameConfig.getTrackLocationXs();
    }

    private void startGame() {
        setGameStartFlag(true);
        holoTitles = null;
        moveMeToInitialPosition();
        removeReadyTextures();
        blockFactory.runFactory();
    }

//    private void testBlock() {
//        testBlock = new Block(game.resourcesManager.getTexture_block(),
//                game.gameConfig.getTrackLocationX(4),
//                game.gameConfig.getViewportHeight())
//                .setSpriteBatch(game.spriteBatch).
//                        setResizeFactor(game.gameConfig.getResizeFactor())
//                .setBaseColor(GameColor.blockBase);
//
//        testBlock.show(true);
//        testBlock.moveY(testBlock.getLocationY(), 0, 3f);
//    }

    private void moveMeToInitialPosition() {
        me.moveY(me.getLocationY(), game.gameConfig.getResizeFactor() * 650f, 0.5f);
    }

    private void removeReadyTextures() {//remove textures which used in ready game state such as title text
//        move down and remove it
        text_tabToStart.moveY(text_tabToStart.getLocationY(), -1 * text_tabToStart.getHeight(), 1f);
        text_tabToStart.addActionListener(new GameObjectActionListener() {
            @Override
            public boolean onMoveCompleted(boolean direction) {
                text_tabToStart.setShow(false);
                gameObjects.removeValue(text_tabToStart, false);
                return false;
            }
        });

        text_nineAttackTitle.moveY(text_tabToStart.getLocationY(),
                -1 * text_nineAttackTitle.getHeight(), 1.2f);
        text_nineAttackTitle.addActionListener(new GameObjectActionListener() {
            @Override
            public boolean onMoveCompleted(boolean direction) {
                text_nineAttackTitle.setShow(false);
                gameObjects.removeValue(text_nineAttackTitle, false);
                return false;
            }
        });


    }

    private void initInputProcessor() {
        Gdx.input.setInputProcessor(new GestureDetector(new GameGestureListener() {
            @Override
            public boolean tap(float x, float y, int count, int button) {

                if (!isGameStarted()) {
                    startGame();
                }
                return !isGameStarted();
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {

                Gdx.app.log("fling", "x : " + String.valueOf(velocityX) +
                        ",y : " + String.valueOf(velocityY) + " btn : " + String.valueOf(button));


                if (velocityX > 0) {
                    me.moveToTrack(Me.MOVE_RIGHT);
                } else {
                    me.moveToTrack(Me.MOVE_LEFT);
                }

                return isGameStarted();
            }
        }));
    }
}
