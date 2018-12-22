package nut.coco.adouble.settingschanger.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.squareup.moshi.Moshi
import nut.coco.adouble.settingschanger.data.response.RemoteSettingsData
import nut.coco.adouble.settingschanger.data.response.RemoteSettingsDataJsonAdapter
import okhttp3.*
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class RemoteDataSource {

    private val initialUrl = "https://interviews-dcoc.s3.amazonaws.com/android/initial.json"
    private val url1 = "https://s3.amazonaws.com/interviews-dcoc/android/devices-1.json"
    private val url2 = "https://s3.amazonaws.com/interviews-dcoc/android/devices-2.json"
    private val url3 = "https://s3.amazonaws.com/interviews-dcoc/android/devices-3.json"

    private val handler = Handler(Looper.getMainLooper())

    // callback to notify listener about new received data.
    var callback: ((RemoteSettingsData) -> Unit)? = null

    private val requestScheduler = Executors.newSingleThreadScheduledExecutor()

    private val settingsUrls = listOf(url1, url2, url3)

    private val moshi = Moshi.Builder().build()

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    fun start() {
        requestSettings(initialUrl)
        startScheduler()
    }

    private fun requestSettings(url: String) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string() ?: return
                    parse(responseBody)
                    response.close()
                }
            }
        })
    }

    private fun startScheduler() {
        requestScheduler.scheduleAtFixedRate({
            // Generate a random number and divide with 3
            // and take remainder so we get a value from 0 to 2
            val randomIndex = Random.nextInt(0, Integer.MAX_VALUE) % 3
            Log.d("Random", randomIndex.toString())
            requestSettings(settingsUrls[randomIndex])
        }, 15, 15, TimeUnit.SECONDS)
    }

    private fun parse(body: String) {
        val remoteSettingsData = RemoteSettingsDataJsonAdapter(moshi).fromJson(body)
        // return to main thread
        handler.post {
            callback?.invoke(remoteSettingsData ?: return@post)
        }
    }
}
