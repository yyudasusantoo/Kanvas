package id.seruseru.tescanvas.FragmenUtama.FragmenTurunan;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import id.seruseru.tescanvas.PilihanFont.FontYangDipilih;
import id.seruseru.tescanvas.PilihanFont.PilihanFont;
import id.seruseru.tescanvas.PilihanFont.PilihanFontAdapter;
import id.seruseru.tescanvas.R;


public class GantiFont extends Fragment  {
    private Typeface tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12,tf13,tf14,tf15,tf16,tf17,tf18,tf19,tf20;
    private FontYangDipilih fontTerpilih;
    private RecyclerView rView;
    private LinearLayoutManager linearLayoutManager;
    private PilihanFontAdapter adapter;
    private ArrayList<PilihanFont> dataFont;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fontTerpilih = (FontYangDipilih) getParentFragment();
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FontYangDipilih");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganti_font, container, false);


        rView = view.findViewById(R.id.rView);
        linearLayoutManager = new LinearLayoutManager(getParentFragment().getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rView.setLayoutManager(linearLayoutManager);

        inisiasiData();
        tambahData();

        adapter = new PilihanFontAdapter(dataFont,fontTerpilih);
        rView.setAdapter(adapter);

        return view;
    }

    private void inisiasiData(){
        tf1 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "1.ttf");
        tf2 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "2.ttf");
        tf3 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "3.ttf");
        tf4 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "4.ttf");
        tf5 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "5.ttf");
        tf6 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "6.ttf");
        tf7 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "7.ttf");
        tf8 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "8.ttf");
        tf9 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "9.ttf");
        tf10 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "10.ttf");
        tf11 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "11.ttf");
        tf12 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "12.ttf");
        tf13 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "13.ttf");
        tf14 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "14.ttf");
        tf15 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "15.ttf");
        tf16 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "16.ttf");
        tf17 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "17.ttf");
        tf18 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "18.ttf");
        tf19 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "19.ttf");
        tf20 = Typeface.createFromAsset(getParentFragment().getActivity().getApplicationContext().getAssets(), "20.ttf");
    }
    private void tambahData(){
        dataFont = new ArrayList<>();
        dataFont.add(new PilihanFont(tf1));
        dataFont.add(new PilihanFont(tf2));
        dataFont.add(new PilihanFont(tf3));
        dataFont.add(new PilihanFont(tf4));
        dataFont.add(new PilihanFont(tf5));
        dataFont.add(new PilihanFont(tf6));
        dataFont.add(new PilihanFont(tf7));
        dataFont.add(new PilihanFont(tf8));
        dataFont.add(new PilihanFont(tf9));
        dataFont.add(new PilihanFont(tf10));
        dataFont.add(new PilihanFont(tf11));
        dataFont.add(new PilihanFont(tf12));
        dataFont.add(new PilihanFont(tf13));
        dataFont.add(new PilihanFont(tf14));
        dataFont.add(new PilihanFont(tf15));
        dataFont.add(new PilihanFont(tf16));
        dataFont.add(new PilihanFont(tf17));
        dataFont.add(new PilihanFont(tf18));
        dataFont.add(new PilihanFont(tf19));
        dataFont.add(new PilihanFont(tf20));
    }
}
