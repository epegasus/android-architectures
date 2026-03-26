# Consumer ProGuard rules for feature:images module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.images.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.images.ui.viewmodel.** { *; }
