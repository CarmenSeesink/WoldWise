package com.example.worldwise

import android.annotation.SuppressLint
import android.net.Uri
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
import com.bumptech.glide.Glide
import com.example.worldwise.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.score.text = "CURRENT SCORE $newScore"
        })

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { newCurrent ->
            binding.currentQuestion.text = "QUESTION ${newCurrent + 1}"
        })

        viewModel.question.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.question.text = newQuestion.question
            Glide.with(this).load(Uri.parse("file:///android_asset/${newQuestion.image}")).into(binding.imageView)
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
//            Toast.makeText(context, "This is the current ID: ${id}", Toast.LENGTH_SHORT).show()
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
