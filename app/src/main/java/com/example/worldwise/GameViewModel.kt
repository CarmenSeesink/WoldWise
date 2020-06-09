package com.example.worldwise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldwise.data.Question

class GameViewModel : ViewModel() {

    private val _question = MutableLiveData<Question>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private val _amountOfQuestions = MutableLiveData<Int>()
    private val _categoryQuestions = MutableLiveData<List<Question>>()

//    init {
//        _score.value = 0
//        _question.value = questions[0]
//        _currentQuestion.value = 0
//        _amountOfQuestions.value = 3
//    }

    fun setupGame(categoryId: Int) {
        val newQuestions = questions.filter { q -> q.categoryId === categoryId }
        _categoryQuestions.postValue(newQuestions)
        _question.value = newQuestions[0]
        _currentQuestion.value = 0
        _score.value = 0
        _amountOfQuestions.value = 3
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    val amountOfQuestions: LiveData<Int> = _amountOfQuestions

    fun updateQuestion(index: Int) {
        _question.value = _categoryQuestions.value?.get(index.plus(1))
        _currentQuestion.value = index.plus(1)
    }

    fun checkQuestion(answer: Int) {
        val validAnswer: String? = question.value?.correctAnswer
        val submittedAnswer: String? = question.value?.answers?.get(answer)
        if(submittedAnswer == validAnswer ){
            _score.value = score.value?.plus(1)
        }
    }

    private val questions = listOf(
        Question("Which country has the world's highest waterfall?",1, listOf("Uganda", "Venezuela", "America"), "Venezuela"),
        Question("Name the smallest Asian country in terms of both population and area?",1, listOf("Cook Island", "Maldives", "Tuvalu"),"Maldives"),
        Question("How many stars are there on the flag of China?",1, listOf("2", "7", "5"), "5"),
        Question("The word “Ketchup” comes from “Koetsiap”, which is in what language?",2, listOf("Swedish", "Malay", "Chinese"), "Malay"),
        Question("Fortune cookies were first made in which of the following countries?",2, listOf("Japan", "Singapore", "America"), "America"),
        Question("Belgian waffles were invented where?",2, listOf("Belgium", "Netherlands", "Luxembourg"), "Luxembourg"),
        Question("Persepolis FC is a football club of which country?",3, listOf("Greece", "Egypt", "Iran"), "Iran"),
        Question("Which of these is the oldest horse racing event?",3, listOf("Kentucky Derby", "Grand National", "Melbourne Cup"),"Grand National"),
        Question("Where were the 1952 Olympic Games held?",3, listOf("Japan", "Germany", "Helsinki"),"Helsinki"),
        Question("In what country is it considered a compliment to slurp loudly while eating soup?",4, listOf("Iceland", "Japan", "Russia"),"Japan"),
        Question("What is the best way to eat in India?",4, listOf("Using a spoon", "Using your left hand", "Using your right hand"),"Using your right hand"),
        Question("How does a Chinese wife gain respect from her husband's family?",4, listOf("Cuts her hair", "Gives birth to a son", "Bows down to them"),"Gives birth to a son")
    )

    fun generateDummyList(size: Int): List<CategoryItem>{
        val list = ArrayList<CategoryItem>()
        for (i in 1 until size) {
            val item = CategoryItem(i,"Category $i")
            list += item
        }

        return list
    }

     val categories = listOf(
        CategoryItem(1,"General"),
        CategoryItem(2,"Food"),
        CategoryItem(3,"Sport"),
        CategoryItem(4,"Culture")
    )

}