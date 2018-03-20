package id.seruseru.tescanvas;


import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import id.seruseru.tescanvas.AntarMuka.FokusTeks;

public class Teks extends AppCompatEditText {
    //Inisiasi variabel
    private float sentuhanX, sentuhanY;
    private Context mContext;
    int aksiTerakhirYangDilakukan;
    private FokusTeks textFocusGained;
    private GestureDetector gestureDetector;
    Kanvas mKanvas;

    //Konstruktor
    public Teks(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    //Konstruktor
    public Teks(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    //Konstruktor
    public Teks(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    //Konstruktor tambahan
    private void init() {
        try {
            textFocusGained = (FokusTeks) mContext;
        } catch (ClassCastException ce) {
            Log.d("Error", "Error " + ce.toString());
        }
        gestureDetector = new GestureDetector(mContext, new GestureListener());

        setBackground(null);

        //setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        //setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        buildDrawingCache(true);
        setWillNotDraw(false);
        setDrawingCacheEnabled(true);
        setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
    }


    //Jika Textku terpasang/tertambahkan di window(view group)
    @Override
    protected void onAttachedToWindow() {
        setSelection(length());
        super.onAttachedToWindow();
    }

    //Jika Textku mendapatkan momen sentuhan di layar
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event); /* Memanggil gestureDetector */

        final int aksi = event.getActionMasked();

        switch (aksi) {
            case MotionEvent.ACTION_DOWN: /* jika ditekan */
                //Mengisi interface
                if (textFocusGained != null) {
                    textFocusGained.fokusTeksDidapat(this);
                }

                //Mendapatkan fokus
                dapatkanFokus();

                //Mengisi variable dengan posisi x dan y ketika disentuh
                sentuhanX = getX() - event.getRawX();
                sentuhanY = getY() - event.getRawY();

                aksiTerakhirYangDilakukan = MotionEvent.ACTION_DOWN;

                break;

            case MotionEvent.ACTION_MOVE: /* jika textku bergerak */

                //Memindah posisi Textku
                setY(event.getRawY() + sentuhanY);
                setX(event.getRawX() + sentuhanX);

                aksiTerakhirYangDilakukan = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP: /* jika momen sentuhan hilang dari layar */

                aksiTerakhirYangDilakukan = MotionEvent.ACTION_UP;
                break;
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public float getTextSize() {
        return super.getTextSize();
    }

    private void dapatkanFokus() {
        requestFocus();
        setFocusableInTouchMode(true);
        setFocusable(true);
    }

    private void hapusFokus() {
        setFocusableInTouchMode(false);
        setFocusable(false);
        clearFocus();
    }


    //Funsgi untuk memunculkan keyboard
    private void showSoftKeyboard(boolean buka) {

        InputMethodManager imm = (InputMethodManager) mContext.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (buka) {
            if (imm != null) {
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
            }
        } else {
            if (imm != null) {
                imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }
        }


    }

    public void setKanvas(Kanvas kanvas) {
        mKanvas = kanvas;
    }

    //Gesture Listener
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            showSoftKeyboard(true);
            return true;
        }
    }

}
