package com.example.worldwise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.worldwise.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game, container, false)
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.score.text = "Score: $newScore"
        })

        viewModel.question.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.question.text = newQuestion.question
            val rGroup = binding.radioGroup
            rGroup.removeAllViews()

            for((index, answer) in newQuestion.answers.withIndex()){
                val newRBtn = createRadioBtn(answer, index)
                rGroup.addView((newRBtn))
            }
        })

        binding.submitButton.setOnClickListener { view : View ->
            val id = binding.radioGroup.checkedRadioButtonId
            viewModel.checkQuestion(id)
            Toast.makeText(context, "This is the current ID: ${id}", Toast.LENGTH_SHORT).show()
            if(viewModel.currentQuestion.value!! < viewModel.amountOfQuestions.value!!-1){
                viewModel.updateQuestion(viewModel.currentQuestion.value ?: 0)
            } else {
                if(viewModel.score.value!! == viewModel.amountOfQuestions.value!!){
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                }
                else {
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameLostFragment)
                }

            }

        }

        return binding.root
    }

    private fun createRadioBtn(answer: String, id: Int): RadioButton{
        val rdb = RadioButton(context)
        rdb.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rdb.text = answer
        rdb.id = id
        return rdb
    }

}
