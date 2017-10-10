package pl.mobisoftware.firebaseat

import android.app.Activity
import android.os.Bundle
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.GpioCallback
import com.google.android.things.pio.PeripheralManagerService
import timber.log.Timber
import java.util.*

class MainActivity : Activity() {
    private lateinit var mButton: Button

    val fManager by lazy { FirebaseManager() }

    var counter = 0

    var tempGpio: Gpio? = null

    val gpioCallback = object : GpioCallback(){
        override fun onGpioEdge(gpio: Gpio): Boolean {
            Timber.d("Is movement : ${gpio.value}")
            fManager.setMoveValue(gpio.value)
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)

        setInputGpio()

        Timer().schedule(object : TimerTask() {
            override fun run() {

                Timber.d("Increment counter ${++counter}")
                fManager.setCounterValue(counter)
            }
        }, 0, 1000)
    }

    fun setInputGpio(){
        val manager = PeripheralManagerService()
        tempGpio = manager.openGpio("BCM4")
        tempGpio?.setDirection(Gpio.DIRECTION_IN)

        tempGpio?.setActiveType(Gpio.ACTIVE_HIGH)
        tempGpio?.setEdgeTriggerType(Gpio.EDGE_BOTH)

        Timber.d("Register gpio callback")
        tempGpio?.registerGpioCallback(gpioCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        tempGpio?.unregisterGpioCallback(gpioCallback)
        tempGpio?.close()
    }


}
