# Consumer ProGuard rules for feature:language module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.language.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.language.ui.viewmodel.** { *; }
