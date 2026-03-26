# Consumer ProGuard rules for domain:favourites module
# These rules will be applied to consumers of this module

# Keep domain entities
-keep class com.sohaib.modularization.domain.favourites.entity.** { *; }

# Keep use cases
-keep class com.sohaib.modularization.domain.favourites.usecase.** { *; }

# Keep mappers
-keep class com.sohaib.modularization.domain.favourites.mapper.** { *; }
