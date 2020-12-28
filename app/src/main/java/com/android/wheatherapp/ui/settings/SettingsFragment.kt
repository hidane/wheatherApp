package com.android.wheatherapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.android.wheatherapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}