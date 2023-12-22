-dontwarn org.slf4j.**
-dontwarn io.ktor.network.sockets.**
-dontwarn kotlinx.serialization.internal.**
-dontwarn org.slf4j.helpers.SubstituteLogger

-keep class io.ktor.client.engine.cio.CIOEngineContainer
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
