# Consumer ProGuard rules for feature:media-detail module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.media.detail.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.media.detail.ui.viewmodel.** { *; }
