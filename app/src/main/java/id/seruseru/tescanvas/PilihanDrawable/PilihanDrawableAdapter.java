package id.seruseru.tescanvas.PilihanDrawable;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.seruseru.tescanvas.R;


/**
 * Created by Yuda Susanto on 3/19/2018.
 */

public class PilihanDrawableAdapter extends RecyclerView.Adapter<PilihanDrawableAdapter.PilihanDrawableViewHolder> {
    private ArrayList<PilihanDrawable> dataDrawable;
    private DrawableYangDipilih drawablePilihan;
    private Context context;

    public PilihanDrawableAdapter(ArrayList<PilihanDrawable> dataDrawable, DrawableYangDipilih drawablePilihan) {
        this.dataDrawable = dataDrawable;
        this.drawablePilihan = drawablePilihan;
    }

    @NonNull
    @Override
    public PilihanDrawableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.drawable_row, parent, false);
        context = parent.getContext();
        return new PilihanDrawableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihanDrawableAdapter.PilihanDrawableViewHolder holder, int position) {
        holder.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,dataDrawable.get(position).getWarna());
        //holder.drawablePilihan2.setImageDrawable(holder.mGradientDrawable);

        Glide.with(context)
                .load(new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,dataDrawable.get(position).getWarna()
                        )
                )
                .into(holder.drawablePilihan2);


    }

    @Override
    public int getItemCount() {
        return dataDrawable.size();
    }

    public class PilihanDrawableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView drawablePilihan2;
        private GradientDrawable mGradientDrawable;

        private PilihanDrawableViewHolder(View itemView) {
            super(itemView);

            drawablePilihan2 = itemView.findViewById(R.id.drawablePilihan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            drawablePilihan.pilihanDrawable(mGradientDrawable);
        }
    }
}
