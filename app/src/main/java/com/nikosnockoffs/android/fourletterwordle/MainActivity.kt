package com.nikosnockoffs.android.fourletterwordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "Correct_Word_Cheat"

class MainActivity : AppCompatActivity() {

    // single length, single line letter text views
    private lateinit var word1Letter1: EditText
    private lateinit var word1Letter2: EditText
    private lateinit var word1Letter3: EditText
    private lateinit var word1Letter4: EditText
    private lateinit var word2Letter1: EditText
    private lateinit var word2Letter2: EditText
    private lateinit var word2Letter3: EditText
    private lateinit var word2Letter4: EditText
    private lateinit var word3Letter1: EditText
    private lateinit var word3Letter2: EditText
    private lateinit var word3Letter3: EditText
    private lateinit var word3Letter4: EditText
    private lateinit var word4Letter1: EditText
    private lateinit var word4Letter2: EditText
    private lateinit var word4Letter3: EditText
    private lateinit var word4Letter4: EditText
    private lateinit var word5Letter1: EditText
    private lateinit var word5Letter2: EditText
    private lateinit var word5Letter3: EditText
    private lateinit var word5Letter4: EditText

    private lateinit var checkWordButton: Button

    private var guessCount = 0


    private val wordListViewModel: WordListViewModel by lazy {
        ViewModelProvider(this).get(WordListViewModel::class.java)
    }

    // build 4 letter word bank
    // TODO track what letter and word user is on
    // TODO make only current letter EditText editable, all others inactive
    // TODO allow user to press enter only when they have entered all 4 letters
    // TODO check user word is real possible word
    // TODO check user word letters against letters in chosen word
    // TODO check user word against chosen word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkWordButton = findViewById(R.id.check_word_button)

        // first row:
        word1Letter1 = findViewById(R.id.W1_L1)
        word1Letter2 = findViewById(R.id.W1_L2)
        word1Letter3 = findViewById(R.id.W1_L3)
        word1Letter4 = findViewById(R.id.W1_L4)
        // second row:
        word2Letter1 = findViewById(R.id.W2_L1)
        word2Letter2 = findViewById(R.id.W2_L2)
        word2Letter3 = findViewById(R.id.W2_L3)
        word2Letter4 = findViewById(R.id.W2_L4)
        // third row:
        word3Letter1 = findViewById(R.id.W3_L1)
        word3Letter2 = findViewById(R.id.W3_L2)
        word3Letter3 = findViewById(R.id.W3_L3)
        word3Letter4 = findViewById(R.id.W3_L4)
        // fourth row:
        word4Letter1 = findViewById(R.id.W4_L1)
        word4Letter2 = findViewById(R.id.W4_L2)
        word4Letter3 = findViewById(R.id.W4_L3)
        word4Letter4 = findViewById(R.id.W4_L4)
        // final row:
        word5Letter1 = findViewById(R.id.W5_L1)
        word5Letter2 = findViewById(R.id.W5_L2)
        word5Letter3 = findViewById(R.id.W5_L3)
        word5Letter4 = findViewById(R.id.W5_L4)

        // start app with focus on first letter
        word1Letter1.requestFocus()

        // pick a random word in the word list
        val correctWord = wordListViewModel.getWord()

        // for testing purposes, show the word
        Toast.makeText(this, "Word is: $correctWord", Toast.LENGTH_LONG).show()

        checkWordButton.setOnClickListener {
            val word1list = listOf(word1Letter1.text.toString(), word1Letter2.text.toString(), word1Letter3.text.toString(), word1Letter4.text.toString())
            val word2list = listOf(word2Letter1.text.toString(), word2Letter2.text.toString(), word2Letter3.text.toString(), word2Letter4.text.toString())
            val word3list = listOf(word3Letter1.text.toString(), word3Letter2.text.toString(), word3Letter3.text.toString(), word3Letter4.text.toString())
            val word4list = listOf(word4Letter1.text.toString(), word4Letter2.text.toString(), word4Letter3.text.toString(), word4Letter4.text.toString())
            val word5list = listOf(word5Letter1.text.toString(), word5Letter2.text.toString(), word5Letter3.text.toString(), word5Letter4.text.toString())
            word1Letter1.setBackgroundColor(Color.GREEN)
            when {
                word5Letter4.text.isNotEmpty() -> {
                    checkWordInList(word5list, correctWord)
                    guessCount++
                }
                word4Letter4.text.isNotEmpty() -> {
                    checkWordInList(word4list, correctWord)
                    guessCount++
                }
                word3Letter4.text.isNotEmpty() -> {
                    checkWordInList(word3list, correctWord)
                    guessCount++
                }
                word2Letter4.text.isNotEmpty() -> {
                    checkWordInList(word2list, correctWord)
                    guessCount++
                }
                word1Letter4.text.isNotEmpty() -> {
                    checkWordInList(word1list, correctWord)
                    guessCount++
                }
                else -> {
                    Toast.makeText(this, "You have to complete a word", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun checkWordInList(word: List<String>, correctWord: String){
        var wordGuessed = ""
        for (letter in word) {
            wordGuessed += letter
        }


        if (wordGuessed.lowercase() == correctWord.lowercase()) {
            Toast.makeText(this, "Nice job!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show()
            for (i in 0..word.size) {
                if (guessCount == 1) {
                    if (wordGuessed[i] == correctWord[i]) {
                        word1Letter1.setBackgroundColor(Color.GREEN)
                    }
                }

            }

//            val tilesToChangeGreen = mutableListOf<String>() // list of right letters, right places
//            val tilesToChangeYellow = mutableListOf<String>() // list of right letters, wrong places
//            for (i in 0..word.size) {
//                if (wordGuessed[i] == correctWord[i]) {
//                    val tileToChange: String = "word".plus(guessCount.toString()).plus("Letter").plus(i.toString())
//                    tilesToChangeGreen.add(tileToChange)
//                } else if (wordGuessed[i] in correctWord) {
//                    val tileToChange: String = "word".plus(guessCount.toString()).plus("Letter").plus(i.toString())
//                    tilesToChangeYellow.add(tileToChange)
//                }
//            }
        }


    }

}

// code from https://stackoverflow.com/questions/38872546/edit-text-for-otp-with-each-letter-in-separate-positions/60462771#60462771
// to try to get each text box to go to the next text box when it is full but only allow user to put in one letter at a time

