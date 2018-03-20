package id.seruseru.tescanvas.FragmenUtama.FragmenTurunan;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.seruseru.tescanvas.PilihanWarna.PilihanWarna;
import id.seruseru.tescanvas.PilihanWarna.PilihanWarnaAdapter;
import id.seruseru.tescanvas.PilihanWarna.WarnaYangDipilih;
import id.seruseru.tescanvas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GantiWarna extends Fragment {
    WarnaYangDipilih warnanya;
    RecyclerView rView;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<PilihanWarna> tesArrayList;
    private PilihanWarnaAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            warnanya = (WarnaYangDipilih) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement WarnaYangDipilih");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ganti_warna, container, false);

        addData();

        rView = view.findViewById(R.id.rView);
        linearLayoutManager = new LinearLayoutManager(getParentFragment().getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rView.setLayoutManager(linearLayoutManager);
        adapter = new PilihanWarnaAdapter(tesArrayList, warnanya);

        rView.setAdapter(adapter);

        return view;
    }
    void addData(){
        tesArrayList = new ArrayList<>();
        tesArrayList.add(new PilihanWarna(Color.parseColor("#FFFFFF")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#000000")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#F44336")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#E91E63")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#9C27B0")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#673AB7")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#3F51B5")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#2196F3")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#03A9F4")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#00BCD4")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#009688")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#4CAF50")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#8BC34A")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#CDDC39")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#FFEB3B")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#FFC107")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#FF9800")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#FF5722")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#795548")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#9E9E9E")));
        tesArrayList.add(new PilihanWarna(Color.parseColor("#607D8B")));
    }


}
