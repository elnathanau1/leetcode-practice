package proj.eau.leetcodepractice.arrays.easy

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class BuyAndSellStockII {
    /** Problem
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/564/
     * Say you have an array prices for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit.
     * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     */

    companion object {
        /** Results
         * https://leetcode.com/submissions/detail/455090642/?from=explore&item_id=564
         * Runtime: 72.02%
         * Memory: 55.91%
         */
        /**
         * Efficiency:
         * Time: O(n)
         * Space: O(1)
         */
        private fun maxProfit1(prices: Array<Int>): Int {
            var total = 0
            // parse through prices
            for(i in 0 until prices.size - 1){
                // get the difference between day i and the next day. if positive, buy and then sell. don't worry about holding - you can always buy and the same day you sold
                val diff = prices[i+1] - prices[i]
                if (diff > 0) {
                    total += diff
                }
            }
            return total
        }

        // Used to generate test cases
        @JvmStatic
        fun generateData(): Stream<Arguments> = Stream.of(
                Arguments.of(::maxProfit1)
        )
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 1`(maxProfit: (Array<Int>) -> Int) {
        val prices = arrayOf(7,1,5,3,6,4)
        val expectedResult = 7

        val result = maxProfit(prices)

        result `should be equal to` expectedResult
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 2`(maxProfit: (Array<Int>) -> Int) {
        val prices = arrayOf(1,2,3,4,5)
        val expectedResult = 4

        val result = maxProfit(prices)

        result `should be equal to` expectedResult
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 3`(maxProfit: (Array<Int>) -> Int) {
        val prices = arrayOf(7,6,4,3,1)
        val expectedResult = 0

        val result = maxProfit(prices)

        result `should be equal to` expectedResult
    }
}