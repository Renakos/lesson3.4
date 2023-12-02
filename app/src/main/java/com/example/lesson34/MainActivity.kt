package com.example.lesson34

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var btnWhatsapp: ImageView
    private lateinit var numberInput: EditText
    private lateinit var imageChoose: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnWhatsapp = this.findViewById(R.id.whatsapp)
        numberInput = this.findViewById(R.id.entereditt)
        imageChoose = this.findViewById(R.id.enterImage)
        openWhatsappChat()
        imageChoose()

    }

    private fun imageChoose() {
        imageChoose.setOnClickListener {
            val getContent =
                registerForActivityResult(ActivityResultContracts.GetContent()) { changeImage: Uri? ->
                    imageChoose.setImageURI(changeImage)
                }
            getContent.launch("image/*")
        }
    }

    private fun openWhatsappChat() {
        btnWhatsapp.setOnClickListener {
            val whatsappNumberUrl = "wa.me/"
            val number = numberInput.text.toString().trim()
            val whatsappIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("$whatsappNumberUrl$number"))
            startActivity(whatsappIntent)
        }
    }
}