package br.com.vostre.visid.data

import androidx.room.TypeConverter
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): DateTime? {
        return if (value != null) {
            //dateTime.withDate(new LocalDate(value));
            DateTime(
                value,
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"))
            )
        } else {
            null
        }
    }

    @TypeConverter
    fun dateTimeToTimestamp(dateTime: DateTime?): Long? {
        return dateTime?.millis
    }

}