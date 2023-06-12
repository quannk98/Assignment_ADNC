package quannkph29999.fpoly.assignment.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import quannkph29999.fpoly.assignment.Adapter.AdapterFavorite;
import quannkph29999.fpoly.assignment.DAO.FavDAO;
import quannkph29999.fpoly.assignment.DAO.ThanhVienDAO;
import quannkph29999.fpoly.assignment.Model.FavoriteMusic;
import quannkph29999.fpoly.assignment.Model.ThanhVien;
import quannkph29999.fpoly.assignment.R;
import quannkph29999.fpoly.assignment.Screen_Login;


public class Person_Fragment extends Fragment {
    FavDAO favDAO;
    AdapterFavorite adapterFavorite;
    ArrayList<FavoriteMusic> listfav;
    RecyclerView recyclerView;
    Button dangxuat;
    ImageView imageView;
    String currentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Button choose;
    TextView tendn;
    ThanhVienDAO thanhVienDAO;

    public Person_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_, container, false);
        recyclerView = view.findViewById(R.id.person_recycler_yeuthichnhac);
        imageView = view.findViewById(R.id.person_img);
        choose = view.findViewById(R.id.person_btnchoose);
        tendn = view.findViewById(R.id.person_tendp);
        realoandata();
        return view;

    }


    public void realoandata() {
        favDAO = new FavDAO(getContext());
        listfav = favDAO.getDSFM();
        adapterFavorite = new AdapterFavorite(getContext(), listfav, favDAO);
        adapterFavorite.setData(listfav);
        recyclerView.setAdapter(adapterFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onResume() {
        super.onResume();
        realoandata();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dangxuat = view.findViewById(R.id.person_btndangxuat);
        Bundle ten = getActivity().getIntent().getExtras();
        String stringtendn = ten.get("tendn").toString();
        tendn.setText(stringtendn);
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Screen_Login.class);
                getContext().startActivity(intent);


            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getContext(),
                                "quannkph29999.fpoly.assignment.Fragment.fileprovider",
                                photoFile);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


                    }
                }
            }
        });

        realoandata();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            File photoFile = new File(currentPhotoPath);
            if (photoFile.exists()) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "quannkph29999.fpoly.assignment.Fragment.fileprovider",
                        photoFile);
                thanhVienDAO = new ThanhVienDAO(getContext());
                String tendn = data.getStringExtra("tendn");
                String mkdn = data.getStringExtra("mkdn");
                String imganh = photoFile.getAbsolutePath();
                ThanhVien imgthem = new ThanhVien(tendn,mkdn,imganh);
                if (thanhVienDAO.ThemAnh(imgthem) >= 0) {
                    realoandata();
                    Toast.makeText(getContext(), "Cập Nhật Ảnh Thành Công", Toast.LENGTH_SHORT).show();
                    setPic();
                } else {
                    Toast.makeText(getContext(), "Cập Nhật Ảnh Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {

        int targetW = imageView.getMaxWidth();
        int targetH = imageView.getMaxHeight();


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;


        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(0);

    }


}