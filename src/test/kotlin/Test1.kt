import kotlin.test.*

internal class Test1 {

    @Test
    fun smallTestLCS() {
        // checking correctness
        assertEquals(listOf("a", "b", "c", "d", "f", "g", "j", "z"),
            lcs(listOf("a", "b", "c", "d", "f", "g", "h", "j", "q", "z"),
                listOf("a", "b", "c", "d", "e", "f", "g", "i", "j", "k", "r", "x", "y", "z")))
        assertEquals(listOf("M", "J", "A", "U"),
            lcs(listOf("X", "M", "J", "Y", "A", "U", "Z"),
                listOf("M", "Z", "J", "A", "W", "X", "U")))
        assertEquals(listOf("a", "c", "e"),
            lcs(listOf("a", "b", "c", "d", "e"),
                listOf("a", "c", "e")))
        assertEquals(listOf("a", "c", "d"),
            lcs(listOf("a", "b", "c", "d", "e"),
                listOf("a", "c", "d", "b")))
        assertEquals(listOf("a", "d"),
            lcs(listOf("a", "b", "c", "d", "e"),
                listOf("x", "a", "y", "d", "z")))
    }

    @Test
    fun bigTestLCS() {
        // checking efficiency
        assertEquals(List(1000) { "1" },
            lcs(List(1000) {"1"},
                List(1000) {"1"}))
        assertEquals(List(500) { (it % 2).toString() },
            lcs(List(1000) {(it % 2).toString()},
                List(1000) {(it % 4).toString()}))
        assertEquals(List(500) { "1" },
            lcs(List(1000) {(it % 2 + 1).toString()},
                List(1000) {(it % 2).toString()}))
    }
}
