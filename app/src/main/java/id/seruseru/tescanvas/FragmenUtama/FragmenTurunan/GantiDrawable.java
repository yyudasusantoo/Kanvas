package id.seruseru.tescanvas.FragmenUtama.FragmenTurunan;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.seruseru.tescanvas.PilihanDrawable.DrawableYangDipilih;
import id.seruseru.tescanvas.PilihanDrawable.PilihanDrawable;
import id.seruseru.tescanvas.PilihanDrawable.PilihanDrawableAdapter;
import id.seruseru.tescanvas.R;

/**
 * Created by Yuda Susanto on 3/19/2018.
 */

public class GantiDrawable extends Fragment {
    private RecyclerView rView;
    private LinearLayoutManager linearLayoutManager;
    private PilihanDrawableAdapter drawableAdapter;
    private ArrayList<PilihanDrawable> dataDrawable;
    Drawable drawable1;
    DrawableYangDipilih drawableYangDipilih;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            drawableYangDipilih = (DrawableYangDipilih) getParentFragment();
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FontYangDipilih");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganti_drawable,container,false);
        rView = view.findViewById(R.id.rView);
        linearLayoutManager = new LinearLayoutManager(getParentFragment().getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rView.setLayoutManager(linearLayoutManager);

        inisiasiData();
        tambahData();


        drawableAdapter = new PilihanDrawableAdapter(dataDrawable,drawableYangDipilih);
        rView.setAdapter(drawableAdapter);
        return view;
    }

    private void inisiasiData(){
        drawable1 = getParentFragment().getActivity().getResources().getDrawable(R.drawable.gradient1);
    }
    private void tambahData(){
        dataDrawable = new ArrayList<>();
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#B79891"),Color.parseColor("#94716B")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#005AA7"),Color.parseColor("#FFFDE4")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#DA4453"),Color.parseColor("#89216B")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#ef32d9"),Color.parseColor("#89fffd")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#ad5389"),Color.parseColor("#3c1053")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#a8c0ff"),Color.parseColor("#3f2b96")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#333333"),Color.parseColor("#dd1818")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#4e54c8"),Color.parseColor("#8f94fb")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#355C7D"),Color.parseColor("#C06C84")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#bc4e9c"),Color.parseColor("#f80759")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#40E0D0"),Color.parseColor("#FF0080")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#3E5151"),Color.parseColor("#DECBA4")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#11998e"),Color.parseColor("#38ef7d")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#108dc7"),Color.parseColor("#ef8e38")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#FC5C7D"),Color.parseColor("#6A82FB")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#fffbd5"),Color.parseColor("#b20a2c")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#CAC531"),Color.parseColor("#F3F9A7")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#3A1C71"),Color.parseColor("#FFAF7B")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#556270"),Color.parseColor("#FF6B6B")}));
        dataDrawable.add(new PilihanDrawable(new int[]{Color.parseColor("#FF6B6B"),Color.parseColor("#3a7bd5")}));
    }


}
