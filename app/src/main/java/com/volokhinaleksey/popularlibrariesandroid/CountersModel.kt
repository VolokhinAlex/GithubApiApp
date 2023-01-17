package com.volokhinaleksey.popularlibrariesandroid

class CountersModel {
    private val counters = mutableListOf(0, 0, 0)

    fun getCounter(itemIndex: Int) = counters[itemIndex]

    fun nextCount(itemIndex: Int): Int {
        counters[itemIndex]++
        return getCounter(itemIndex)
    }

    fun setCount(itemIndex: Int, newValue: Int) {
        counters[itemIndex] = newValue
    }
}