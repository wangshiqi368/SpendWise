package com.spendwise.app.domain.util

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await

object ReceiptAnalyzer {
    suspend fun analyze(context: Context, imageUri: Uri): Double? {
        return try {
            val inputImage = InputImage.fromFilePath(context, imageUri)
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val visionText = recognizer.process(inputImage).await()
            findTotalAmount(visionText.text)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun findTotalAmount(fullText: String): Double? {
        val totalKeywords = listOf("total", "合计", "总额", "应付", "实付", "sum")
        val amountRegex = Regex("""(\d{1,3}(,\d{3})*(\.\d{2}))|\d+\.\d{2}""")
        
        val lines = fullText.lines()
        val potentialAmounts = mutableListOf<Double>()

        for (line in lines.reversed()) {
            if (totalKeywords.any { line.contains(it, ignoreCase = true) }) {
                amountRegex.find(line)?.value?.replace(",", "")?.toDoubleOrNull()?.let {
                    return it
                }
            }
            amountRegex.findAll(line).forEach { match ->
                match.value.replace(",", "").toDoubleOrNull()?.let { potentialAmounts.add(it) }
            }
        }
        
        return potentialAmounts.maxOrNull()
    }
}
