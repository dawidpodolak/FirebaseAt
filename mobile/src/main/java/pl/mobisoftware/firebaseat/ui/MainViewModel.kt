package pl.mobisoftware.firebaseat.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import pl.mobisoftware.firebaseat.FirebaseManager
import timber.log.Timber

/**
 * Created by dpodolak on 10.10.2017.
 */
class MainViewModel(val firebaseManager: FirebaseManager): ViewModel() {

    val counterObservable = ObservableField<String>()
    val moveObservable = ObservableField<String>()

    init {
        firebaseManager.listenCounterValue(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e(p0?.message)
            }

            override fun onDataChange(snap: DataSnapshot?) {
                val value = snap?.value
                Timber.d("New value $value")
                counterObservable.set(value.toString())
            }
        })

        firebaseManager.listenMoveValue(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError?) {
                Timber.e(error?.message)
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                moveObservable.set(snapshot?.value.toString())
            }
        })
    }
}