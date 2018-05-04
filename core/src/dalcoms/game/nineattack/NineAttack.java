package dalcoms.game.nineattack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NineAttack extends Game {
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;
    public GameConfiguration gameConfig;
    public ResourcesManager resourcesManager;
    private Preferences prefs;
    private int pref_highScore, pref_gameCnt, pref_inputMethod;
    private boolean pref_soundOn;

    @Override
    public void create() {
        gameConfig = GameConfiguration.getInstance();
        resourcesManager = ResourcesManager.getInstance();
        gameConfig.setViewportSize(Gdx.graphics.getWidth() > 800);
        resourcesManager.loadResoucesSplash(gameConfig.getAssetsFolder());
        resourcesManager.loadResouces();
        loadPreferences();

        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        this.setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        resourcesManager.dispose();

        spriteBatch.dispose();
        bitmapFont.dispose();
    }

    private void loadPreferences() {
        prefs = Gdx.app.getPreferences("nineAttackGamePreference");
        pref_highScore = prefs.getInteger("highScore", 0);
        pref_gameCnt = prefs.getInteger("gameCount", 1);
        pref_inputMethod = prefs.getInteger("inputMethod", 0);
        pref_soundOn = prefs.getBoolean("soundOn", true);
    }

    public void flushGamePreferences() {
        prefs.flush();
    }

    public void setPref_highScore(int pref_highScore) {
        this.pref_highScore = pref_highScore;
        prefs.putInteger("highScore", pref_highScore);
    }

    public void setPref_gameCnt(int pref_gameCnt) {
        this.pref_gameCnt = pref_gameCnt;
        prefs.putInteger("gameCount", pref_gameCnt);
    }

    public void setPref_inputMethod(int pref_inputMethod) {
        this.pref_inputMethod = pref_inputMethod;
        prefs.putInteger("inputMethod", pref_inputMethod);
    }

    public void setPref_soundOn(boolean pref_soundOn) {
        this.pref_soundOn = pref_soundOn;
        prefs.putBoolean("soundOn", pref_soundOn);
    }

    public int getPref_highScore() {
        return pref_highScore;
    }

    public int getPref_gameCnt() {
        return pref_gameCnt;
    }

    public int getPref_inputMethod() {
        return pref_inputMethod;
    }

    public boolean isPref_soundOn() {
        return pref_soundOn;
    }
}
