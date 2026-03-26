# Consumer ProGuard rules for domain:onboarding module
# These rules will be applied to consumers of this module

# Keep domain entities
-keep class com.sohaib.modularization.domain.onboarding.entity.** { *; }

# Keep use cases
-keep class com.sohaib.modularization.domain.onboarding.usecase.** { *; }

# Keep mappers
-keep class com.sohaib.modularization.domain.onboarding.mapper.** { *; }
