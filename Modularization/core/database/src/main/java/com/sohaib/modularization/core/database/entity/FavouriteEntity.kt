package com.sohaib.modularization.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

/**
 * Entity representing a favourite media item
 */
@Entity(tableName = "favourites")
@TypeConverters(Converters::class)
data class FavouriteEntity(
    @PrimaryKey val id: Long,
    val mediaType: MediaType,
    val name: String,
    val uri: String,
    val size: Long,
    val dateAdded: Long,
    val dateFavourited: Date = Date()
)

/**
 * Enum representing media types
 */
enum class MediaType {
    IMAGE,
    VIDEO
}

/**
 * Type converters for Room database
 */
class Converters {
    @TypeConverter
    fun fromMediaType(mediaType: MediaType): String {
        return mediaType.name
    }

    @TypeConverter
    fun toMediaType(mediaType: String): MediaType {
        return MediaType.valueOf(mediaType)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }
}