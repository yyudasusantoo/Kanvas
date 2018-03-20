package id.seruseru.tescanvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Main2Activity extends AppCompatActivity {
    ImageView gambarku;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        File fileGambar = (File) getIntent().getExtras().get("image");


        gambarku = findViewById(R.id.gambarku);
        gambarku.setImageURI(Uri.fromFile(fileGambar));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        getIntent().removeExtra("image");
        super.onDestroy();
    }
}
