package com.multiplatform.course.platform

import android.content.Context
import android.content.SharedPreferences.Editor
import androidx.core.content.edit
import com.g985892345.android.utils.context.appContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/21 18:17
 */
actual object Preference {

  val sp = appContext.getSharedPreferences("default", Context.MODE_PRIVATE)

  actual fun getString(key: String): String? {
    return sp.getString(key, null)
  }

  actual fun getInt(key: String, default: Int): Int {
    return sp.getInt(key, default)
  }

  actual fun getLong(key: String, default: Long): Long {
    return sp.getLong(key, default)
  }

  actual fun getFloat(key: String, default: Float): Float {
    return sp.getFloat(key, default)
  }

  actual fun getBoolean(key: String, default: Boolean): Boolean {
    return sp.getBoolean(key, default)
  }

  actual fun getStringArray(
    key: String,
    default: Array<String?>
  ): Array<String?> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getIntArray(
    key: String,
    default: Array<Int>
  ): Array<Int> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getLongArray(
    key: String,
    default: Array<Long>
  ): Array<Long> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getFloatArray(
    key: String,
    default: Array<Float>
  ): Array<Float> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun getBooleanArray(
    key: String,
    default: Array<Boolean>
  ): Array<Boolean> {
    val value = getString(key) ?: return default
    return Json.decodeFromString(value)
  }

  actual fun edit(action: WritePreference.() -> Unit) {
    sp.edit {
      action.invoke(WritePreferenceImpl(this))
    }
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
}

class WritePreferenceImpl(private val editor: Editor) : WritePreference {

  override fun put(key: String, value: String) {
    editor.putString(key, value)
  }

  override fun put(key: String, value: Int) {
    editor.putInt(key, value)
  }

  override fun put(key: String, value: Long) {
    editor.putLong(key, value)
  }

  override fun put(key: String, value: Float) {
    editor.putFloat(key, value)
  }

  override fun put(key: String, value: Boolean) {
    editor.putBoolean(key, value)
  }

  override fun put(key: String, value: Array<String?>) {
    editor.putString(key, Json.encodeToString(value))
  }

  override fun put(key: String, value: Array<Int>) {
    editor.putString(key, Json.encodeToString(value))
  }

  override fun put(key: String, value: Array<Long>) {
    editor.putString(key, Json.encodeToString(value))
  }

  override fun put(key: String, value: Array<Float>) {
    editor.putString(key, Json.encodeToString(value))
  }

  override fun put(key: String, value: Array<Boolean>) {
    editor.putString(key, Json.encodeToString(value))
  }

  override fun remove(key: String) {
    editor.remove(key)
  }
}
