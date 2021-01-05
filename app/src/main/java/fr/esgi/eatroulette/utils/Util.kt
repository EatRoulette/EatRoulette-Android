package fr.esgi.eatroulette.utils

import android.util.Log
import java.io.IOException

object Util {

    fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            Log.e("toto", e.printStackTrace().toString())
        } catch (e: InterruptedException) {
            Log.e("toto", e.printStackTrace().toString())
        }
        return false
    }

}