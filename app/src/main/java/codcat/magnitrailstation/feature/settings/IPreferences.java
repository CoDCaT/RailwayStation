package codcat.magnitrailstation.feature.settings;


public interface IPreferences {

    interface SettingChangeListener {
        void changed();
    }
    void addSettingChangeListener(String key, SettingChangeListener l);
    boolean getIsJsonParsed();
    void setIsJsonParsed(boolean b);
}
