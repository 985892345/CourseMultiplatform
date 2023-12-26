-dontwarn org.slf4j.**
-dontwarn io.ktor.network.sockets.**
-dontwarn kotlinx.serialization.internal.**
-dontwarn org.slf4j.helpers.SubstituteLogger

-keep class io.ktor.client.engine.okhttp.OkHttpEngineContainer
-keep class io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensionProvider

###################################### kotlinx.serialization #######################################
# Serializer for classes with named companion objects are retrieved using `getDeclaredClasses`.
# If you have any, replace classes with those containing named companion objects.
-keepattributes InnerClasses # Needed for `getDeclaredClasses`.

-keepclasseswithmembers @kotlinx.serialization.Serializable class ** {
    static *** Companion;
}

-if @kotlinx.serialization.Serializable class **
-keepclasseswithmembers class <1>$Companion {
    *** serializer();
}

# https://juejin.cn/post/6966526844552085512
-optimizationpasses 7                       # 代码混淆的压缩比例，值介于0-7，默认5
-keepattributes SourceFile,LineNumberTable  # 保留行号

## OkHttp https://github.com/square/okhttp
-dontwarn javax.annotation.**
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
# 下面这个是 OkHttp 依赖的 Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*