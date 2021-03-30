package com.example.asiancountries.overview;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asiancountries.R;
import com.example.asiancountries.models.AsiaCountry;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {

    private List<AsiaCountry> dataSet;
    private Context cxt;
    private OnItemClickListener countryListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        countryListener = listener;
    }

    public OverviewAdapter(Context context, List<AsiaCountry> countries) {
        cxt = context;
        dataSet = countries;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView flag;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            flag = view.findViewById(R.id.flag);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countryListener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            countryListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(cxt)
                .inflate(R.layout.row_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AsiaCountry currentItem = dataSet.get(position);
        String imageUrl = currentItem.getFlag();
        String name = currentItem.getName();
        viewHolder.name.setText(name);
        GlideToVectorYou.init().with(cxt).setPlaceHolder(R.drawable.placeholder, R.drawable.placeholder).load(Uri.parse(imageUrl), viewHolder.flag);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
