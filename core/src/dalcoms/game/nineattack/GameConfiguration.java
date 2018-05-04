package dalcoms.game.nineattack;

/**
 * Created by hslee on 2018-04-01.
 */

public class GameConfiguration {
    private static GameConfiguration instance = new GameConfiguration();

    private float viewportWidth = 720f;
    private float viewportHeight = 1280f;

    private float resizeFactor = 720f / 1080f;

    private String assetsFolder = "L_";

    public static final int TRACK_COUNT = 9;

    private float[] trackLocationXs;


    public static GameConfiguration getInstance() {
        return instance;
    }

    public void setTrackLocationXs(float[] locationXs) {
        trackLocationXs = new float[TRACK_COUNT];
        trackLocationXs = locationXs;
    }

    public float[] getTrackLocationXs() {
        return getInstance().trackLocationXs;
    }

    public float getTrackLocationX(int trackNum) {
        return trackLocationXs[trackNum];
    }

    public void setViewportSize(boolean isHigh) {
        if (isHigh) {
            viewportWidth = 1080f;
            viewportHeight = 1920f;
        } else {
            viewportWidth = 720f;
            viewportHeight = 1280f;
        }
        setAssetsFolder(isHigh);
        setResizeFactor(isHigh);
    }

    private void setAssetsFolder(boolean isHigh) {
        if (isHigh) {
            assetsFolder = "H_";
        } else {
            assetsFolder = "L_";
        }
    }

    public void setResizeFactor(boolean isHigh) {
        this.resizeFactor = isHigh ? 1f : 720f / 1080f;
    }

    public float getResizeFactor() {
        return this.resizeFactor;
    }

    public String getAssetsFolder() {
        return this.assetsFolder;
    }

    public float getViewportWidth() {
        return this.viewportWidth;
    }

    public float getViewportHeight() {
        return this.viewportHeight;
    }
}
