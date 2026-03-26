# Consumer ProGuard rules for domain:settings module
# These rules will be applied to consumers of this module

# Keep domain entities
-keep class com.sohaib.modularization.domain.settings.entity.** { *; }

# Keep use cases
-keep class com.sohaib.modularization.domain.settings.usecase.** { *; }

# Keep mappers
-keep class com.sohaib.modularization.domain.settings.mapper.** { *; }
