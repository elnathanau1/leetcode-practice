package proj.eau.leetcodepractice.arrays.easy

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class ContainsDuplicate {
    /** Problem
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/578/
     * Given an array of integers, find if the array contains any duplicates.
     * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
     */

    companion object {
        /** Results
         * https://leetcode.com/submissions/detail/455246879/?from=explore&item_id=578
         * Runtime: 56.38%
         * Memory: 22.27%
         */
        /**
         * Efficiency:
         * Time: O(1)
         * Space: O(1)
         */
        /**
         * Summary:
         * Sets can't contain duplicates by definition - so converting the array to a set will get all unique numbers.
         * If the size of the set matches the size of the original array, that means every value inside nums is unique (no dupes)
         */
        private fun containsDuplicate(nums: Array<Int>): Boolean = nums.toSet().size != nums.size

        // Used to generate test cases
        @JvmStatic
        fun generateData(): Stream<Arguments> = Stream.of(
                Arguments.of(::containsDuplicate)
        )
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 1`(containsDuplicate: (Array<Int>) -> Boolean) {
        val nums = arrayOf(1,2,3,1)

        containsDuplicate(nums) `should be equal to` true
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 2`(containsDuplicate: (Array<Int>) -> Boolean) {
        val nums = arrayOf(1,2,3,4)

        containsDuplicate(nums) `should be equal to` false
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 3`(containsDuplicate: (Array<Int>) -> Boolean) {
        val nums = arrayOf(1,1,1,3,3,4,3,2,4,2)

        containsDuplicate(nums) `should be equal to` true
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `empty`(containsDuplicate: (Array<Int>) -> Boolean) {
        val nums = emptyArray<Int>()

        containsDuplicate(nums) `should be equal to` false
    }
}