package com.multiplatform.course.platform

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.prefs.Preferences

/**
 * https://blog.csdn.net/CodingEnding/article/details/72580201
 *
 * @author 985892345
 * @date 2023/12/21 18:17
 */
actual object Preference {

  val prefs = Preferences.userRoot().node("/com/cqupt/course")

  actual fun getString(key: String): String? {
    return prefs.get(key, null)
  }

  actual fun getInt(key: String, default: Int): Int {
    return prefs.getInt(key, 0)
  }

  actual fun getLong(key: String, default: Long): Long {
    return prefs.getLong(key, default)
  }

  actual fun getFloat(key: String, default: Float): Float {
    return prefs.getFloat(key, default)
  }

  actual fun getBoolean(key: String, default: Boolean): Boolean {
    return prefs.getBoolean(key, default)
  }

  actual fun getStringArray(key: String, default: Array<String?>): Array<String?> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getIntArray(key: String, default: Array<Int>): Array<Int> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getLongArray(key: String, default: Array<Long>): Array<Long> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getFloatArray(key: String, default: Array<Float>): Array<Float> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getBooleanArray(key: String, default: Array<Boolean>): Array<Boolean> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun edit(action: WritePreference.() -> Unit) {
    action.invoke(WritePreference)
  }
}

actual interface WritePreference {
  actual fun put(key: String, value: String)
  actual fun put(key: String, value: Int)
  actual fun put(key: String, value: Long)
  actual fun put(key: String, value: Float)
  actual fun put(key: String, value: Boolean)
  actual fun put(key: String, value: Array<String?>)
  actual fun put(key: String, value: Array<Int>)
  actual fun put(key: String, value: Array<Long>)
  actual fun put(key: String, value: Array<Float>)
  actual fun put(key: String, value: Array<Boolean>)
  actual fun remove(key: String)

  companion object : WritePreference {

    override fun put(key: String, value: String) {
      Preference.prefs.put(key, value)
    }

    override fun put(key: String, value: Int) {
      Preference.prefs.putInt(key, value)
    }

    override fun put(key: String, value: Long) {
      Preference.prefs.putLong(key, value)
    }

    override fun put(key: String, value: Float) {
      Preference.prefs.putFloat(key, value)
    }

    override fun put(key: String, value: Boolean) {
      Preference.prefs.putBoolean(key, value)
    }

    override fun put(key: String, value: Array<String?>) {
      Preference.prefs.put(key, Json.encodeToString(value))
    }

    override fun put(key: String, value: Array<Int>) {
      Preference.prefs.put(key, Json.encodeToString(value))
    }

    override fun put(key: String, value: Array<Long>) {
      Preference.prefs.put(key, Json.encodeToString(value))
    }

    override fun put(key: String, value: Array<Float>) {
      Preference.prefs.put(key, Json.encodeToString(value))
    }

    override fun put(key: String, value: Array<Boolean>) {
      Preference.prefs.put(key, Json.encodeToString(value))
    }

    override fun remove(key: String) {
      Preference.prefs.remove(key)
    }
  }
}