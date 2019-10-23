package com.alejandrorios.core.extensions

/**
 * Created by Alejandro Rios on 2019-10-23
 */
inline val Any?.isNotNull: Boolean get() = this != null

val Any?.isNull: Boolean get() = this == null
