package com.example.tipcalculatorarchitectured.utils


fun calculateTipAmount(percentage: Int, bill: Int): Int {
    return percentage * bill / 100
}

fun calculateTotalPerPerson(totalBill: Double, splitBy: Int, tipPercentage: Int): Double {
    val bill = calculateTipAmount(percentage = tipPercentage, bill = totalBill.toInt()) + totalBill

    return (bill / splitBy)

}
