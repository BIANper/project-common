package icu.yuyurbq.project.util

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * 获取当前时间戳
 */
fun timestamp(): Long = System.currentTimeMillis() / 1000

/**
 * 获取当前时间戳（毫秒）
 */
fun timestampMilli(): Long = System.currentTimeMillis()

/**
 * 获取时间戳
 */
val LocalDateTime.timestamp: Long inline get() = this.atZone(ZoneId.systemDefault()).toEpochSecond()

/**
 * 获取时间戳
 */
val ZonedDateTime.timestamp: Long inline get() = this.toEpochSecond()

/**
 * 获取时间戳（毫秒）
 */
val LocalDateTime.timestampMilli: Long inline get() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

/**
 * 获取时间戳（毫秒）
 */
val ZonedDateTime.timestampMilli: Long inline get() = this.toInstant().toEpochMilli()

/**
 * 时间戳转换成日期
 */
fun Long.toDateTime(zone: ZoneId? = null): ZonedDateTime {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(this), zone ?: ZoneId.systemDefault())
}

/**
 * 格式化时间输出
 */
fun Long.toDateString(format: String = "yyyy-MM-dd HH:mm:ss", zone: ZoneId? = null): String {
    val f = DateTimeFormatter.ofPattern(format)
    return this.toDateTime(zone).format(f)
}

/**
 * 日期转时间戳
 */
fun String.toTimestamp(format: String = "yyyy-MM-dd HH:mm:ss", zone: ZoneId? = null): Long? {
    if (this.isEmpty()) {
        return null
    }
    val f = DateTimeFormatter.ofPattern(format)
    return try {
        val ld = when (val d = f.parseBest(this, LocalDateTime::from, LocalDate::from)) {
            is LocalDateTime -> d.atZone(zone ?: ZoneId.systemDefault())
            is LocalDate -> d.atStartOfDay(zone ?: ZoneId.systemDefault())
            else -> {
                return null
            }
        }
        ld.toEpochSecond()
    } catch (_: DateTimeParseException) {
        null
    }
}

/**
 * 获取系统时间——小时（24小时制）
 */
fun nowHour(): Int = LocalDateTime.now().hour

/**
 * 获取日期
 */
fun nowDay(): Int = LocalDateTime.now().dayOfMonth

/**
 * 获取月份
 */
fun nowMonth(): Int = LocalDateTime.now().monthValue

/**
 * 获取年份
 */
fun nowYear(): Int = LocalDateTime.now().year

/**
 * 获取今天周几（1-7）
 */
fun nowWeekDay(): Int = LocalDateTime.now().dayOfWeek.value


/**
 * 获取当天0点时间戳
 */
fun dayStart(): Long {
    return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).timestamp
}

/**
 * 获取从当天0点经过了多少秒
 */
fun secondOfToday(): Int {
    return LocalTime.now().toSecondOfDay()
}

/**
 * 获取周一0点时间戳
 */
fun weekStart(): Long {
    val now = LocalDate.now()
    return LocalDateTime.of(now.minusDays(now.dayOfWeek.value.toLong()), LocalTime.MIN).timestamp
}

/**
 * 获取从本周一经过了多少秒
 */
fun secondOfWeek(): Int {
    return (timestamp() - weekStart()).toInt()
}

/**
 * 获取指定月份第一天时间戳
 */
fun firstDayOfMonth(month: Int): Long {
    val now = LocalDate.now()
    return LocalDateTime.of(now.year, month, 1, 0, 0, 0).timestamp
}