package com.alejandrorios.core.contract

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Alejandro Rios on 2019-10-24
 */
abstract class BaseActivity : AppCompatActivity(){

    abstract fun prepareActivityBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prepareActivityBuilder()
    }
}
