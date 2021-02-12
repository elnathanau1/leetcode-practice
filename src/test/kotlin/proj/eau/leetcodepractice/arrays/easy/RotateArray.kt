package proj.eau.leetcodepractice.arrays.easy

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class RotateArray {
    /** Problem
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/646/
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     */

    companion object {
        /**
         * Results
         * https://leetcode.com/submissions/detail/455098098/?from=explore&item_id=646
         * Runtime: 5.58%
         * Memory: 95.89%
         */
        /**
         * Efficiency:
         * Time: O(k * n)
         * Space: O(1)
         */
        /**
         * Summary:
         * brute force - just rotate k % nums.size times
         */
        private fun rotate1(nums: Array<Int>, k: Int) {
            // ex: if an array has 3 values and shifts 3 times...everything stays the same
            if (nums.isEmpty()) return
            val trueShift = k % nums.size

            // shift as many times as we need to
            for (i in 1..trueShift) {
                rotate1Helper(nums)
            }

        }

        /**
         * Helper method that rotates an array just once
         */
        private fun rotate1Helper(nums:Array<Int>) {
            // if it's empty, don't bother
            if (nums.isEmpty()) {
                return
            }
            // save temp so nums[0] isn't lost forever
            val temp = nums[0]
            // shift everything up by 1
            for (i in nums.size-1 downTo 0) {
                if (i == nums.size - 1){
                    nums[0] = nums[i]
                }
                else {
                    nums[i+1] = nums[i]
                }
            }

            // if it exists, put temp back where index 0 should have gone (index 1)
            if (nums.size > 1) {
                nums[1] = temp
            }
        }

        /**
         * Results:
         * https://leetcode.com/submissions/detail/455230892/?from=explore&item_id=646
         * Runtime: 100%
         * Memory: 29.92%
         */
        /**
         * Efficiency:
         * Time: O(n)
         * Space: O(2n) = O(n)
         */
        /**
         * Summary:
         * Did some math to take the array size and k to figure out which index corresponds to the shifted value.
         * Created a array copy so that we don't override any values we need.
         */
        private fun rotate2(nums: Array<Int>, k: Int) {
            // if nums is empty or doesn't need to be shifted, exit
            if (nums.isEmpty() || k % nums.size == 0) return
            val copyNums = nums.copyOf()

            for (i in copyNums.indices) {
                val origIndex = (i + k) % nums.size
                nums[origIndex] = copyNums[i]
            }
        }

        // Used to generate test cases
        @JvmStatic
        fun generateData(): Stream<Arguments> = Stream.of(
                Arguments.of(::rotate1),
                Arguments.of(::rotate2)

        )
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 1`(rotate: (Array<Int>, Int) -> Unit) {
        val nums = arrayOf(1,2,3,4,5,6,7)
        val k = 3
        val expectedResult = arrayOf(5,6,7,1,2,3,4)

        rotate(nums, k)

        nums `should be equal to` expectedResult
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `example 2`(rotate: (Array<Int>, Int) -> Unit) {
        val nums = arrayOf(-1,-100,3,99)
        val k = 2
        val expectedResult = arrayOf(3,99,-1,-100)

        rotate(nums, k)

        nums `should be equal to` expectedResult
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `empty array`(rotate: (Array<Int>, Int) -> Unit) {
        val nums = emptyArray<Int>()
        val k = 2
        val expectedResult = emptyArray<Int>()

        rotate(nums, k)

        nums `should be equal to` expectedResult
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `single entry array`(rotate: (Array<Int>, Int) -> Unit) {
        val nums = arrayOf(1)
        val k = 5
        val expectedResult = arrayOf(1)

        rotate(nums, k)

        nums `should be equal to` expectedResult
    }

}