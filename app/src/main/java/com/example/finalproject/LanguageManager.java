package com.example.finalproject;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

public class LanguageManager {
    private static LanguageManager instance;
    private final Context context;

    private LanguageManager(Context context) {
        this.context = context;
    }

    public static synchronized LanguageManager getInstance(Context context) {
        if (instance == null) {
            instance = new LanguageManager(context);
        }
        return instance;
    }

    public void setAppLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);

        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, resources.getDisplayMetrics());
    }

    public String getCurrentLanguage() {
        Configuration config = context.getResources().getConfiguration();
        return config.locale.getLanguage();
    }
}
