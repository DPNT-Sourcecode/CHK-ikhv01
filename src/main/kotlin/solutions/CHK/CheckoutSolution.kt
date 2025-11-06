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



 CHK_R3
ROUND 3 - More items and offers
A new item has arrived. Item F.
Our marketing team wants to try rewording the offer to see if it affects consumption
Instead of multi-pricing this item they want to say "buy 2Fs and get another F free"
The offer requires you to have 3 Fs in the basket.

Our price table and offers:
+------+-------+------------------------+
| Item | Price | Special offers         |
+------+-------+------------------------+
| A    | 50    | 3A for 130, 5A for 200 |
| B    | 30    | 2B for 45              |
| C    | 20    |                        |
| D    | 15    |                        |
| E    | 40    | 2E get one B free      |
| F    | 10    | 2F get one F free      |
+------+-------+------------------------+


Notes:
 - The policy of the supermarket is to always favor the customer when applying special offers.
 - Offers involving multiple items always give a better discount than offers containing fewer items.
 - For any illegal input return -1

In order to complete the round you need to implement the following method:

checkout(string) -> integer
 - param[0] = a string containing the SKUs of all the products in the basket
 - @return = an integer representing the total checkout value of the items

 CHK_R4
ROUND 4 - Broad range of products
Our shop is growing so fast ! We have exceeded all expectations.
But with more clients we get lots of requests for other items.
Our management decided that it is time to start selling a broader range of products.
We just got a good deal for 20 products. Please add the to the system.

Our price table and offers:
+------+-------+------------------------+
| Item | Price | Special offers         |
+------+-------+------------------------+
| A    | 50    | 3A for 130, 5A for 200 |
| B    | 30    | 2B for 45              |
| C    | 20    |                        |
| D    | 15    |                        |
| E    | 40    | 2E get one B free      |
| F    | 10    | 2F get one F free      |
| G    | 20    |                        |
| H    | 10    | 5H for 45, 10H for 80  |
| I    | 35    |                        |
| J    | 60    |                        |
| K    | 80    | 2K for 150             |
| L    | 90    |                        |
| M    | 15    |                        |
| N    | 40    | 3N get one M free      |
| O    | 10    |                        |
| P    | 50    | 5P for 200             |
| Q    | 30    | 3Q for 80              |
| R    | 50    | 3R get one Q free      |
| S    | 30    |                        |
| T    | 20    |                        |
| U    | 40    | 3U get one U free      |
| V    | 50    | 2V for 90, 3V for 130  |
| W    | 20    |                        |
| X    | 90    |                        |
| Y    | 10    |                        |
| Z    | 50    |                        |
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
    E(40),
    F(10),
    G(20),
    H(10),
    I(35),
    J(60),
    K(80),
    L(90),
    M(15),
    N(40),
    O(10),
    P(50),
    Q(30),
    R(50),
    S(30),
    T(20),
    U(40),
    V(50),
    W(20),
    X(90),
    Y(10),
    Z(50);


    companion object {
        fun from(char: Char): Sku? = entries.find { it.name.single() == char }
    }
}

class CheckoutSolution {
    fun checkout(skus: String): Int {
        if (skus.isEmpty()) return 0
        val validSkus = Sku.entries.map { it.name.single() }.toSet()
        if (skus.any { it !in validSkus }) return -1 //found invalid chars

        val grouped = groupItems(skus)

        applyFreeBFromE(grouped)
        applyFreeMFromN(grouped)
        applyFreeQFromR(grouped)
        applyFreeFFor2F(grouped)
        applyFreeUFromU(grouped)
        return calculateTotal(grouped)

    }

    private fun applyFreeUFromU(grouped: kotlin.collections.MutableMap<Sku, Int>) {
        val uCount = grouped.getOrDefault(Sku.U, 0)
        val freeU = uCount / 4
        grouped[Sku.U] = (uCount - freeU).coerceAtLeast(0)
    }

    private fun applyFreeQFromR(grouped: MutableMap<Sku, Int>) {
        val rCount = grouped.getOrDefault(Sku.R, 0)
        val qCount = grouped.getOrDefault(Sku.Q, 0)
        val freeQ = rCount / 3
        grouped[Sku.Q] = (qCount - freeQ).coerceAtLeast(0)
    }

    private fun applyFreeMFromN(grouped: MutableMap<Sku, Int>) {
        val nCount = grouped.getOrDefault(Sku.N, 0)
        val mCount = grouped.getOrDefault(Sku.M, 0)
        val freeM = nCount / 3
        grouped[Sku.M] = (mCount - freeM).coerceAtLeast(0)

    }

    private fun applyFreeFFor2F(grouped: MutableMap<Sku, Int>) {
        val fCount = grouped.getOrDefault(Sku.F, 0)
        val freeF = fCount / 3
        grouped[Sku.F] = (fCount - freeF).coerceAtLeast(0)
    }

    private fun applyFreeBFromE(grouped: MutableMap<Sku, Int>) {
        val eCount = grouped.getOrDefault(Sku.E, 0)
        val freeB = eCount / 2
        val bCount = grouped.getOrDefault(Sku.B, 0)
        grouped[Sku.B] = (bCount - freeB).coerceAtLeast(0)
    }

    private fun calculateTotal(grouped: MutableMap<Sku, Int>): Int = grouped.entries.sumOf { (sku, count) ->
        when (sku) {
            Sku.A -> calculateOfferForA(count)
            Sku.B -> calculateOffer(count, 2, 45, sku.price)
            Sku.H -> calculateTieredOffer(count, listOf(10 to 80, 5 to 45), sku.price)
            Sku.K -> calculateOffer(count, 2, 150, sku.price)
            Sku.P -> calculateOffer(count, 5, 200, sku.price)
            Sku.Q -> calculateOffer(count, 3, 80, sku.price)
            Sku.V -> calculateTieredOffer(count, listOf(3 to 130, 2 to 90), sku.price)
            else -> count * sku.price
        }
    }

    private fun groupItems(skus: String) = skus.mapNotNull { Sku.from(it) }.groupingBy { it }.eachCount().toMutableMap()

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

    private fun calculateTieredOffer(quantity: Int, offers: List<Pair<Int, Int>>, unitPrice: Int): Int {
        var remaining = quantity
        var total = 0
        for ((size, price) in offers.sortedByDescending { it.first }) {
            val bundles = remaining / size
            remaining %= size
            total += bundles * price
        }
        total += remaining * unitPrice
        return total
    }

}









