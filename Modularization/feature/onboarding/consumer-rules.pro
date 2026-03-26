# Consumer ProGuard rules for feature:onboarding module
# These rules will be applied to consumers of this module

# Keep UI components
-keep class com.sohaib.modularization.feature.onboarding.ui.** { *; }

# Keep ViewModels
-keep class com.sohaib.modularization.feature.onboarding.ui.viewmodel.** { *; }
