package com.example.amplifyapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var btnPost: Button
    private lateinit var btnGet: Button
    private lateinit var btnPut: Button
    private lateinit var btnDelete: Button

    private val api by lazy {
        ApiRestHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edt_name)
        btnPost = findViewById(R.id.btn_post)
        btnGet = findViewById(R.id.btn_get)
        btnPut = findViewById(R.id.btn_put)
        btnDelete = findViewById(R.id.btn_delete)

        btnPost.setOnClickListener {
            api.postTodoItem(
                name = edtName.text.toString(),
                callback = ::handleFinish
            )
        }

        btnGet.setOnClickListener {
            api.getTodo(::handleFinish)
        }

        btnPut.setOnClickListener {
            api.putTodo(
                name = edtName.text.toString(),
                callback = ::handleFinish)
        }

        btnDelete.setOnClickListener {
            api.deleteTodo(::handleFinish)
        }
    }

    private fun handleFinish(isCompleted: Boolean) {
        runOnUiThread {
            val message = if (isCompleted) "Operation completed!" else "An error happens"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}