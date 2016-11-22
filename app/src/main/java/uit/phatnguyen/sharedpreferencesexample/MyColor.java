package uit.phatnguyen.sharedpreferencesexample;

/**
 * Created by PhatNguyen on 2016-10-31.
 */

public class MyColor {
    private String colorKey;
    private int colorValue;

    public MyColor() {
    }
    public MyColor(String colorKey, int colorValue) {
        this.colorKey = colorKey;
        this.colorValue = colorValue;
    }

    public String getColorKey() {
        return colorKey;
    }

    public void setColorKey(String colorKey) {
        this.colorKey = colorKey;
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }

    @Override
    public String toString() {
        return "Key :"+this.colorKey + " Value :"+this.colorValue;
    }
}
