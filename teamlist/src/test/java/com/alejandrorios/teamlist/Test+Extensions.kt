package com.alejandrorios.teamlist

/**
 * Created by Alejandro Rios on 2019-10-24
 */
infix fun <T> Any.given(block: () -> T) = block.invoke()

infix fun <T> Any.whenever(block: () -> T) = block.invoke()

infix fun Any.then(block: () -> Unit) = block.invoke()
