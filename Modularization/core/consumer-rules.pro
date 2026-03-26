# Consumer ProGuard rules for core module
# These rules will be applied to the consuming application

# Keep Room database classes
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *
-keep @androidx.room.Database class *

# Keep Koin classes
-keep class org.koin.** { *; }

# Keep base classes
-keep class com.sohaib.modularization.core.base.** { *; }
-keep class com.sohaib.modularization.core.navigation.** { *; }
