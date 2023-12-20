package com.example.sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreference.databinding.ActivityReciveBinding

class ReciveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReciveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReciveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editor=getSharedPreferences("MY_SETTING", MODE_PRIVATE)
        binding.showData.setText("Hey your Email is ${editor.getString("email",null)}"+"\n"+"your Password is ${editor.getString("password",null)}")
    }
}