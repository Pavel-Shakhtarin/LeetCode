import kotlin.math.abs
import kotlin.math.max

fun main(args: Array<String>) {
//    merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)
//    merge(intArrayOf(0), 0, intArrayOf(1), 1)
//    merge(intArrayOf(1), 1, intArrayOf(), 0)
//    merge(intArrayOf(2, 0), 1, intArrayOf(1), 1)
//    removeInPlace(intArrayOf(3, 2, 2, 3), 3)
    containsDouble(intArrayOf(3, 1, 7, 11))
}

fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var maxCons = 0
    var currentSeq = 0
    nums.forEach {
        if (it == 1) currentSeq++
        else {
            if (currentSeq > 0) maxCons = max(currentSeq, maxCons)
            currentSeq = 0
        }
    }
    return max(currentSeq, maxCons)
}

fun findNumbers(nums: IntArray): Int {
    var evenNumCounter = 0
    nums.forEach {
        var left = it
        var digitCounter = 0
        while (left > 0) {
            digitCounter++
            left /= 10
        }
        if (digitCounter % 2 == 0) evenNumCounter++
    }
    return evenNumCounter
}

fun sortedSquares(nums: IntArray): IntArray {
    val result = intArrayOf(*nums)
    var resultPointer = nums.size - 1
    var head = 0
    var tail = nums.size - 1
    while (resultPointer >= 0) {
        val headEl = nums[head]
        val tailEl = nums[tail]
        if (abs(headEl) > abs(tailEl)) {
            result[resultPointer] = headEl * headEl
            head++
        } else {
            result[resultPointer] = tailEl * tailEl
            tail--
        }
        resultPointer--
    }
    return result
}

fun duplicateZeros(arr: IntArray) {
    val result = intArrayOf(*arr)
    var sPointer = 0
    var rPointer = 0
    while (sPointer < arr.size) {
        if (rPointer < result.size) {
            val el = arr[sPointer]
            if (el == 0) result[rPointer++] = 0
            if (rPointer < arr.size) result[rPointer++] = el
        }
        arr[sPointer] = result[sPointer]
        sPointer++
    }
    arr.forEach { print("$it ") }
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var mainPointer = m + n - 1
    var countdown = if (mainPointer > 0) mainPointer else 1
    var fPointer = if (m - 1 >= 0) m - 1 else 0
    var sPointer = if (n - 1 >= 0) n - 1 else 0
    while (countdown > 0) {
        val num1El = if (nums1.isNotEmpty()) nums1[fPointer] else null
        val num2El = if (nums2.isNotEmpty()) nums2[sPointer] else null
        if (num1El >= num2El) {
            nums1[mainPointer] = num1El!!
            fPointer--
        } else {
            nums1[mainPointer] = num2El!!
            sPointer--
        }
        mainPointer--
        countdown--
    }
    println("Result:")
    nums1.forEach { print("$it ") }
    println()
}

fun removeInPlace(nums: IntArray, value: Int): Int {
    var headP = 0
    var tailP = nums.size - 1
    var counter = 0
    while (tailP > headP) {
        val head = nums[headP]
        val tail = nums[tailP]
        if (head == value && tail != value) {
            nums[headP] = tail
            tailP--
            counter++
        } else if (head != value && tail == value) {
            tailP--
        } else if (head != value) {
            headP++
            counter++
        } else {
            tailP--
        }
    }
    nums.forEach { print("$it ") }
    println()
    println("$counter")
    return counter
}

private operator fun Int?.compareTo(nullableNumber: Int?): Int {
    return if (this != null && nullableNumber != null) {
        if (this == nullableNumber) 0 else if (max(this, nullableNumber) == this) 1 else -1
    } else if (this == null && nullableNumber != null) -1
    else if (this != null) 1
    else throw Error("Both numbers are null")
}

fun containsDouble(arr: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    arr.forEach {
        if (set.contains(it * 2) || (it % 2 == 0 && set.contains(it / 2))) return true
        set += it
    }
    return false
}