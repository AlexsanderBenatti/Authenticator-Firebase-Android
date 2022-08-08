package com.example.autenticador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val btnCadastrarUsuario: Button = findViewById(R.id.btnCadastrar1)

        btnCadastrarUsuario.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val btnLogar: Button = findViewById(R.id.btnLogin)

        btnLogar.setOnClickListener{
            performLogin()
        }
    }

    private fun performLogin() {
        val edtEmail: EditText = findViewById(R.id.edtLogin)
        val edtSenha: EditText = findViewById(R.id.edtSenha)

        if (edtEmail.text.isEmpty() || edtSenha.text.isEmpty()){
            Toast.makeText(this, "Por favor, preencher os campos para efetuar o login", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = edtEmail.text.toString()
        val inputSenha = edtSenha.text.toString()

        auth.signInWithEmailAndPassword(inputEmail,inputSenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, Principal::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Autenticação realizada com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Autenticação falhou!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}