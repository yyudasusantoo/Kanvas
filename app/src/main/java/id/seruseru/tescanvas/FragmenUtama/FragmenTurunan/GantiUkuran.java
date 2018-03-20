package id.seruseru.tescanvas.FragmenUtama.FragmenTurunan;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import id.seruseru.tescanvas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GantiUkuran extends Fragment {
    private SeekBar seekBar;
    private UkuranYangDipilih ukuranPilihan;

    public interface UkuranYangDipilih {
        void ukuranPilihan(int position);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
           ukuranPilihan = (UkuranYangDipilih) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement UkuranYangDipilih");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganti_ukuran, container, false);

        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setProgress(50);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ukuranPilihan.ukuranPilihan(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(50);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }
}
