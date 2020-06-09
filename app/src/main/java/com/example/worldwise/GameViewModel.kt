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
        Question("Our First Question",1, listOf("answerOne", "answerTwo", "answerThree"), "answerOne"),
        Question("Our Second Question",1, listOf("answerOne", "answerTwo", "answerThree"),"answerOne"),
        Question("Our Third Question",1, listOf("answerOne", "answerTwo", "answerThree"), "answerOne"),
        Question("Our First Question",2, listOf("answerOne", "answerTwo", "answerThree"), "answerOne"),
        Question("Our Second Question",2, listOf("answerOne", "answerTwo", "answerThree"), "answerOne"),
        Question("Our Third Question",2, listOf("answerOne", "answerTwo", "answerThree"), "answerOne"),
        Question("Our First Question",3, listOf("answerOne", "answerTwo", "answerThree"), "answerOne"),
        Question("Our Second Question",3, listOf("answerOne", "answerTwo", "answerThree"),"answerOne"),
        Question("Our Third Question",3, listOf("answerOne", "answerTwo", "answerThree"),"answerOne")
    )

}