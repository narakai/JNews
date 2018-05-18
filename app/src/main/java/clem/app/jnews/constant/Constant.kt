package clem.app.jnews.constant

import android.widget.Toast

object Constant {
    /**
     * baseUrl
     */
    const val REQUEST_BASE_URL = "http://119.23.19.90:5000/"
    /**
     * Toast
     */
    @JvmField
    var showToast: Toast? = null
    /**
     * Share preferences name
     */
    const val SHARED_NAME = "_preferences"
    /**
     * Debug
     */
    const val INTERCEPTOR_ENABLE = false
}