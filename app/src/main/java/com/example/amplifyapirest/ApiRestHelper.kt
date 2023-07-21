package com.example.amplifyapirest

import android.util.Log
import com.amplifyframework.api.rest.RestOptions
import com.amplifyframework.core.Amplify
import java.text.SimpleDateFormat
import java.util.Date

class ApiRestHelper {

    fun postTodoItem(
        name:String,
        date:String = SimpleDateFormat("dd-MM-yyyy HH:mm").format(Date()),
        callback: (isCompleted: Boolean) -> Unit,
    ) {
        val options = RestOptions.builder()
            .addPath("/todo")
            .addBody("{\"name\":\"$name\", \"date\":\"$date\"}".toByteArray())
            .build()

        Amplify.API.post(options,
            { response ->
                Log.i("ApiRestHelper", "POST succeeded: ${String(response.data.rawBytes)}")
                callback(true)
            },
            {
                Log.e("ApiRestHelper", "POST failed", it)
                callback(false)
            }
        )
    }

    fun getTodo(callback: (isCompleted: Boolean) -> Unit) {
        val request = RestOptions.builder()
            .addPath("/todo")
            .addQueryParameters(mapOf(
                "param1" to "test",
                "param2" to "test2"
            ))
            .build()

        Amplify.API.get(request,
            { response ->
                Log.i("ApiRestHelper", "GET succeeded: ${String(response.data.rawBytes)}")
                callback(true)},
            {
                Log.e("ApiRestHelper", "GET failed.", it)
                callback(false)
            }
        )
    }

    fun putTodo(
        name:String,
        date:String = SimpleDateFormat("dd-MM-yyyy HH:mm").format(Date()),
        callback: (isCompleted: Boolean) -> Unit) {
        val request = RestOptions.builder()
            .addPath("/todo/1")
            .addBody("{\"name\":\"$name\", \"date\":\"$date\"}".toByteArray())
            .build()

        Amplify.API.put(request,
            { response ->
                Log.i("ApiRestHelper", "PUT succeeded: ${String(response.data.rawBytes)}")
                callback(true)},
            {   Log.e("ApiRestHelper", "PUT failed", it)
                callback(false)}
        )
    }
    fun deleteTodo(callback: (isCompleted: Boolean) -> Unit){
        val options = RestOptions.builder()
            .addPath("/todo/1")
            .build()

        Amplify.API.delete(options,
            { response ->
                Log.i("ApiRestHelper", "DELETE succeeded: ${String(response.data.rawBytes)}")
                callback(true)},
            {   Log.e("ApiRestHelper", "DELETE failed.", it)
                callback(false)}
        )
    }
}