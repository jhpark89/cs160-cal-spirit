package com.example.finalprojectcs160;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

//import android.support.v7.widget.RecyclerView;


/**
 * Created by User on 2/12/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDist = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Boolean> mIsUrl = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> dist, ArrayList<String> imageUrls, ArrayList<Boolean> isURL) {
        mNames = names;
        mDist = dist;
        mImageUrls = imageUrls;
        mIsUrl = isURL;
        mContext = context;
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> dist, ArrayList<String> imageUrls) {
        mNames = names;
        mDist = dist;
        mImageUrls = imageUrls;
        mIsUrl = null;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        System.out.println(mImageUrls.get(position));
        System.out.println(mIsUrl);
        if (mIsUrl != null && !mIsUrl.get(position)) {
            try {
                Integer.parseInt(mImageUrls.get(position));
                try {
                    this.mContext.getResources().getDrawable(Integer.parseInt(mImageUrls.get(position)), null);
                    Drawable img = this.mContext.getResources().getDrawable(Integer.parseInt(mImageUrls.get(position)), null);
                    holder.image.setImageDrawable(img);
                } catch (Resources.NotFoundException | NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("TODO: need to set images from local resources");
        } else {
        Glide.with(mContext).load(mImageUrls.get(position))
                .apply(new RequestOptions().override(200, 200)).into(holder.image);
        }

        holder.name.setText(mNames.get(position));
        holder.distance.setText(mDist.get(position));
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
//                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView distance;
        ConstraintLayout parent;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.explore_store_image);
            name = itemView.findViewById(R.id.explore_store_name);
            distance = itemView.findViewById(R.id.explore_store_dist);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}