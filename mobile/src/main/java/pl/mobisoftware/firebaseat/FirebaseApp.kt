package pl.mobisoftware.firebaseat

import android.app.Application
import timber.log.Timber

/**
 * Created by dpodolak on 10.10.2017.
 */
class FirebaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}