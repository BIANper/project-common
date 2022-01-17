package icu.yuyurbq.project.util

import java.math.BigDecimal

fun Int.toBigDecimal(limit: Int = 4): BigDecimal = BigDecimal.valueOf(this.toLong()).setScale(limit, BigDecimal.ROUND_HALF_UP)
fun Long.toBigDecimal(limit: Int = 4): BigDecimal = BigDecimal.valueOf(this).setScale(limit, BigDecimal.ROUND_HALF_UP)
fun Float.toBigDecimal(limit: Int = 4): BigDecimal = BigDecimal.valueOf(this.toDouble()).setScale(limit, BigDecimal.ROUND_HALF_UP)
fun Double.toBigDecimal(limit: Int = 4): BigDecimal = BigDecimal.valueOf(this).setScale(limit, BigDecimal.ROUND_HALF_UP)
fun String.toBigDecimal(limit: Int = 4): BigDecimal = BigDecimal(this).setScale(limit, BigDecimal.ROUND_HALF_UP)

fun Int.toCNY() = this.toBigDecimal(2)
fun Long.toCNY() = this.toBigDecimal(2)
fun Float.toCNY() = this.toBigDecimal(2)
fun Double.toCNY() = this.toBigDecimal(2)
fun String.toCNY() = this.toBigDecimal(2)