package com.study.objects.generic

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
) : Comparable<Money> {
    companion object {
        val ZERO = Money(BigDecimal.ZERO)

        fun wons(amount: Long) = Money(BigDecimal.valueOf(amount))

        fun wons(amount: Double) = Money(BigDecimal.valueOf(amount))

        fun <T> sum(
            bags: Iterable<T>,
            monetary: (T) -> Money,
        ): Money = bags.sumOf { monetary(it).amount }.let(::Money)
    }

    operator fun plus(other: Money) = Money(amount + other.amount)

    operator fun minus(other: Money) = Money(amount - other.amount)

    operator fun times(percent: Double) = Money(amount * BigDecimal.valueOf(percent))

    operator fun div(divisor: Double) = Money(amount / BigDecimal.valueOf(divisor))

    override fun compareTo(other: Money) = amount.compareTo(other.amount)

    val longValue: Long get() = amount.toLong()
    val doubleValue: Double get() = amount.toDouble()
    val formattedValue: String get() = "${amount}원"
}
