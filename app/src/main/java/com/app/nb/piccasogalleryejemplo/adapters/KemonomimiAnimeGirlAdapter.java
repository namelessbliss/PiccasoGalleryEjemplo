package com.app.nb.piccasogalleryejemplo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.nb.piccasogalleryejemplo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class KemonomimiAnimeGirlAdapter extends RecyclerView.Adapter<KemonomimiAnimeGirlAdapter.ViewHolder> {

    private Context context;
    private String[] animeGirlsUrls;
    private int layout;

    public KemonomimiAnimeGirlAdapter(Context context, String[] animeGirlsUrls, int layout) {
        this.context = context;
        this.animeGirlsUrls = animeGirlsUrls;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        // CallBack es lanzado cuando termina la carga de la imagen
        Picasso.get().load(animeGirlsUrls[position]).fit().centerCrop().placeholder(R.drawable.progress_animation).into(viewHolder.imageView, new Callback() {
            @Override
            public void onSuccess() { // imagen cargada correctamente
                //viewHolder.imageView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                //Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return animeGirlsUrls.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Declara image view
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Captura image view por su id
            this.imageView = itemView.findViewById(R.id.imageViewLayout);
        }
    }
}
