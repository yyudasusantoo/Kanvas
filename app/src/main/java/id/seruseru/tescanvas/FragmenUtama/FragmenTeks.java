package id.seruseru.tescanvas.FragmenUtama;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import id.seruseru.tescanvas.FragmenUtama.FragmenTurunan.GantiFont;
import id.seruseru.tescanvas.FragmenUtama.FragmenTurunan.GantiUkuran;
import id.seruseru.tescanvas.FragmenUtama.FragmenTurunan.GantiWarna;
import id.seruseru.tescanvas.Kanvas;
import id.seruseru.tescanvas.PilihanFont.FontYangDipilih;
import id.seruseru.tescanvas.PilihanWarna.WarnaYangDipilih;
import id.seruseru.tescanvas.R;
import id.seruseru.tescanvas.Teks;


public class FragmenTeks extends Fragment implements GantiUkuran.UkuranYangDipilih, FontYangDipilih, WarnaYangDipilih {
    private ImageButton hapusTextku, warnaFont, gantiFont, ukuranFont;
    private Fragment warnaFragment, ukuranFragment, fontFragment;
    private Teks teks;
    private Kanvas kanvas;
    private FragmentTransaction transaction;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.text_fragment, container, false);


        hapusTextku = view.findViewById(R.id.hapusTextku);
        ukuranFont = view.findViewById(R.id.ukuranFont);
        gantiFont = view.findViewById(R.id.gantiFont);
        warnaFont = view.findViewById(R.id.gantiWarnaFont);

        warnaFragment = new GantiWarna();
        ukuranFragment = new GantiUkuran();
        fontFragment = new GantiFont();


        ukuranFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warnaFont.isSelected() || gantiFont.isSelected()) {
                    warnaFont.setSelected(false);
                    gantiFont.setSelected(false);
                }
                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    mulaiFragment(ukuranFragment, true);
                } else {
                    mulaiFragment(ukuranFragment, false);
                }
            }
        });
        gantiFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warnaFont.isSelected() || ukuranFont.isSelected()) {
                    warnaFont.setSelected(false);
                    ukuranFont.setSelected(false);
                }
                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    mulaiFragment(fontFragment, true);
                } else {
                    mulaiFragment(fontFragment, false);
                }
            }
        });
        warnaFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gantiFont.isSelected() || ukuranFont.isSelected()) {
                    gantiFont.setSelected(false);
                    ukuranFont.setSelected(false);
                }

                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    mulaiFragment(warnaFragment, true);
                } else {
                    mulaiFragment(warnaFragment, false);
                }
            }
        });
        hapusTextku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kanvas.removeView(teks);
                onDetach();
            }
        });


        return view;
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
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void mulaiFragment(Fragment fragment, boolean buka) {
        transaction = getChildFragmentManager().beginTransaction();
        if (buka) {
            if (!fragment.isAdded()){
                transaction.replace(R.id.nestedFragment,fragment);
            }
        } else {
            if (fragment.isAdded()){
                transaction.remove(fragment);
            }
        }
        transaction.commit();
    }

    public void setTeks(Teks teks) {
        this.teks = teks;
    }

    public void setKanvas(Kanvas kanvas) {
        this.kanvas = kanvas;
    }


    @Override
    public void fontDipilih(Typeface jenisFont) {
        teks.setTypeface(jenisFont);
    }

    @Override
    public void warnaDipilih(int warna) {
        teks.setTextColor(warna);
    }

    @Override
    public void ukuranPilihan(int position) {
        teks.setTextSize(position);
    }
}
