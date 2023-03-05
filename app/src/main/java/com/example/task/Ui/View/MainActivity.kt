package com.example.task.Ui.View



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.shuhart.stepview.StepView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var f:Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        setUpView()

    }
    private fun setUpView() {
        binding.stepView.state
            .animationType(StepView.ANIMATION_CIRCLE)
            .stepsNumber(5)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()
    }
}