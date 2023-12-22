package com.multiplatform.course.platform

import platform.Foundation.NSArray
import platform.Foundation.NSNumber
import platform.Foundation.NSUserDefaults

/**
 * https://juejin.cn/post/6844903854098612231
 *
 * @author 985892345
 * @date 2023/12/21 18:17
 */
actual object Preference {

  val userDefaults = NSUserDefaults.standardUserDefaults

  actual fun getString(key: String): String? {
    return userDefaults.stringForKey(key)
  }

  actual fun getInt(key: String, default: Int): Int {
    return (userDefaults.objectForKey(key) as? NSNumber)?.integerValue?.toInt() ?: default
  }

  actual fun getLong(key: String, default: Long): Long {
    return (userDefaults.objectForKey(key) as? NSNumber)?.integerValue ?: default
  }

  actual fun getFloat(key: String, default: Float): Float {
    return (userDefaults.objectForKey(key) as? NSNumber)?.floatValue ?: default
  }

  actual fun getBoolean(key: String, default: Boolean): Boolean {
    return (userDefaults.objectForKey(key) as? NSNumber)?.boolValue ?: default
  }

  actual fun getStringArray(key: String, default: Array<String?>): Array<String?> {
    val nsArray = userDefaults.objectForKey(key) as? NSArray ?: return default
    return Array(nsArray.count.toInt()) {
      nsArray.objectAtIndex(it.toULong()) as String
    }
  }

  actual fun getIntArray(key: String, default: Array<Int>): Array<Int> {
    val nsArray = userDefaults.objectForKey(key) as? NSArray ?: return default
    return Array(nsArray.count.toInt()) {
      (nsArray.objectAtIndex(it.toULong()) as NSNumber).integerValue.toInt()
    }
  }

  actual fun getLongArray(key: String, default: Array<Long>): Array<Long> {
    val nsArray = userDefaults.objectForKey(key) as? NSArray ?: return default
    return Array(nsArray.count.toInt()) {
      (nsArray.objectAtIndex(it.toULong()) as NSNumber).integerValue
    }
  }

  actual fun getFloatArray(key: String, default: Array<Float>): Array<Float> {
    val nsArray = userDefaults.objectForKey(key) as? NSArray ?: return default
    return Array(nsArray.count.toInt()) {
      (nsArray.objectAtIndex(it.toULong()) as NSNumber).floatValue
    }
  }

  actual fun getBooleanArray(key: String, default: Array<Boolean>): Array<Boolean> {
    val nsArray = userDefaults.objectForKey(key) as? NSArray ?: return default
    return Array(nsArray.count.toInt()) {
      (nsArray.objectAtIndex(it.toULong()) as NSNumber).boolValue
    }
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
      Preference.userDefaults.setObject(value, key)
    }

    override fun put(key: String, value: Int) {
      Preference.userDefaults.setInteger(value.toLong(), key)
    }

    override fun put(key: String, value: Long) {
      Preference.userDefaults.setInteger(value, key)
    }

    override fun put(key: String, value: Float) {
      Preference.userDefaults.setFloat(value, key)
    }

    override fun put(key: String, value: Boolean) {
      Preference.userDefaults.setBool(value, key)
    }

    override fun put(key: String, value: Array<String?>) {
      Preference.userDefaults.setObject(value, key)
    }

    override fun put(key: String, value: Array<Int>) {
      Preference.userDefaults.setObject(value, key)
    }

    override fun put(key: String, value: Array<Long>) {
      Preference.userDefaults.setObject(value, key)
    }

    override fun put(key: String, value: Array<Float>) {
      Preference.userDefaults.setObject(value, key)
    }

    override fun put(key: String, value: Array<Boolean>) {
      Preference.userDefaults.setObject(value, key)
    }

    override fun remove(key: String) {
      Preference.userDefaults.removeObjectForKey(key)
    }
  }
}