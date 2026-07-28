package github.com.pinmarigor.vigia.data.extensions

import com.google.firebase.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

fun Timestamp.toLocalDateTime(): LocalDateTime =
    toDate()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

fun LocalDateTime.toTimestamp(): Timestamp =
    Timestamp(
        Date.from(
            atZone(ZoneId.systemDefault())
                .toInstant()
        )
    )