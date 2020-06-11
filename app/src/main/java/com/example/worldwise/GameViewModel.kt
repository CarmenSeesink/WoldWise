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
        Question("What do you call a bull fighter?","bull.png",1, listOf("Matador", "Corrida", "Estocade"), "Matador"),
        Question("What animal is considered sacred in Egypt?","egypt.png",1, listOf("Dog", "Cat", "Cow"),"Cat"),
        Question("How does a Chinese wife gain respect from her husband's family?","chinese.png",1, listOf("Cuts her hair", "Gives birth to a son", "Bows down to them"), "Gives birth to a son"),
        Question("The word “Ketchup” comes from “Koetsiap”, which is in what language?","ketchup.png",2, listOf("Swedish", "Malay", "Chinese"), "Malay"),
        Question("Pretzels were first made in which of the following countries?","pretzel.png",2, listOf("Germany", "Luxembourg", "America"), "Germany"),
        Question("In Japan, sushi is meant to be eaten with?","sushi.png",2, listOf("Chopsticks", "Your hands", "A fork"), "Your hands"),
        Question("Persepolis FC is a football club of which country?","soccer.png",3, listOf("Greece", "Egypt", "Iran"), "Iran"),
        Question("In which country did boxing originate?","box.png",3, listOf("America", "Croatia", "Egypt"),"Egypt"),
        Question("Where were the 1952 Olympic Games held?","olympic.png",3, listOf("Japan", "Germany", "Helsinki"),"Helsinki"),
        Question("How many entrances did the colosseum in Rome have?","colosseum.png",4, listOf("3", "8", "11"),"8"),
        Question("How heavy is the Fernsehturm in Berlin?","berlin.png",4, listOf("48 tons", "480 tons", "4800 tons"),"4800 tons"),
        Question("The Washington Monument is a type of?","obelisk.png",4, listOf("Pyramid", "Obelisk", "Monolith"),"Obelisk")
    )

     val categories = listOf(
        CategoryItem(1,"Culture", "mexican.png"),
        CategoryItem(2,"Food", "sandwich.png"),
        CategoryItem(3,"Sport", "pingpong.png"),
        CategoryItem(4,"Monuments", "sphinx.png")
    )

}