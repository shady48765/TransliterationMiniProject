package com.amankumar.transliterator.ui.slideshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.amankumar.transliterator.CopiedDetails;
import com.amankumar.transliterator.LanguageDetails;
import com.amankumar.transliterator.R;

import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ArrayList<CopiedDetails> arrayListDetails = new ArrayList<>();
    ListView listView;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        listView = root.findViewById(R.id.list_item);
        SharedPreferences prefs = getContext().getSharedPreferences("Copied", MODE_PRIVATE);
        int idName = prefs.getInt("index", 0);
        Toast.makeText(getContext(), String.valueOf(idName), Toast.LENGTH_LONG).show();
        arrayListDetails.clear();

        if (idName > 0) {
            for (int i = 1; i <= idName; i++) {
                CopiedDetails copiedDetails = new CopiedDetails();
                copiedDetails.language = prefs.getString("original" + String.valueOf(i), "Testing");
                copiedDetails.transliterated = prefs.getString("text" + String.valueOf(i), "Testing");
                arrayListDetails.add(copiedDetails);
            }
            Collections.reverse(arrayListDetails);
            ArrayAdapter<CopiedDetails> adapter = new ArrayAdapter<CopiedDetails>(getContext(),
                    R.layout.listviewcopied,
                    arrayListDetails) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.listviewalllanguage, null);
                        ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.imageButton);
                        final TextView text = (TextView) convertView.findViewById(R.id.textView2);
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vib.vibrate(150);
                                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                    clipboard.setText(text.getText().toString());
                                    Toast.makeText(getContext(),"Copied",Toast.LENGTH_LONG).show();
                                } else {
                                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text.getText().toString());
                                    clipboard.setPrimaryClip(clip);
                                    Toast.makeText(getContext(),"Copied",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                    TextView Languagename = (TextView) convertView.findViewById(R.id.textView);
                    Languagename.setText(arrayListDetails.get(position).language);

                    TextView TransText = (TextView) convertView.findViewById(R.id.textView2);
                    TransText.setText(arrayListDetails.get(position).transliterated);

                    return convertView;
                }
            };

            listView.setAdapter(adapter);
        }
        return root;

    }
}