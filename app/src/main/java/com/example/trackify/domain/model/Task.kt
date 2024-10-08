package com.example.trackify.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trackify.domain.LocalTimeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Entity(tableName = "tasks_tbl")
@TypeConverters(
    LocalTimeConverter::class
)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCompleted: Boolean,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val reminder: Boolean,
    val category: String,
    val priority: Int=0

) {
    fun getFormattedTime(): String {
        val startTimeFormat = formatTime(startTime)
        val endTimeFormat = formatTime(endTime)
        return "$startTimeFormat - $endTimeFormat"
    }


    private fun formatTime(time: LocalTime): String {
        val dtf = DateTimeFormatter.ofPattern("hh:mm a")
        return time.format(dtf)
    }

    fun getDuration():Long{
        return (endTime.toSecondOfDay()-startTime.toSecondOfDay()).toLong()
    }

    fun getFormattedDuration(duration: Long):String{
        val hours = duration/3600
        val minutes = (duration%3600)/60
        val seconds = duration%60

        val formattedTime = if (hours==0L){
            String.format("%02d:%02d",minutes,seconds)
        }else{
            String.format("%02d:%02d:%02d",hours,minutes,seconds)
        }

        return formattedTime

    }
}
