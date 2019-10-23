package com.alejandrorios.core

/**
 * Created by Alejandro Rios on 2019-10-22
 */
import android.app.Application
import com.alejandrorios.core.di.*
import com.alejandrorios.core.exceptions.CoreComponentNotInitializatedException
import com.alejandrorios.core.extensions.isNull

class CoreApplication : Application(), CoreComponentProvider {

    private var coreComponent: CoreComponent? = null

    override fun getCoreComponent(): CoreComponent {
        if (coreComponent.isNull) {
            coreComponent = DaggerCoreComponent.builder()
                .coreModule(CoreModule(this))
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule(this))
                .build()
        }

        return coreComponent ?: throw CoreComponentNotInitializatedException
    }
}
