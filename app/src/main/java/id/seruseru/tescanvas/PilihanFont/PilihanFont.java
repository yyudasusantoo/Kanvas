package id.seruseru.tescanvas.PilihanFont;

import android.graphics.Typeface;

/**
 * Created by Yuda Susanto on 3/18/2018.
 */

public class PilihanFont {
    private Typeface jenisFont;

    public PilihanFont(Typeface jenisFont) {
        this.jenisFont = jenisFont;
    }

    public Typeface getmTypeFace() {
        return jenisFont;
    }

    public void setmTypeFace(Typeface jenisFont) {
        this.jenisFont = jenisFont;
    }
}
