# Consumer ProGuard rules for feature:videos module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.videos.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.videos.ui.viewmodel.** { *; }
