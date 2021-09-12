import java.io.File
import java.io.PrintWriter
import kotlin.math.max



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
fun diff(originalText: List<String>, newText: List<String>): List<String> {
    return lcs(originalText, newText)
}


fun main(args: Array<String>) {

    // input data: files originalText.txt and newText.txt
    val originalText = File("src/main/kotlin/originalText.txt").readLines()
    val newText = File("src/main/kotlin/newText.txt").readLines()

    // using diff function
    val diffText = diff(originalText, newText)

    // output data: file resText.txt
    val resText = PrintWriter("src/main/kotlin/resText.txt")
    for (line in diffText) resText.println(line)
    resText.close()

}

