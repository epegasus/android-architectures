# Consumer ProGuard rules for feature:settings module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.settings.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.settings.ui.viewmodel.** { *; }
