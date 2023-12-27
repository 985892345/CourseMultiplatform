package com.multiplatform.course.platform

/**
 * 简单数据保存
 *
 * @author 985892345
 * @date 2023/12/21 18:17
 */
expect object Preference {
  fun getString(key: String): String?
  fun getInt(key: String, default: Int): Int
  fun getLong(key: String, default: Long): Long
  fun getFloat(key: String, default: Float): Float
  fun getBoolean(key: String, default: Boolean): Boolean
  fun getStringArray(key: String, default: Array<String?>): Array<String?>
  fun getIntArray(key: String, default: Array<Int>): Array<Int>
  fun getLongArray(key: String, default: Array<Long>): Array<Long>
  fun getFloatArray(key: String, default: Array<Float>): Array<Float>
  fun getBooleanArray(key: String, default: Array<Boolean>): Array<Boolean>
  fun edit(action: WritePreference.() -> Unit)
}

expect interface WritePreference {
  fun put(key: String, value: String)
  fun put(key: String, value: Int)
  fun put(key: String, value: Long)
  fun put(key: String, value: Float)
  fun put(key: String, value: Boolean)
  fun put(key: String, value: Array<String?>)
  fun put(key: String, value: Array<Int>)
  fun put(key: String, value: Array<Long>)
  fun put(key: String, value: Array<Float>)
  fun put(key: String, value: Array<Boolean>)
  fun remove(key: String)
}