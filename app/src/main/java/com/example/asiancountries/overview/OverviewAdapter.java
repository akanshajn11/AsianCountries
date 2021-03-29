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
import com.example.asiancountries.network.Country;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {

    private List<Country> dataSet;
    private Context mContext;

    public OverviewAdapter(Context context, List<Country> countries) {
        mContext = context;
        dataSet = countries;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView flag;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            flag = view.findViewById(R.id.flag);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.row_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Country currentItem = dataSet.get(position);
        String imageUrl = currentItem.getFlag();
        String name = currentItem.getName();

        viewHolder.name.setText(name);

        GlideToVectorYou.init().with(mContext).load(Uri.parse(imageUrl), viewHolder.flag);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
