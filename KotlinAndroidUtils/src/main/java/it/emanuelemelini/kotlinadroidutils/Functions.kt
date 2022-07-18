package it.emanuelemelini.kotlinadroidutils

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.annotation.IntDef
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

/**
 * An annotation that simulate the [View] @Visibility annotation that can't be accessed.
 *
 * It takes only [View.VISIBLE], [View.INVISIBLE] and [View.GONE]
 */
@IntDef(value = [View.VISIBLE, View.INVISIBLE, View.GONE])
@Retention(AnnotationRetention.SOURCE)
annotation class Visibility {}


/**
 * A function that sets the given views visibility to [View.VISIBLE]
 * @param[views] An [Array] or varargs of [View]
 */
fun setVisible(vararg views: View) {
    for(v in views) {
        v.visibility = View.VISIBLE
    }
}

/**
 * A function that sets the given views visibility to [View.GONE]
 * @param[views] An [Array] or varargs of [View]
 */
fun setGone(vararg views: View) {
    for(v in views) {
        v.visibility = View.GONE
    }
}

/**
 * A function that sets the given views visibility to the given visibility
 * @param[vis] A [Visibility] [Int] that can be [View.VISIBLE], [View.INVISIBLE] or [View.GONE]
 * @param[views] An [Array] or varargs of [View]
 */
fun setVisibility(@Visibility vis: Int, vararg views: View) {
    for(v in views) {
        v.visibility = vis
    }
}

/**
 * A function which convert the given [Int] as dp value to pixel value
 * @param[dp] The dp value to convert
 * @return The [Int] pixel value
 */
fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * A function which convert the given [Int] as pixel value to dp value
 * @param[px] The pixel value to convert
 * @return The [Int] dp value
 */
fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * A function which checks if the device has the Google Play Services
 * @return true if the device has the services, false if not
 */
fun checkService(ctx: Context): Boolean {
    val gApi = GoogleApiAvailability.getInstance()
    val resultCode = gApi.isGooglePlayServicesAvailable(ctx)
    return resultCode == ConnectionResult.SUCCESS
}

/**
 * A function which returns a random [String] of a given lenght
 * @param[length] The [Int] value of the random string
 * @return The randomised [String]
 */
fun getRandomString(length: Int): String {
    val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz1234567890"
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}