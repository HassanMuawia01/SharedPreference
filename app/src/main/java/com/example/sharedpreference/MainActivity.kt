package com.example.sharedpreference

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.example.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listener:SharedPreferences.OnSharedPreferenceChangeListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager=PreferenceManager.getDefaultSharedPreferences(this)
        listener=SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key.equals("Change_UI")){
                if (manager.getBoolean("Change_UI",false)==true){
                    binding.mainLayout.setBackgroundColor(Color.MAGENTA)
                }else{
                    binding.mainLayout.setBackgroundColor(Color.WHITE)

                }
            }

        }
        manager.registerOnSharedPreferenceChangeListener(listener)


        val editor=getSharedPreferences("MY_SETTING",MODE_PRIVATE)
        binding.email.setText(editor.getString("email",null))
        binding.pass.setText(editor.getString("password",null))

        binding.settingButton.setOnClickListener{
            val intent=Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.addData.setOnClickListener {
            val editor=getSharedPreferences("MY_SETTING",MODE_PRIVATE).edit()
            editor.putString("email",binding.email.text.toString())
            editor.putString("password",binding.pass.text.toString())
            editor.apply()
            startActivity(Intent(this,ReciveActivity::class.java))
            Toast.makeText(this@MainActivity,"Add Data",Toast.LENGTH_LONG).show()
        }
    }
}