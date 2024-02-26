package com.example.kontrolajpc.util.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

enum class PreferenceKeysString(val default: String) {
    QC_PERSON_NAME(" "),
}
object PreferenceKeys {
    val AUTO_DELETE_EXPORTED = booleanPreferencesKey("auto_delete_exported")
    val OVERWRITE_EXISTING_OUTPUT_FILE = booleanPreferencesKey("overwrite_existing_output_file")
    val QC_PERSON_NAME = stringPreferencesKey("qc_person_name")
}