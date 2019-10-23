package com.alejandrorios.core

/**
 * Created by Alejandro Rios on 2019-10-23
 */
infix fun <T> Any.given(block: () -> T) = block.invoke()

infix fun <T> Any.whenever(block: () -> T) = block.invoke()

infix fun <T> Any.then(block: () -> T) = block.invoke()
