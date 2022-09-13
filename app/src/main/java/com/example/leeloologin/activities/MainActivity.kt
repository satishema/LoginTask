package com.example.leeloologin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.leeloologin.R
import com.example.leeloologin.databinding.ActivityMainBinding
import com.example.leeloologin.model.LoginResponse
import com.example.leeloologin.retrofit.Leeloo
import com.example.leeloologin.room.AppDatabase
import com.example.leeloologin.utils.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var TAG = MainActivity::class.java

    private lateinit var binding: ActivityMainBinding

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    var userList : List<LoginResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loginUser()
    }

    private fun loginUser() {

        binding.loginBtn.setOnClickListener {

            if (validate()) {
                val userNameText = binding.usernameEmailEditText.text.toString().trim()
                val passwordText = binding.passwordEditText.text.toString().trim()

                getLogin(userNameText, passwordText)

            } else {
                Toast.makeText(this, "Incorrect Username or password", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun validate(): Boolean {

        when {
            binding.usernameEmailEditText.text.isNullOrEmpty() -> {
                binding.outlinedTextField.error = "This field can't be Blank"
                binding.usernameEmailEditText.requestFocus()
                return false
            }
            binding.passwordEditText.text.isNullOrEmpty() -> {
                binding.passwordLay.error = "This field can't be Blank"
                binding.passwordEditText.isFocusable = true
                return false
            }
            binding.passwordEditText.text!!.length < 6 -> {
                binding.passwordLay.error = "password must be at least 6 character"
                return false
            }
            else -> {
                return true
            }
        }

    }

    private fun getLogin(userNameText: String, passwordText: String) {
        binding.progressBar.visibility = View.VISIBLE
        val call: Call<LoginResponse> =
            Leeloo.getClient.loginByUserNameAndPassword(
                API_KEY,
                userNameText,
                passwordText
            )
        call.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>,
            ) {
                if (response.isSuccessful) {
                    val userData = response.body()
                    Log.d(TAG.toString(), "userData: $userData")
                    CoroutineScope(Dispatchers.IO).launch  { // Insert Data
                        if (userData != null) {
                            database.loginresponseDao().insert(userData)
                        }

                        // Get Data
                    }
                    if (response.body()!!.ok) {

                        binding.progressBar.visibility = View.GONE

                        val intent =
                            Intent(this@MainActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                        Toast.makeText(
                            this@MainActivity,
                            "The user name or password is incorrect",
                            Toast.LENGTH_SHORT
                        ).show()

                        binding.progressBar.visibility = View.GONE

                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: " + t.message)

                binding.progressBar.visibility = View.GONE

                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong, Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}