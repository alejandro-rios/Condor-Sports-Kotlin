package com.alejandrorios.login

/**
 * Created by Alejandro Rios on 2019-10-28
 */
infix fun <T> Any.given(block: () -> T) = block.invoke()

infix fun <T> Any.whenever(block: () -> T) = block.invoke()

infix fun Any.then(block: () -> Unit) = block.invoke()
