# Consumer ProGuard rules for feature:favourites module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.favourites.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.favourites.ui.viewmodel.** { *; }
