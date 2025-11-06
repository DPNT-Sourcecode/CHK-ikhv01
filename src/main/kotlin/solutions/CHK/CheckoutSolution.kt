package solutions.CHK

/*
CHK_R1
ROUND 1 - Our supermarket
The purpose of this challenge is to implement a supermarket checkout that calculates the total price of a number of items.

In a normal supermarket, things are identified using Stock Keeping Units, or SKUs.
In our store, we'll use individual letters of the alphabet (A, B, C, and so on).
Our goods are priced individually. In addition, some items are multi-priced: buy n of them, and they'll cost you y pounds.
For example, item A might cost 50 pounds individually, but this week we have a special offer:
 buy three As and they'll cost you 130.

Our price table and offers:
+------+-------+----------------+
| Item | Price | Special offers |
+------+-------+----------------+
| A    | 50    | 3A for 130     |
| B    | 30    | 2B for 45      |
| C    | 20    |                |
| D    | 15    |                |
+------+-------+----------------+


Notes:
 - For any illegal input return -1

In order to complete the round you need to implement the following method:

checkout(string) -> integer
 - param[0] = a string containing the SKUs of all the products in the basket
 - @return = an integer representing the total checkout value of the items

 CHK_R2
ROUND 2 - More offers
The checkout feature is great and our supermarket is doing fine. Is time to think about growth.
Our marketing teams wants to experiment with new offer types and we should do our best to support them.

We are going to sell a new item E.
Normally E costs 40, but if you buy 2 of Es you will get B free. How cool is that ? Multi-priced items also seemed to work well so we should have more of these.

Our price table and offers:
+------+-------+------------------------+
| Item | Price | Special offers         |
+------+-------+------------------------+
| A    | 50    | 3A for 130, 5A for 200 |
| B    | 30    | 2B for 45              |
| C    | 20    |                        |
| D    | 15    |                        |
| E    | 40    | 2E get one B free      |
+------+-------+------------------------+


Notes:
 - The policy of the supermarket is to always favor the customer when applying special offers.
 - Offers involving multiple items always give a better discount than offers containing fewer items.
 - For any illegal input return -1

In order to complete the round you need to implement the following method:

checkout(string) -> integer
 - param[0] = a string containing the SKUs of all the products in the basket
 - @return = an integer representing the total checkout value of the items
 */

enum class Sku(val price: Int) {
    A(50),
    B(30),
    C(20),
    D(15),
    E(40);

    companion object {
        fun from(char: Char): Sku? = entries.find { it.name.single() == char }
    }
}

class CheckoutSolution {
    fun checkout(skus: String): Int {
        if (skus.isEmpty()) return 0
        val validSkus = Sku.entries.map { it.name.single() }.toSet()
        if (skus.any { it !in validSkus }) return -1 //found invalid chars

        val grouped = skus.mapNotNull { Sku.from(it) }.groupingBy { it }.eachCount().toMutableMap()
        val eCount = grouped.getOrDefault(Sku.E, 0)
        val freeB = eCount / 2
        val bCount = grouped.getOrDefault(Sku.B, 0)
        grouped[Sku.B] = (bCount - freeB).coerceAtLeast(0)


        return grouped.entries.sumOf { (sku, count) ->
            when (sku) {
                Sku.A -> calculateOfferForA(count)
                Sku.B -> calculateOffer(count, 2, 45, sku.price)
                else -> count * sku.price
            }

        }

    }

    private fun calculateOffer(
        quantity: Int,
        bundleSize: Int,
        bundlePrice: Int,
        unitPrice: Int
    ): Int {
        val (offers, remainder) = quantity / bundleSize to quantity % bundleSize
        return offers * bundlePrice + remainder * unitPrice
    }

    private fun calculateOfferForA(quantity: Int): Int {
        val fiveBundles = quantity / 5
        val remainderAfterFive = quantity % 5
        val threeBundles = remainderAfterFive / 3
        val remainder = remainderAfterFive % 3
        return fiveBundles * 200 + threeBundles * 130 + remainder * Sku.A.price
    }

}




