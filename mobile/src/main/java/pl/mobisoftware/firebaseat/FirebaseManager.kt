package pl.mobisoftware.firebaseat

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by dpodolak on 10.10.2017.
 */
class FirebaseManager {

    val fDatabase: FirebaseDatabase

    init {
        fDatabase = FirebaseDatabase.getInstance("https://test-20858.firebaseio.com/")
    }

    fun listenCounterValue(valueListener: ValueEventListener){
        fDatabase.getReference("counter").addValueEventListener(valueListener)
    }


}