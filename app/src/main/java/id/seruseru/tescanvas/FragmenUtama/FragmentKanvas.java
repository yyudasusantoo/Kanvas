package id.seruseru.tescanvas.FragmenUtama;


import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;


import java.io.File;
import java.io.FileOutputStream;

import id.seruseru.tescanvas.PilihanDrawable.DrawableYangDipilih;
import id.seruseru.tescanvas.FragmenUtama.FragmenTurunan.GantiDrawable;
import id.seruseru.tescanvas.FragmenUtama.FragmenTurunan.GantiWarna;
import id.seruseru.tescanvas.Kanvas;
import id.seruseru.tescanvas.Main2Activity;
import id.seruseru.tescanvas.PilihanWarna.WarnaYangDipilih;
import id.seruseru.tescanvas.R;
import id.seruseru.tescanvas.Teks;


public class FragmentKanvas extends Fragment implements WarnaYangDipilih, DrawableYangDipilih {

    //Inisisasi variabel
    private Context mContext;
    private Kanvas mKanvas;
    private ImageButton tambahTextku, pilihWarna, pilihDrawable, pilihGambar;
    private Uri uriGambarYangDipangkas;
    private Activity mActivity;
    private Fragment gantiDrawable,gantiWarna;
    private FragmentTransaction transaction;

    /* Mulai menimpa Fragment */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mContext = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kanvas_fragment, container, false);

        tambahTextku = view.findViewById(R.id.tambahTextku);
        pilihWarna = view.findViewById(R.id.pilihWarna);
        pilihDrawable = view.findViewById(R.id.pilihDrawable);
        pilihGambar = view.findViewById(R.id.pilihGambar);

        gantiDrawable = new GantiDrawable();
        gantiWarna = new GantiWarna();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tambahTextku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fungsiTambahTextku();
            }
        });

        pilihWarna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pilihDrawable.isSelected() || pilihGambar.isSelected()) {
                    pilihDrawable.setSelected(false);
                    pilihGambar.setSelected(false);
                }
                v.setSelected(!pilihWarna.isSelected());
                if (v.isSelected()){
                    mulaiTurunanFragmen(gantiWarna,true);
                }else {
                    mulaiTurunanFragmen(gantiWarna,false);
                }
            }
        });
        pilihDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pilihWarna.isSelected() || pilihGambar.isSelected()) {
                    pilihWarna.setSelected(false);
                    pilihGambar.setSelected(false);
                }
                v.setSelected(!pilihDrawable.isSelected());
                if (v.isSelected()){
                    mulaiTurunanFragmen(gantiDrawable,true);
                }else {
                    mulaiTurunanFragmen(gantiDrawable,false);
                }

            }
        });
        pilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pilihWarna.isSelected() || pilihDrawable.isSelected()) {
                    pilihWarna.setSelected(false);
                    pilihDrawable.setSelected(false);
                }
                fungsiPilihGambar();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("FRAGMENT", "onACtivityKepanggil");
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE) {
                Uri imageUri = CropImage.getPickImageResultUri(mContext, data);
                if (CropImage.isReadExternalStoragePermissionsRequired(mContext, imageUri)) {
                    // request permissions and handle the result in onRequestPermissionsResult()
                    uriGambarYangDipangkas = imageUri;
                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                } else {
                    // no permissions required or already granted, can start crop image activity
                    startCropImageActivity(imageUri);
                }
            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                mKanvas.latarBelakangGambar(resultUri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (uriGambarYangDipangkas != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Sudah mendapat ijin, lanjutkann...
                startCropImageActivity(uriGambarYangDipangkas);
            } else {
                Toast.makeText(mActivity, "Coba sekali lagi..", Toast.LENGTH_LONG).show();
            }
        }
    }

    /* Mulai Fungsi onClickListener */

    //Fungsi untuk menambah Textku
    private void fungsiTambahTextku() {
        //Inisiasi Textku
        Teks teks;

        if (mKanvas != null) {
            //Mengambil lebar mKanvas
            float x = mKanvas.getWidth() / 2;
            //Mengambil tinggi mKanvas
            float y = mKanvas.getHeight() / 2;

            teks = new Teks(mContext);
            teks.setTextSize(50);
            teks.setText("Halooo, ");
            teks.setKanvas(mKanvas);
            teks.setTranslationX(x);
            teks.setTranslationY(y);

            mKanvas.addView(teks);
        }

    }

    //Fungsi untuk memilih Gambar untuk Kanvas
    private void fungsiPilihGambar() {
        CropImage.startPickImageActivity(mActivity);
    }

    private void mulaiTurunanFragmen(Fragment fragment, boolean buka){
        transaction = getChildFragmentManager().beginTransaction();
        if (buka) {
            if (!fragment.isAdded()){
                transaction.replace(R.id.turunanKanvas,fragment);
            }
        } else {
            if (fragment.isAdded()){
                transaction.remove(fragment);
            }
        }
        transaction.commit();
    }

    /* Mulai Fungsi lanjutan */
    //Fungsi untuk melanjutkan Gambar yang dipilih dari fungsiPilihGambar ke 'Crop Image Activity'
    private void startCropImageActivity(Uri uri) {
        CropImage.activity(uri)
                .start(mContext, this);
    }

    public Bitmap jadikanGambar(View view) {
        Bitmap bitmapss = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.setDrawingCacheEnabled(false);
        Canvas canvas = new Canvas(bitmapss);
        view.draw(canvas);
        return bitmapss;
    }

    private File simpanKanvas() {
        ContextWrapper cw = new ContextWrapper(mContext.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("KataKata", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, System.currentTimeMillis() + ".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            Bitmap bitmap = jadikanGambar(mKanvas);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("File", "haloo " + Uri.fromFile(mypath));

        return mypath;
    }

    public void setKanvas(Kanvas kanvas) {
        mKanvas = kanvas;
    }

    @Override
    public void warnaDipilih(int warna) {
        mKanvas.latarBelakangWarna(warna);
        mKanvas.requestLayout();
        mKanvas.invalidate();
    }

    @Override
    public void pilihanDrawable(Drawable drawable) {
        mKanvas.latarBelakangGradien(drawable);
        mKanvas.requestLayout();
        mKanvas.invalidate();
    }
}
