package com.amankumar.transliterator.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.amankumar.transliterator.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private static final float[] NEGATIVE ={
            -1.0f, 0 , 0, 0, 255,
            0, -1.0f, 0, 0 , 255,
            0, 0, -1.0f, 0 , 255,
            0, 0, 0, 1.0f, 0
    };
    ImageView whatsapp , email , linkedin , githubaman , telegram , instaaman;
    TextView link1,link2,link3,link4;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        whatsapp = root.findViewById(R.id.whatsapp);
        final Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        link1 = root.findViewById(R.id.iitlink);
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse(link1.getText().toString()));
                startActivity(httpIntent);
            }
        });
        link2 = root.findViewById(R.id.kitlink);
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse(link2.getText().toString()));
                startActivity(httpIntent);
            }
        });

        link3 = root.findViewById(R.id.onelink);
        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse(link3.getText().toString()));
                startActivity(httpIntent);
            }
        });
        link4 = root.findViewById(R.id.scilink);
        link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse(link3.getText().toString()));
                startActivity(httpIntent);
            }
        });
        whatsapp.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91 7021211083"));
                startActivity(intent);
            }
        });

        email = root.findViewById(R.id.email);
        email.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","amankumar48765@gmail.com", null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        linkedin = root.findViewById(R.id.linkedinaman);
        linkedin.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.linkedin.com/in/aman-kumar-52ab56192"));
                startActivity(httpIntent);
            }
        });
        telegram = root.findViewById(R.id.telegram);
        telegram.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://t.me/Toshiro0_0"));
                startActivity(httpIntent);
            }
        });
        githubaman = root.findViewById(R.id.github);
        githubaman.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        githubaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.github.com/shady48765"));
                startActivity(httpIntent);
            }
        });
        instaaman = root.findViewById(R.id.instagram);
        instaaman.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        instaaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);

                Toast.makeText(getContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.instagram.com/shady48765?igshid=b2lmly77eohd"));
                startActivity(httpIntent);
            }
        });
        return root;
    }



}