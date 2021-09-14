import java.io.File
import java.io.PrintWriter
import kotlin.math.max


data class Line (val symbol : Char, val string : String) {
    override fun toString(): String {
        return ("$symbol $string")
    }
}


// auxiliary function
fun printTable(m: Array<Array<Int>>) {
    print("     ")
    for (i in m[0].indices) print("$i ")
    println()

    for (i in m.indices) {
        print("$i :  ")
        for (j in m[i].indices) print(m[i][j].toString() + " ")
        println()
    }
}


// LCS function finds the largest common sequence of strings in two given texts
fun lcs(text1: List<String>, text2: List<String>) : List<String> {

    // res[i][j] stores the length of LCS for the first i strings of text1 and first j strings of text2
    val res : Array<Array<Int>> = Array(text1.size + 1) { Array(text2.size + 1) { 0 } }

    // dynamic programming realization
    for (i in text1.indices) {
        for (j in text2.indices) {
            if (text1[i] == text2[j]) {
                res[i + 1][j + 1] = res[i][j] + 1
            }
            else {
                res[i + 1][j + 1] = max(res[i][j + 1], res[i + 1][j])
            }
        }
    }

    // printTable(res)

    val resText : ArrayList<String> = arrayListOf()
    var i = text1.size
    var j = text2.size

    // retracting LCS using res table
    while (i > 0 && j > 0) {
        if (res[i - 1][j] == res[i][j]) {
            i -= 1
        }
        else if (res [i][j - 1] == res[i][j]) {
            j -= 1
        }
        else {
            resText.add(text1[i - 1])
            i -= 1
            j -= 1
        }
    }
    resText.reverse()

    return resText.toList()
}


// diff function creates the edit script for two given text using LCS function
fun diff(originalText: List<String>, newText: List<String>): List<Line> {
    val mutualText = lcs(originalText, newText)
    val resLines : ArrayList<Line> = arrayListOf()
    // ind1 - current position in original Text, ind2 -  in newText, ind - in mutualText
    var ind1 = 0
    var ind2 = 0
    var ind = 0
    // merging into resText
    while (ind1 < originalText.size || ind2 < newText.size) {
        if (ind < mutualText.size) {
            if (originalText[ind1] == newText[ind2] && originalText[ind1] == mutualText[ind]) {
                // resLines.add(Line(' ', mutualText[ind]))
                ind1++
                ind2++
                ind++
            }
            else if (originalText[ind1] == mutualText[ind]) {
                resLines.add(Line('+', newText[ind2]))
                ind2++
            } else {
                resLines.add(Line('-', originalText[ind1]))
                ind1++
            }
        }
        else {
            while (ind1 < originalText.size) {
                resLines.add(Line('-', originalText[ind1]))
                ind1++
            }
            while (ind2 < newText.size) {
                resLines.add(Line('+', newText[ind2]))
                ind2++
            }
        }
    }
    return resLines.toList()
}


fun printLines(fileName : String, diffText : List<Line>) {
    val resText = PrintWriter(fileName)
    for (line in diffText) resText.println(line)
    resText.close()
}


fun main(args: Array<String>) {

    // input data: files originalText.txt and newText.txt
    val originalText = File("src/main/kotlin/originalText.txt").readLines()
    val newText = File("src/main/kotlin/newText.txt").readLines()

    // using diff function
    val diffLines = diff(originalText, newText)

    // output data: file resText.txt
    printLines("src/main/kotlin/resText.txt", diffLines)

}

