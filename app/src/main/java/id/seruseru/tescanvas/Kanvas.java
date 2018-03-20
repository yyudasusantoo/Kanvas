package id.seruseru.tescanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import id.seruseru.tescanvas.AntarMuka.FokusKanvas;

public class Kanvas extends ViewGroup {
    private int mWidth, bitmapWidth, lebarUtama;
    private int mHeight, bitmapHeight, tinggiUtama;
    private boolean bitmapMode;
    private boolean drawableMode = true;
    private boolean pertamaKali = true;
    private FokusKanvas focusGainer;
    private Drawable mDrawable;
    private int mColor;
    Context mContext;
    Bitmap myBitmap;
    SimpleTarget sTarget;
    float y1, y2,y3;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    //Konstruktor
    public Kanvas(Context context) {
        super(context);
        mContext = context;
        mulai();
    }

    //Konstruktor
    public Kanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mulai();
    }

    //Konstruktor
    public Kanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        mulai();
    }

    //Konstruktor tambahan
    private void mulai() {
        try {
            focusGainer = (FokusKanvas) mContext;
        } catch (ClassCastException ce) {
            Log.d("Error", "Error " + ce.toString());
        }
        buildDrawingCache(true);
        setWillNotDraw(false);
        setDrawingCacheEnabled(true);
        setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
    }

    //Mengatur ukuran Kanvas
    //Dipanggil ulang menggunakan perintah requestLayout();
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Mengambil ukuran Kanvas saat ini
        mWidth = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.getMode(MeasureSpec.UNSPECIFIED));
        mHeight = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.getMode(MeasureSpec.UNSPECIFIED));
        //Menyimpan ukuran kanvas pertama kali dan disimpan dalam variable @lebarUtama dan @tinggiUtama
        if (isPertamaKali()) {
            lebarUtama = mWidth;
            tinggiUtama = mHeight;

        } else if (isBitmapMode()) {
            //mWidth = bitmapWidth;
            //mHeight = bitmapHeight;

            mWidth = bitmapWidth;
            mHeight = bitmapHeight;
        } else if (isDrawableMode()) {
            mWidth = lebarUtama;
            mHeight = tinggiUtama;
        }

        Log.d("KANVAS", "ukuran utama " + lebarUtama + " " + tinggiUtama);
        setMeasuredDimension(mWidth,
                mHeight);
    }

    //Mengatur ukuran tampilan 'child view' didalam Kanvas
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        int curWidth, curHeight, curLeft, curTop, maxHeight;

        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;
        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                return;
            }

            //Get the maximum size of the child
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.UNSPECIFIED));
            curWidth = child.getMeasuredWidth() + 20;
            curHeight = child.getMeasuredHeight();
            //wrap is reach to the end
            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            //do the layout
            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);

        }

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    //Mengatur penampilan Kanvas
    @Override
    protected void onDraw(Canvas canvas) {
        //Mendapatkan mode & menggambar Kanvas sesuai mode tersebut
        if (isPertamaKali()) {
            canvas.drawColor(Color.GREEN);
        } else if (isBitmapMode()) {
            canvas.drawBitmap(myBitmap, 0, 0, null);
        } else if (isDrawableMode()) {
            if (mDrawable != null) {
                mDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                mDrawable.draw(canvas);
            } else {
                canvas.drawColor(mColor);
            }
        }
    }

    //Menginisiasi Kanvas agar menerima sentuhan layar
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (performClick()) {
            requestFocus();
            setFocusable(true);
            setFocusableInTouchMode(true);
            return true;
        }
        return true;
    }

    //Aku gak tau buat apa ini.. tapi masih nyambung sama onTouchEvent
    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    //Jika Kanvas mendapatkan fokus
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        if (gainFocus) {
            if (focusGainer != null) {
                focusGainer.fokusKanvasDidapat(this);
            }
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }



    //Mengubah background dengan bitmap (kamera/galeri)
    public void setMyBitmap(Bitmap gambar) {
        if (gambar != null) {
            if (myBitmap != null) {
                myBitmap.recycle();
                myBitmap = null;
            }
            boolean portrait = false;
            boolean lansdcape = false;
            bitmapMode = true;
            drawableMode = false;
            pertamaKali = false;


            int lebarGambar = gambar.getWidth();
            int tinggiGambar = gambar.getHeight();
            float lebarnya = 0;
            float tingginya = 0;

            float rasioGambar = (float) lebarGambar / (float) tinggiGambar;
            float rasioLebar = (float) lebarUtama / lebarGambar;
            float rasioTinggi = (float) tinggiUtama / tinggiGambar;
            float rasioKanvas = (float) lebarUtama / (float) tinggiUtama;


            if (lebarGambar > tinggiGambar) {
                lansdcape = true;
                portrait = false;
            } else if (lebarGambar < tinggiGambar) {
                lansdcape = false;
                portrait = true;
            }

            if (lansdcape) {
                lebarnya = (float) lebarGambar * rasioLebar;
                tingginya = lebarnya / rasioGambar;
            }
            if (portrait) {
                tingginya = (float) tinggiGambar * rasioTinggi;
                lebarnya = tingginya * rasioGambar;
            }
            bitmapWidth = (int) lebarnya;
            bitmapHeight = (int) tingginya;

            myBitmap = Bitmap.createScaledBitmap(gambar, bitmapWidth, bitmapHeight, true);

            requestLayout();
            invalidate();
        }
        gambar.recycle();
    }

    public void latarBelakangGambar(Uri uri) {

        bersihBersih();
        bitmapMode = true;
        drawableMode = false;
        pertamaKali = false;


        sTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition transition) {
                setMyBitmap(bitmap);
            }
        };
        Glide.with(this)
                .asBitmap()
                .load(uri)
                .into(sTarget);
    }

    //Mengambil drawable dan menginisisai variabel mDrawable
    public void latarBelakangGradien(Drawable drawableku) {
        bersihBersih();
        bitmapMode = false;
        drawableMode = true;
        pertamaKali = false;

        mDrawable = drawableku;
        requestLayout();
        invalidate();
    }

    //Mengubah background (warna dasar)
    public void latarBelakangWarna(int color) {
        bersihBersih();
        mColor = color;

        bitmapMode = false;
        drawableMode = true;
        pertamaKali = false;

        requestLayout();
        invalidate();
    }

    private void bersihBersih() {
        if (sTarget != null) {
            Glide.with(this).clear(sTarget);
        }
        if (mColor != 0) {
            mColor = 0;
        }
        if (mDrawable != null) {
            mDrawable = null;
        }
    }

    //Getter method untuk bitmapMode
    public boolean isBitmapMode() {
        return bitmapMode;
    }

    //Getter method untuk drawableMode
    public boolean isDrawableMode() {
        return drawableMode;
    }

    //Getter method untuk pertama kali dibuka
    public boolean isPertamaKali() {
        return pertamaKali;
    }


}
