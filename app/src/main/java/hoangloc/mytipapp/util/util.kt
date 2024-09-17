package hoangloc.mytipapp.util

fun calculateTip(totalBill: Double,
                 tipPercentage: Int): Double {
    return if (tipPercentage == 100) totalBill
    else if (totalBill > 1 &&
        totalBill.toString().isNotEmpty())
        (totalBill * tipPercentage) / 100
    else 0.0
}

fun calculateTotalPerPerson(
    totalBill: Double,
    splitBy: Int,
    tipPercentage: Int): Double
{
    val bill = calculateTip(totalBill = totalBill,
        tipPercentage = tipPercentage) + totalBill
    val perPerson = bill / splitBy
    return perPerson
}