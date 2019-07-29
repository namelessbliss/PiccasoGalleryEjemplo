package com.app.nb.piccasogalleryejemplo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.nb.piccasogalleryejemplo.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class StoredImageAdapter extends RecyclerView.Adapter<StoredImageAdapter.ViewHolder> {

    private Context context;
    private List<String> storedIamges;
    private int layout;

    public StoredImageAdapter(Context context, List<String> storedIamges, int layout) {
        this.context = context;
        this.storedIamges = storedIamges;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Picasso.get().load(new File(storedIamges.get(position))).fit().centerCrop().placeholder(R.drawable.progress_animation).noFade().into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return storedIamges.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewLayout);
        }
    }
}
