package com.sohaib.modularization.core.extensions

import java.text.DecimalFormat
import java.util.Locale

fun Long.formatFileSize(): String {
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    var fileSize = this.toDouble()
    var unitIndex = 0

    while (fileSize >= 1024 && unitIndex < units.size - 1) {
        fileSize /= 1024
        unitIndex++
    }

    val decimalFormat = DecimalFormat("#.##")
    return "${decimalFormat.format(fileSize)} ${units[unitIndex]}"
}

fun Long.formatDuration(): String {
    val seconds = this / 1000
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format(Locale.ENGLISH, "%d:%02d", minutes, remainingSeconds)
}

fun Long.formatDate(): String {
    val days = (System.currentTimeMillis() - this) / (1000 * 60 * 60 * 24)
    return when {
        days == 0L -> "today"
        days == 1L -> "yesterday"
        days < 7L -> "$days days ago"
        days < 30L -> "${days / 7} weeks ago"
        else -> "${days / 30} months ago"
    }
}