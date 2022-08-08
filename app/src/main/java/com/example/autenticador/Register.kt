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

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            performCadastrar()
        }
    }

    private fun performCadastrar() {
        val edtEmail: EditText = findViewById(R.id.edtLogin)
        val edtSenha: EditText = findViewById(R.id.edtSenha)

        if (edtEmail.text.isEmpty() || edtSenha.text.isEmpty()){
            Toast.makeText(this, "Por favor, preencher os campos para efetuar o login", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = edtEmail.text.toString()
        val inputSenha = edtSenha.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputSenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Cadastro falhou!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}