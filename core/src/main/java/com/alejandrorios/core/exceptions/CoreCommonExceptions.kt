package com.alejandrorios.core.exceptions

import com.alejandrorios.core.constants.LocalStorageKey
import java.lang.NullPointerException

/**
 * Created by Alejandro Rios on 2019-10-23
 */
object CoreComponentNotInitializatedException : NullPointerException("Core component was not found")

class DataNoFoundOnLocalStorageException(key: LocalStorageKey) : NullPointerException("No data found for key $key")
