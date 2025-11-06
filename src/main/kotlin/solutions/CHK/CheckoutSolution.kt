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
 */

enum class Unit(val price: Int) {
    A(50),
    B(30),
    C(20),
    D(15)
}

class CheckoutSolution {
    fun checkout(skus: String): Int {
        if (skus.isEmpty()) return -1
        val validSkus = Unit.entries.map { it.name.single() }
        if (skus.any { it !in validSkus }) return -1

        val counts = skus.groupingBy { it }.eachCount() //map of each sku with the quantity

        
    }


}


