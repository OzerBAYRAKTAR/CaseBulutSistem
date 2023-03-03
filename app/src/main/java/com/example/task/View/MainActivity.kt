package com.example.task.View



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.task.databinding.ActivityMainBinding
import com.shuhart.stepview.StepView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.stepView.state
            .animationType(StepView.ANIMATION_CIRCLE)
            .stepsNumber(5)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()

    }
}