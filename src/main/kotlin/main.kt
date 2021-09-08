import java.io.File
import java.io.PrintWriter


// LCS function finds the largest common sequence of strings in two given texts
fun LCS(text1: List<String>, text2: List<String>) : List<String> {
    TODO()
}

// diff function creates the edit script for two given text using LCS function
fun diff(originalText: List<String>, newText: List<String>) : List<String> {
    TODO()
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

