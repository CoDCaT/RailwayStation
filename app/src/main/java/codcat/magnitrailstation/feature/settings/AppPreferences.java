package codcat.magnitrailstation.feature.settings;


import android.content.Context;

import java.util.HashMap;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferences implements IPreferences {

    private Context appContext;
    private final String PREFERENCE_NAME = "app_settings";
    private final String IS_JSON_PARSED = "is_json_parsed";
    private HashMap<String, LinkedList<SettingChangeListener>> mChangeListener = new HashMap<>();

    @Override
    public boolean getIsJsonParsed() {
        return getBoolean(IS_JSON_PARSED);
    }

    @Override
    public void setIsJsonParsed(boolean b) {
        setBoolean(IS_JSON_PARSED, b);
    }

    @Inject
    public AppPreferences(Context appContext) {
        this.appContext = appContext;

        appContext.
                getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).
                registerOnSharedPreferenceChangeListener((sharedPreferences, s) ->
                {
                    if (!mChangeListener.containsKey(s)) {
                        return;
                    }
                    LinkedList<SettingChangeListener> listeners = mChangeListener.get(s);
                    LinkedList<SettingChangeListener> copyListeners = new LinkedList<>(listeners);
                    for (SettingChangeListener l : copyListeners) {
                        l.changed();
                    }
                });
    }

    public void addSettingChangeListener(String key, SettingChangeListener l) {
        if (!mChangeListener.containsKey(key)) {
            mChangeListener.put(key, new LinkedList<>());
        }
        LinkedList<SettingChangeListener> listeners = mChangeListener.get(key);
        listeners.add(l);
    }
    private boolean getBoolean(String prefKey) {
        return appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getBoolean(prefKey, false);
    }
    private void setBoolean(String prefKey, boolean value) {
        appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putBoolean(prefKey, value).apply();
    }

    private long getLong(String prefKey, long def) {
        return appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getLong(prefKey, def);
    }
    private void setLong(String prefKey, long value) {
        appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putLong(prefKey, value).apply();
    }

    private String getString(String prefKey, String def) {
        return appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(prefKey, def);
    }
    private void setString(String prefKey, String value) {
        appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putString(prefKey, value).apply();
    }

    private int getInt(String prefKey, int def) {
        return appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getInt(prefKey, def);
    }
    private void setInt(String prefKey, int value) {
        appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putInt(prefKey, value).apply();
    }
}
