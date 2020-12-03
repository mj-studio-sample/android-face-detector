package happy.mjstudio.facedetector

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import happy.mjstudio.facedetector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (it.toList().all { it.second }) {
            navigateDetector()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkPermissions()) {
            navigateDetector()
        } else {
            requestPermission.launch(Permissions.values().map { it.permissionName }.toTypedArray())
        }
    }

    private fun checkPermissions(): Boolean {
        val grantedStates = Permissions.values().map { permission ->
            val result = PermissionChecker.checkSelfPermission(
                this, permission.permissionName
            )
            result == PermissionChecker.PERMISSION_GRANTED
        }


        return grantedStates.all { it }
    }

    private fun navigateDetector() {
        startActivity(Intent(this, DetectorActivity::class.java))
        finish()
    }

    enum class Permissions(val permissionName: String) {
        STORAGE(WRITE_EXTERNAL_STORAGE), CAMERA(Manifest.permission.CAMERA)
    }
}