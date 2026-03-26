# Consumer ProGuard rules for feature:entrance module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.entrance.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.entrance.ui.viewmodel.** { *; }
