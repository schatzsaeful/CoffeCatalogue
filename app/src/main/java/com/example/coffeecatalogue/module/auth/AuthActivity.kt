package com.example.coffeecatalogue.module.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.coffeecatalogue.R
import com.example.coffeecatalogue.base.BaseActivity
import com.example.coffeecatalogue.databinding.ActivityAuthBinding
import com.example.coffeecatalogue.module.coffee.CoffeeActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(getViewBinder()) {
            tvSubmit.setOnClickListener {
                if (etPin.text.toString().isEmpty()) {
                    Toast.makeText(this@AuthActivity, "Pin Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                } else if (etPin.text.toString() != "2022") {
                    Toast.makeText(this@AuthActivity, "Pin Yang Anda Masukan Salah", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this@AuthActivity, CoffeeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_auth
}