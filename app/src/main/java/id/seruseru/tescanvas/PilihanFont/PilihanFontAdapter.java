package id.seruseru.tescanvas.PilihanFont;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.seruseru.tescanvas.R;

/**
 * Created by Yuda Susanto on 3/18/2018.
 */

public class PilihanFontAdapter extends RecyclerView.Adapter<PilihanFontAdapter.PilihanFontHolder> {
    private ArrayList<PilihanFont> dataFont;
    private FontYangDipilih pilihanFont;

    public PilihanFontAdapter(ArrayList<PilihanFont> dataFont, FontYangDipilih fontTerpilih) {
        this.dataFont = dataFont;
        pilihanFont = fontTerpilih;
    }

    @NonNull
    @Override
    public PilihanFontAdapter.PilihanFontHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.font_row, parent, false);

        return new PilihanFontHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihanFontAdapter.PilihanFontHolder holder, int position) {
        holder.textView.setTypeface(dataFont.get(position).getmTypeFace());
    }

    @Override
    public int getItemCount() {
        return dataFont.size();
    }

    public class PilihanFontHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        private PilihanFontHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textku);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            pilihanFont.fontDipilih(dataFont.get(getAdapterPosition()).getmTypeFace());
        }
    }



}
