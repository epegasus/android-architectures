# Consumer ProGuard rules for domain:videos module
# These rules will be applied to consumers of this module

# Keep domain entities
-keep class com.sohaib.modularization.domain.videos.entity.** { *; }

# Keep use cases
-keep class com.sohaib.modularization.domain.videos.usecase.** { *; }

# Keep mappers
-keep class com.sohaib.modularization.domain.videos.mapper.** { *; }
