package com.amankumar.transliterator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.Transliterator;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    EditText text;
    Button submit;
    Spinner spinner;
    ListView listView;
    ArrayList<LanguageDetails> arrayListDetails = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        text = findViewById(R.id.editTextTextPersonName);
        submit = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.list_item);
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Latin");
        arrayList.add("Devanagari");
        arrayList.add("Bengali");
        arrayList.add("Gujarati");
        arrayList.add("Tamil");
        arrayList.add("Telugu");
        arrayList.add("Kannada");
        arrayList.add("Malayalam");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(arrayAdapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListDetails.clear();
             for(int i = 0;i<arrayList.size();i++){
                 if(!(arrayList.get(i).equals(spinner.getSelectedItem().toString()))) {
                     LanguageDetails languageDetails = new LanguageDetails();

                     if (spinner.getSelectedItem().toString().equals("Latin")&&!arrayList.get(i).equals("Latin")){
                         languageDetails.language = arrayList.get(i).concat(" : ");
                         final String LatintoLang2 = "Latin-" + arrayList.get(i);
                         final Transliterator transliteratortoLang2 = Transliterator.getInstance(LatintoLang2);
                         final String lang2 = transliteratortoLang2.transliterate(text.getText().toString());
                         languageDetails.transliterated = lang2;
                         arrayListDetails.add(languageDetails);
                     }
                     else if (!spinner.getSelectedItem().toString().equals("Latin")&&!arrayList.get(i).equals("Latin")){
                         languageDetails.language = arrayList.get(i).concat(" : ");
                         final String Lang1toLatin = spinner.getSelectedItem().toString().concat("-Latin");
                         final Transliterator transliteratortoLatin = Transliterator.getInstance(Lang1toLatin);
                         final String latin = transliteratortoLatin.transliterate(text.getText().toString());
                         final String LatintoLang2 = "Latin-" + arrayList.get(i);
                         final Transliterator transliteratortoLang2 = Transliterator.getInstance(LatintoLang2);
                         final String lang2 = transliteratortoLang2.transliterate(latin);
                         languageDetails.transliterated = lang2;
                         arrayListDetails.add(languageDetails);
                     }
                     else if (!spinner.getSelectedItem().toString().equals("Latin")&&arrayList.get(i).equals("Latin")){
                         languageDetails.language = arrayList.get(i).concat(" : ");
                         final String Lang1toLatin = spinner.getSelectedItem().toString().concat("-Latin");
                         final Transliterator transliteratortoLatin = Transliterator.getInstance(Lang1toLatin);
                         final String latin = transliteratortoLatin.transliterate(text.getText().toString());
                         languageDetails.transliterated = latin;
                         arrayListDetails.add(languageDetails);
                     }
                 }
             }

             ArrayAdapter<LanguageDetails> adapter = new ArrayAdapter<LanguageDetails>(MainActivity.this,
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
                                    if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                        clipboard.setText(text.getText().toString());
                                        Toast.makeText(getApplicationContext(),"Copied",Toast.LENGTH_LONG).show();
                                    } else {
                                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text.getText().toString());
                                        clipboard.setPrimaryClip(clip);
                                        Toast.makeText(getApplicationContext(),"Copied",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

                        SharedPreferences prefs = getSharedPreferences("Copied", MODE_PRIVATE);
                        int idName = prefs.getInt("index", 0);
                        idName++;
                        SharedPreferences.Editor editor = getSharedPreferences("Copied", MODE_PRIVATE).edit();
                        editor.putString("original"+String.valueOf(idName),spinner.getSelectedItem().toString());
                        editor.putString("text"+String.valueOf(idName), text.getText().toString());
                        editor.putInt("index", idName);
                        editor.apply();

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



    }


    @Override
    public void onInit(int status) {

    }
}