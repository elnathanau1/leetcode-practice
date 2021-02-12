package proj.eau.leetcodepractice.arrays.easy

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream


@SpringBootTest
class RemoveDuplicatesFromSortedArrayTests {
    /** Problem
     * https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/727/
     * Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.
     * Do not allocate extra space for another` array, you must do this by modifying the input array in-place with O(1) extra memory.
     */

    companion object{
        /** Results
         * https://leetcode.com/submissions/detail/455066861/?from=explore&item_id=727
         * Runtime: 5.05%
         * Memory: 35.98%
         */
        private fun removeDuplicates1(nums: Array<Int>) : Int {
            // mark the end of the array
            var endIndex = nums.size

            // keep track of where we are
            var currentIndex = 0

            // increment up till we've gone through the whole array
            while (currentIndex < endIndex-1) {
                // if it matches, shift everything after currentIndex down by 1, and lower the endIndex by 1
                if (nums[currentIndex] == nums[currentIndex + 1]){
                    for(j in currentIndex+1 until endIndex-1){
                        nums[j] = nums[j + 1]
                    }
                    endIndex--
                }
                // if it doesn't match, move onto the next item in the array
                else {
                    currentIndex++
                }
            }
            return endIndex
        }

        /** Results
         * https://leetcode.com/submissions/detail/455084229/?from=explore&item_id=727
         * Runtime: 100%
         * Memory: 27.93%
         */
        private fun removeDuplicates2(nums: Array<Int>) : Int {
            // store num of unique values
            var uniqueValues = 0

            // iterate through
            for (i in nums.indices) {
                // if it's the first num, count it as unique
                if (i == 0) {
                    uniqueValues++
                }
                // if nums[i] doesnt match the one before it, add it to the front of the array, incrementing uniqueValues
                else if (nums[i-1] != nums[i]){
                    nums[uniqueValues] = nums[i]
                    uniqueValues++
                }
            }

            return uniqueValues
        }

        // Used to generate test cases
        @JvmStatic
        fun generateData() : Stream<Arguments> = Stream.of(
                Arguments.of(::removeDuplicates1),
                Arguments.of(::removeDuplicates2)
        )
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `happy path test 1`(removeDuplicates: (Array<Int>) -> Int) {
        val array = arrayOf(1, 1, 1, 1, 1, 2)
        val expectedArray = arrayOf(1, 2)
        val expectedLength = 2

        val length = removeDuplicates(array)

        length `should be equal to` expectedLength
        array.copyOfRange(0, length) `should be equal to` expectedArray
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `happy path test 2`(removeDuplicates: (Array<Int>) -> Int) {
        val array = arrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4)
        val expectedArray = arrayOf(0, 1, 2, 3, 4)
        val expectedLength = 5

        val length = removeDuplicates(array)

        length `should be equal to` expectedLength
        array.copyOfRange(0, length) `should be equal to` expectedArray
    }


    @ParameterizedTest
    @MethodSource("generateData")
    fun `empty array`(removeDuplicates: (Array<Int>) -> Int) {
        val array = emptyArray<Int>()
        val expectedArray = emptyArray<Int>()
        val expectedLength = 0

        val length = removeDuplicates(array)

        length `should be equal to` expectedLength
        array `should be equal to` expectedArray
    }

    @ParameterizedTest
    @MethodSource("generateData")
    fun `single value in array`(removeDuplicates: (Array<Int>) -> Int) {
        val array = arrayOf(1)
        val expectedArray = arrayOf(1)
        val expectedLength = 1

        val length = removeDuplicates(array)

        length `should be equal to` expectedLength
        array `should be equal to` expectedArray
    }

}
