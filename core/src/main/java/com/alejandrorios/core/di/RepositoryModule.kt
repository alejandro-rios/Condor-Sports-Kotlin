package com.alejandrorios.core.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.alejandrorios.core.repositories.LocalStorageRepository
import com.alejandrorios.core.repositories.LocalStorageRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alejandro Rios on 2019-10-23
 */
@Module
class RepositoryModule (private val context: Context) {

    @Provides
    fun provideLocalStorageRepository(): LocalStorageRepository {
        return LocalStorageRepositoryImpl(context) {
            PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}
