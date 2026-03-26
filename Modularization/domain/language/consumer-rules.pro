# Consumer ProGuard rules for domain:language module
# These rules will be applied to consumers of this module

# Keep domain entities
-keep class com.sohaib.modularization.domain.language.entity.** { *; }

# Keep use cases
-keep class com.sohaib.modularization.domain.language.usecase.** { *; }

# Keep mappers
-keep class com.sohaib.modularization.domain.language.mapper.** { *; }
