# Consumer ProGuard rules for mediastore module
# These rules will be applied to the consuming application

# Keep MediaStore classes
-keep class android.provider.MediaStore { *; }
-keep class android.provider.MediaStore$** { *; }

# Keep mediastore classes
-keep class com.sohaib.modularization.core.mediastore.** { *; }
