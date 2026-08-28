# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames

# Keep line numbers for crash logs
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep custom application classes
-keep class com.example.malaylanguage.** { *; }

# Keep GSON classes
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep View constructors for LayoutInflater
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Keep Parcelable
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Fragment classes
-keep public class * extends androidx.fragment.app.Fragment

# Keep Activity classes
-keep public class * extends androidx.appcompat.app.AppCompatActivity
