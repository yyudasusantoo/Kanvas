package id.seruseru.tescanvas.PilihanDrawable;

import android.graphics.drawable.Drawable;

/**
 * Created by Yuda Susanto on 3/19/2018.
 */

public class PilihanDrawable {
    public PilihanDrawable(int[] warna) {
        this.warna = warna;
    }

    public int[] getWarna() {
        return warna;
    }

    public void setWarna(int[] warna) {
        this.warna = warna;
    }

    private int[] warna;

}
