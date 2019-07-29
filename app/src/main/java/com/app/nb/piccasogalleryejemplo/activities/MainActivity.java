package com.app.nb.piccasogalleryejemplo.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.nb.piccasogalleryejemplo.R;
import com.app.nb.piccasogalleryejemplo.adapters.KemonomimiAnimeGirlAdapter;
import com.app.nb.piccasogalleryejemplo.adapters.ProjectDrawableAdapter;
import com.app.nb.piccasogalleryejemplo.adapters.StoredImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Imagenes del dispositivo
    private List<String> storedImages;
    // Imagenes desde URL
    private String[] animeGirlsURLs;
    // Imagenes del proyecto
    private int[] drawables;

    private KemonomimiAnimeGirlAdapter animeGirlAdapter;
    private ProjectDrawableAdapter drawableAdapter;
    private StoredImageAdapter imageAdapter;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private final int PERMISSION_READ_EXTERNAL_MEMORY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storedImages = getImagesPath();
        drawables = getProjectDrawables();
        animeGirlsURLs = getAnimeGirlLinks();

        animeGirlAdapter = new KemonomimiAnimeGirlAdapter(this, animeGirlsURLs, R.layout.image_layout);
        drawableAdapter = new ProjectDrawableAdapter(this, drawables, R.layout.image_layout);
        imageAdapter = new StoredImageAdapter(this, storedImages, R.layout.image_layout);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.urlAdapter:
                recyclerView.setAdapter(animeGirlAdapter);
                return true;
            case R.id.projectDrawables:
                recyclerView.setAdapter(drawableAdapter);
                return true;
            case R.id.memoryAdapter:
                checkForPermission();
                storedImages.clear();
                storedImages.addAll(getImagesPath());
                recyclerView.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Metodo para ejecutar dialogo de peticion de permiso
     */
    private void checkForPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_EXTERNAL_MEMORY);
        }
    }

    /**
     * Metodo para comprar si se tiene permiso
     *
     * @return
     */
    private boolean hasPermission(String permissionToCheck) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, permissionToCheck);

        return (permissionCheck == PackageManager.PERMISSION_GRANTED);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_EXTERNAL_MEMORY:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    storedImages.clear();
                    storedImages.addAll(getImagesPath());
                    imageAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, R.string.permission_denied_message, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        storedImages.clear();
        storedImages.addAll(getImagesPath());
        imageAdapter.notifyDataSetChanged();
    }

    public String[] getAnimeGirlLinks() {
        String[] Links = {"https://images2.alphacoders.com/577/thumb-1920-57710.jpg",
                "https://images6.alphacoders.com/874/thumb-1920-874882.jpg",
                "https://images5.alphacoders.com/855/thumb-1920-855633.png",
                "https://images2.alphacoders.com/803/thumb-1920-803178.jpg",
                "https://images5.alphacoders.com/891/thumb-1920-891539.png",
                "https://images4.alphacoders.com/245/thumb-1920-245086.jpg",
                "https://images5.alphacoders.com/839/thumb-1920-839101.png",
                "https://images4.alphacoders.com/816/thumb-1920-816071.png",
                "https://images2.alphacoders.com/851/thumb-1920-851396.jpg",
                "https://images8.alphacoders.com/830/thumb-1920-830385.png",
                "https://images7.alphacoders.com/825/thumb-1920-825617.png",
                "https://images3.alphacoders.com/822/thumb-1920-822867.png",
                "https://images2.alphacoders.com/815/thumb-1920-815210.png",
                "https://images4.alphacoders.com/815/thumb-1920-815488.png",
                "https://images2.alphacoders.com/821/thumb-1920-821181.png",
                "https://images6.alphacoders.com/851/thumb-1920-851390.jpg",
                "https://images6.alphacoders.com/839/thumb-1920-839766.png",
                "https://images6.alphacoders.com/810/thumb-1920-810685.jpg",
                "https://images7.alphacoders.com/824/thumb-1920-824430.png",
                "https://images4.alphacoders.com/101/thumb-1920-1012116.jpg",
                "https://images7.alphacoders.com/101/thumb-1920-1015419.png",
                "https://images3.alphacoders.com/569/thumb-1920-569355.png",
                "https://images4.alphacoders.com/868/thumb-1920-868872.jpg",
                "https://images4.alphacoders.com/865/thumb-1920-865464.jpg"};
        return Links;
    }

    public int[] getProjectDrawables() {
        int[] values = {
                R.drawable.sandia,
                R.drawable.banana,
                R.drawable.ceresa,
                R.drawable.fresa,
                R.drawable.ic_apple,
                R.drawable.ic_cherry,
                R.drawable.ic_mango,
                R.drawable.ic_orange,
                R.drawable.ic_pear,
                R.drawable.ic_raspberry,
                R.drawable.ic_strawberry,
                R.drawable.mango,
                R.drawable.manzana,
                R.drawable.naranja,
                R.drawable.pera,
                R.drawable.sandia
        };
        return values;
    }

    public List<String> getImagesPath() {
        List<String> listOfAllStoredImages = new ArrayList<>();

        if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // DATA da la ruta
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};

            //consulta para obtener las imagenes
            Cursor cursor = getContentResolver()
                    .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, MediaStore.Images.Media._ID);
            if (cursor != null) {
                if (cursor.moveToFirst()) { //va al indice 0 del cursor
                    do {
                        // obtiene la ruta del elemento de indice n del cursor
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        listOfAllStoredImages.add(path);
                    } while (cursor.moveToNext());
                }
            }
        }
        return listOfAllStoredImages;
    }

}
