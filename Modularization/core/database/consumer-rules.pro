# Consumer ProGuard rules for database module
# These rules will be applied to the consuming application

# Keep Room database classes
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *
-keep @androidx.room.Database class *
-keep @androidx.room.TypeConverter class *

# Keep database classes
-keep class com.sohaib.modularization.core.database.** { *; }
