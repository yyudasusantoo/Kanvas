package id.seruseru.tescanvas.PilihanWarna;

import android.content.Context;
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
 * Created by Yuda Susanto on 3/14/2018.
 */

public class PilihanWarnaAdapter extends RecyclerView.Adapter<PilihanWarnaAdapter.PilihanWarnaViewHolder> {
    private ArrayList<PilihanWarna> dataWarna;
    private WarnaYangDipilih warnaPilihan;
    Context context;


    public PilihanWarnaAdapter(ArrayList<PilihanWarna> dataWarna, WarnaYangDipilih warnaPilihan) {
        this.dataWarna = dataWarna;
        this.warnaPilihan = warnaPilihan;
    }

    @NonNull
    @Override
    public PilihanWarnaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.text_row, parent, false);
        context = parent.getContext();

        return new PilihanWarnaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihanWarnaViewHolder holder, int position) {
        holder.tesText.setBackgroundColor(dataWarna.get(position).getmWarna());

    }

    @Override
    public int getItemCount() {
        return dataWarna.size();
    }

    public class PilihanWarnaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView tesText;


        public PilihanWarnaViewHolder(View itemView) {
            super(itemView);
            tesText = itemView.findViewById(R.id.tesText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            warnaPilihan.warnaDipilih(dataWarna.get(getAdapterPosition()).getmWarna());
        }
    }

}
