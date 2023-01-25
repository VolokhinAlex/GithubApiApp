package com.volokhinaleksey.popularlibrariesandroid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.volokhinaleksey.popularlibrariesandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private val mainPresenter: MainPresenter = MainPresenter(CountersModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listener = View.OnClickListener {
            when(it.id) {
                R.id.btn_counter1 -> mainPresenter.counterClick(0)
                R.id.btn_counter2 -> mainPresenter.counterClick(1)
                R.id.btn_counter3 -> mainPresenter.counterClick(2)
            }
        }
        binding.btnCounter1.setOnClickListener(listener)
        binding.btnCounter2.setOnClickListener(listener)
        binding.btnCounter3.setOnClickListener(listener)
    }

    override fun setButton1Text(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButton2Text(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButton3Text(text: String) {
        binding.btnCounter3.text = text
    }

}