package id.seruseru.tescanvas;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import id.seruseru.tescanvas.AntarMuka.FokusKanvas;
import id.seruseru.tescanvas.AntarMuka.FokusTeks;
import id.seruseru.tescanvas.FragmenUtama.FragmentKanvas;
import id.seruseru.tescanvas.FragmenUtama.FragmenTeks;


public class MainActivity extends AppCompatActivity implements FokusTeks, FokusKanvas {
    private FragmentKanvas fragmentKanvas;
    private FragmenTeks fragmenTeks;
    private Kanvas kanvas;
    private Teks teks;
    private FragmentTransaction ft;
    FloatingActionButton simpanKanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        kanvas = findViewById(R.id.hasilGambar);
        simpanKanvas = findViewById(R.id.simpanKanvas);

        kanvas.requestFocus();
        kanvas.setFocusable(true);
        kanvas.setFocusableInTouchMode(true);

        fragmentKanvas = new FragmentKanvas();
        fragmenTeks = new FragmenTeks();

        simpanKanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {

                        File fileku = simpanKanvas();
                        Log.d("File", "yaa1 " + fileku.getAbsolutePath());
                        Log.d("File", "yaa2 " + fileku.getPath());

                        Intent in1 = new Intent(getApplicationContext(), Main2Activity.class);
                        in1.putExtra("image", fileku);
                        startActivity(in1);
                    }
                }).start();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder;
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        //} else {
        builder = new AlertDialog.Builder(MainActivity.this);
        //}
        builder.setTitle("Yakin?")
                .setMessage("Yakin??")
                .setCancelable(false)
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentKanvas.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void fokusTeksDidapat(Teks teks) {
        fragmenTeks.setTeks(teks);
        fragmenTeks.setKanvas(kanvas);
        initFragmen(fragmenTeks);
    }

    private void initFragmen(Fragment fragment) {
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    @Override
    public void fokusKanvasDidapat(Kanvas kanvas) {
        fragmentKanvas.setKanvas(this.kanvas);
        initFragmen(fragmentKanvas);
    }

    private File simpanKanvas() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("KataKata", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, System.currentTimeMillis() + ".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            Bitmap bitmap = jadikanGambar(kanvas);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("File", "haloo " + Uri.fromFile(mypath));

        return mypath;
    }

    private Bitmap jadikanGambar(View view) {
        Bitmap bitmapss = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.setDrawingCacheEnabled(false);
        Canvas canvas = new Canvas(bitmapss);
        view.draw(canvas);
        return bitmapss;
    }
}
