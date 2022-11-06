package z.nova.usecase5koin.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import z.nova.usecase5koin.di.appModule
import z.nova.usecase5koin.di.dataModule
import z.nova.usecase5koin.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }

    }

}