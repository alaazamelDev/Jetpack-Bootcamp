package com.example.tipcalculatorarchitectured.utils


fun calculateTipAmount(percentage: Int, bill: Int): Int {
    return percentage * bill / 100
}
