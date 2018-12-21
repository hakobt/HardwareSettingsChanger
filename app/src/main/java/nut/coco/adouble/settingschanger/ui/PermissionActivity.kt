package nut.coco.adouble.settingschanger.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast


/**
 * Created by Hakob Tovmasyan on 12/21/18
 * Package nut.coco.adouble.settingschanger.ui
 */
class PermissionActivity : AppCompatActivity() {

    private val settingsRequestCode = 4242
    private val locationRequestCode = 3131

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationRequestCode)
            if (hasPermission()) {
                routeToMain()
            } else {
                requestPermission()
            }
        } else {
            routeToMain()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasPermission(): Boolean {
        return Settings.System.canWrite(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestPermission() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivityForResult(intent, settingsRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == settingsRequestCode) {
            // This intent does not return Context.RESULT_OK so we have to check again.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasPermission()) {
                Toast.makeText(this, "This app does not work without required permissions", Toast.LENGTH_LONG).show()
                finish()
            }
            else {
                routeToMain()
            }
        }
    }

    private fun routeToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}