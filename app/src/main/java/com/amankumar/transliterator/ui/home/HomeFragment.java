package com.amankumar.transliterator.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.Transliterator;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.amankumar.transliterator.LanguageDetails;
import com.amankumar.transliterator.MainActivity;
import com.amankumar.transliterator.MainActivity2;
import com.amankumar.transliterator.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText text;
    Button submit;
    Spinner spinner;
    ListView listView;
    String toSearch;
    ArrayList<LanguageDetails> arrayListDetails = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        text = root.findViewById(R.id.editTextTextPersonName);
        submit = root.findViewById(R.id.button);
        spinner = root.findViewById(R.id.spinner);
        listView = root.findViewById(R.id.list_item);
        final Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<String> arrayListog = new ArrayList<>();
        arrayList.add("English");
        arrayList.add("Devanagari");
        arrayList.add("Bengali");
        arrayList.add("Gujarati");
        arrayList.add("Tamil");
        arrayList.add("Telugu");
        arrayList.add("Kannada");
        arrayList.add("Malayalam");
        arrayList.add("Oriya");
        arrayList.add("Arabic");
        arrayList.add("Punjabi");
        arrayListog.add("Latin");
        arrayListog.add("Devanagari");
        arrayListog.add("Bengali");
        arrayListog.add("Gujarati");
        arrayListog.add("Tamil");
        arrayListog.add("Telugu");
        arrayListog.add("Kannada");
        arrayListog.add("Malayalam");
        arrayListog.add("Oriya");
       arrayListog.add("Arabic");
       arrayListog.add("GURMUKHI");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(arrayAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                toSearch=text.getText().toString();
                vib.vibrate(200);
                arrayListDetails.clear();
                for(int i = 0;i<arrayList.size();i++){
                    if(!(arrayListog.get(i).equals(arrayListog.get(spinner.getSelectedItemPosition())))) {
                        LanguageDetails languageDetails = new LanguageDetails();
                        if (arrayListog.get(spinner.getSelectedItemPosition()).equals("Latin")&&!arrayListog.get(i).equals("Latin")){
                            languageDetails.language = arrayList.get(i).concat(" : ");
                            final String LatintoLang2 = "Latin-" + arrayListog.get(i);
                            final Transliterator transliteratortoLang2 = Transliterator.getInstance(LatintoLang2);
                            final String lang2 = transliteratortoLang2.transliterate(text.getText().toString());
                            languageDetails.transliterated = lang2;
                            arrayListDetails.add(languageDetails);
                        }
                        else if (!arrayListog.get(spinner.getSelectedItemPosition()).equals("Latin")&&!arrayListog.get(i).equals("Latin")){
                            languageDetails.language = arrayList.get(i).concat(" : ");
                            final String Lang1toLatin = arrayListog.get(spinner.getSelectedItemPosition()).concat("-Latin");
                            final Transliterator transliteratortoLatin = Transliterator.getInstance(Lang1toLatin);
                            final String latin = transliteratortoLatin.transliterate(text.getText().toString());
                            final String LatintoLang2 = "Latin-" + arrayListog.get(i);
                            final Transliterator transliteratortoLang2 = Transliterator.getInstance(LatintoLang2);
                            final String lang2 = transliteratortoLang2.transliterate(latin);
                            languageDetails.transliterated = lang2;
                            arrayListDetails.add(languageDetails);
                        }
                        else if (!arrayListog.get(spinner.getSelectedItemPosition()).equals("Latin")&&arrayListog.get(i).equals("Latin")){
                            languageDetails.language = arrayList.get(i).concat(" : ");
                            final String Lang1toLatin = arrayListog.get(spinner.getSelectedItemPosition()).concat("-Latin");
                            final Transliterator transliteratortoLatin = Transliterator.getInstance(Lang1toLatin);
                            String latin = transliteratortoLatin.transliterate(text.getText().toString());
                            latin = Normalizer.normalize(latin, Normalizer.Form.NFD);
                            latin = latin.replaceAll("[^\\p{ASCII}]", "");
                            languageDetails.transliterated = latin;
                            arrayListDetails.add(languageDetails);
                        }
                    }
                }

                ArrayAdapter<LanguageDetails> adapter = new ArrayAdapter<LanguageDetails>(getContext(),
                        R.layout.listviewalllanguage,
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

                                    SharedPreferences prefs = getContext().getSharedPreferences("Copied", MODE_PRIVATE);
                                    int idName = prefs.getInt("index", 0);
                                    idName++;
                                    SharedPreferences.Editor editor = getContext().getSharedPreferences("Copied", MODE_PRIVATE).edit();

                                    editor.putString("original"+String.valueOf(idName),toSearch);
                                    editor.putString("text"+String.valueOf(idName), text.getText().toString());
                                    editor.putInt("index", idName);
                                    editor.apply();

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
        });


        return root;
    }
}