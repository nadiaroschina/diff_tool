import kotlin.test.*


internal class TestLCS {

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
        assertEquals(listOf("a", "b", "c"),
            lcs(listOf("a", "b", "c", "d", "e"),
                listOf("x", "y", "a", "b", "c")))
        assertEquals(listOf("c", "d", "e"),
            lcs(listOf("a", "b", "c", "d", "e"),
                listOf("c", "d", "e", "x", "y")))
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


internal class TestDiff {

    @Test
    fun smallTestDiff() {
        // checking correctness
        assertEquals(listOf(Line('+', "e"), Line('-', "h"), Line('+', "i"), Line('-', "q"), Line('+', "k"), Line('+', "r"), Line('+', "x"), Line('+', "y")),
            diff(listOf("a", "b", "c", "d", "f", "g", "h", "j", "q", "z"),
                listOf("a", "b", "c", "d", "e", "f", "g", "i", "j", "k", "r", "x", "y", "z")))
        assertEquals(listOf(Line('-', "X"), Line('+', "Z"), Line('-', "Y"), Line('+', "W"), Line('+', "X"), Line('-', "Z")),
            diff(listOf("X", "M", "J", "Y", "A", "U", "Z"),
                listOf("M", "Z", "J", "A", "W", "X", "U")))
        assertEquals(listOf(Line('-', "b"), Line('-', "d")),
            diff(listOf("a", "b", "c", "d", "e"),
                listOf("a", "c", "e")))
        assertEquals(listOf(Line('-', "b"), Line('-', "e"), Line('+', "b")),
            diff(listOf("a", "b", "c", "d", "e"),
                listOf("a", "c", "d", "b")))
        assertEquals(listOf(Line('+', "x"), Line('-', "b"), Line('-', "c"), Line('+', "y"), Line('-', "e"), Line('+', "z")),
            diff(listOf("a", "b", "c", "d", "e"),
                listOf("x", "a", "y", "d", "z")))
        assertEquals(listOf(Line('+', "x"), Line('+', "y"), Line('-', "d"), Line('-', "e")),
            diff(listOf("a", "b", "c", "d", "e"),
                listOf("x", "y", "a", "b", "c")))
        assertEquals(listOf(Line('-', "a"), Line('-', "b"), Line('+', "x"), Line('+', "y")),
            diff(listOf("a", "b", "c", "d", "e"),
                listOf("c", "d", "e", "x", "y")))
    }

    @Test
    fun bigTestDiff() {
        // checking efficiency
        assertEquals(emptyList(),
            diff(List(1000) { "1" },
                List(1000) { "1" }))
        assertEquals(List(500) { Line('+', "1") },
            diff(List(500) { "0" },
                List(1000) {(it % 2).toString()}))
        assertEquals(List(500) { Line ('-', "1") },
            diff(List(1000) {(it % 2).toString()},
                List(500) { "0" }))
    }
}
