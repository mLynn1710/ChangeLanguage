package com.example.marlyn.changelanguage;
//m.marlyn 27/7/2017
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //add my preference to store current language
    private static final String MyPREFERENCES = "MyPrefsForLanguage" ;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    String language;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        language = sharedpreferences.getString("language", null);
        if(language!=null){
            Locale myLocale = new Locale(language);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }

        setContentView(R.layout.activity_main);

        Button changeLang = (Button)findViewById(R.id.button);
        TextView dispText = (TextView)findViewById(R.id.textView);

        dispText.setText(getText(R.string.welcome));

        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (language != null){
                    editor.putString("language", null);
                    editor.commit();
                    String eng = "en";
                    setLocale(eng);
                }else {
                    String malay = "ms";
                    editor.putString("language", malay);
                    editor.commit();
                    setLocale(malay);
                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

}
