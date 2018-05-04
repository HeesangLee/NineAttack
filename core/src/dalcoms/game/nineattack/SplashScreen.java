package dalcoms.game.nineattack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-04-01.
 */

public class SplashScreen implements Screen {
    final NineAttack game;
    OrthographicCamera camera;

    private boolean flag_gameReady = false;

    private float[] trackLocationX;
    private float trackLocationRefY;

    private Me me;

    private Array<GameObject> gameObjects;
    private GameObject nineIcon, textDalcoms, textAttack, meCircle;

    private double angleMeCircle = 0d;

    private float timeout = 0;
    private final float TIME_OUT = 3f;


    public SplashScreen(final NineAttack game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, game.gameConfig.getViewportWidth(), game.gameConfig.getViewportHeight());
    }

    @Override
    public void show() {
        game.gameConfig.setTrackLocationXs(calTrackLocation(game.gameConfig.TRACK_COUNT));
        gameObjects = new Array<GameObject>();
        initGameObjects();
//        initMe();
    }

    @Override
    public void render(float delta) {
        this.clearScreen();
        this.draw(delta);
        if (this.checkScreenTimeout(delta)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
        game.resourcesManager.disposeSplash();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(44 / 255f, 53 / 255f, 63 / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private boolean checkScreenTimeout(float delta) {
        this.timeout += delta;

        return this.timeout > this.TIME_OUT;
    }

    private void draw(float delta) {
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);

        game.spriteBatch.begin();


//        drawBackgroundTracks(delta);
//        if (me != null) {
//            me.render(delta);
//        }
        setHoloCicleLocation(delta);
        drawGameObjects(delta);

        game.spriteBatch.end();
    }

    private void drawBackgroundTracks(float delta) {
        float trackY;
        if (flag_gameReady) {
            trackY = 0;
        } else {
            trackLocationRefY += delta * game.gameConfig.getViewportHeight() * 1.5;
            trackY = game.gameConfig.getViewportHeight() - trackLocationRefY;
            if (trackY < 0) {
                trackY = 0;
                flag_gameReady = true;
            }
        }
        for (int i = 0; i < game.gameConfig.TRACK_COUNT; i++) {
            game.spriteBatch.draw(game.resourcesManager.getTexture_track(), trackLocationX[i], trackY);
        }
    }


    private float[] calTrackLocation(int trackCount) {
        trackLocationX = new float[trackCount];

        float trackWidth = game.resourcesManager.getTexture_track().getWidth();
        float gap = (game.gameConfig.getViewportWidth() - trackWidth * trackCount) / trackCount;

        for (int i = 0; i < trackCount; i++) {
            this.trackLocationX[i] = (trackWidth + gap) * i + gap / 2;
        }

        return trackLocationX;
    }

    private void initMe() {
        me = new Me(game.resourcesManager.getTexture_me(),
                (game.gameConfig.getViewportWidth() - game.resourcesManager.getTexture_me().getWidth()) / 2,
                0)
                .setSpriteBatch(game.spriteBatch)
                .setTrack(4);
        me.show(true);
        me.moveY(0, game.gameConfig.getViewportHeight() - me.getHeight(), 3);
    }

    private void initGameObjects() {
        nineIcon = new GameObject(game.resourcesManager.getTexture_9icon(), 0, 0);
        textDalcoms = new GameObject(game.resourcesManager.getTexture_text_dalcoms_blue(), 0, 0);
        textAttack = new GameObject(game.resourcesManager.getTexture_text_attack_blue(), 0, 0);
        meCircle = new GameObject(game.resourcesManager.getTexture_circleMe(), 0, 0);

        nineIcon.setCenterLocation(game.gameConfig.getViewportWidth() / 2, game.gameConfig.getViewportHeight() / 2);
        nineIcon.setShow(true);

        textDalcoms.setLocationY(nineIcon.getLocationY() + nineIcon.getHeight() - textDalcoms.getHeight());
        textDalcoms.moveX(game.gameConfig.getViewportWidth(),
                game.gameConfig.getViewportWidth() / 2 - nineIcon.getWidth() - textDalcoms.getWidth(),
                0.5f);
        textDalcoms.setShow(true);

        textAttack.setLocationY(nineIcon.getLocationY());
        textAttack.moveX(-1f * textAttack.getWidth(),
                game.gameConfig.getViewportWidth() / 2 + nineIcon.getWidth(),
                0.5f);
        textAttack.setShow(true);

        meCircle.setShow(true);


        gameObjects.add(textDalcoms);
        gameObjects.add(textAttack);
        gameObjects.add(nineIcon);
        gameObjects.add(meCircle);
    }

    private void setHoloCicleLocation(float delta) {
        final double time = 2d;
        final double vel = 2 * Math.PI / time;
        float radius = 80f * game.gameConfig.getResizeFactor();

        angleMeCircle += vel * (double) delta;
        if (angleMeCircle > 2 * Math.PI) {
            angleMeCircle -= 2 * Math.PI;
        }
        if (meCircle != null) {
            meCircle.setCenterLocation((float) Math.cos(angleMeCircle) * radius + game.gameConfig.getViewportWidth() / 2,
                    (float) Math.sin(angleMeCircle) * radius + game.gameConfig.getViewportHeight() / 2);
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

}
