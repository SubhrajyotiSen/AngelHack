package com.example.angelhack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private ArrayList<String> mArrayList ;
    private Context mContext;
    private ViewHolder.ClickListener clickListener;



    PlacesAdapter (Context context, ArrayList<String> arrayList, ViewHolder.ClickListener clickListener) {
        this.mArrayList = arrayList;
        this.mContext = context;
        this.clickListener = clickListener;

    }

    // Create new views
    @Override
    public PlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, null);

        return new ViewHolder(itemLayoutView,clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(mArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener  {

        private TextView textView;
        private ClickListener listener;


         ViewHolder(View itemLayoutView,ClickListener listener) {
            super(itemLayoutView);

            this.listener = listener;

            textView = (TextView) itemLayoutView.findViewById(R.id.textView);

            itemLayoutView.setOnClickListener(this);

            itemLayoutView.setOnLongClickListener (this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(getAdapterPosition ());
            }
        }
        @Override
        public boolean onLongClick (View view) {
            return listener != null && listener.onItemLongClicked(getAdapterPosition());
        }

        interface ClickListener {
            void onItemClicked(int position);

            boolean onItemLongClicked(int position);
        }
    }


    private boolean isLocation(String place){
        return !(place.equalsIgnoreCase("left") || place.equalsIgnoreCase("right"));
    }
}