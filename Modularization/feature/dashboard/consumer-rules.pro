# Consumer ProGuard rules for feature:dashboard module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.dashboard.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.dashboard.ui.viewmodel.** { *; }
