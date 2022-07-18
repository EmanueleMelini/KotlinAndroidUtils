import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import it.emanuelemelini.kotlinadroidutils.CONN_ERROR
import it.emanuelemelini.kotlinadroidutils.R
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.abs
import kotlin.math.log10

/**
 * An extension function that show a [Toast] with a given [String] as the message
 * @receiver[Context] - The context of an [Activity]
 * @param[str] A [String] to be displayed as the message
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun Context.simpleToast(str: String) {
    try {
        this as Activity
        runOnUiThread {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        }
    } catch (ex: ClassCastException) {
        throw ex
    }
}

/**
 * An extension function that show a [Toast] with a given [StringRes] id as the message
 * @receiver[Context] - The context of an [Activity]
 * @param[res] A [StringRes] id of a string to be displayed as the message
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun Context.simpleToast(@StringRes res: Int) {
    try {
        this as Activity
        runOnUiThread {
            Toast.makeText(this, res, Toast.LENGTH_LONG).show()
        }
    } catch (ex: ClassCastException) {
        throw ex
    }
}

/**
 * An extension function that show an error [Toast] with a connection error message ([R.string.conn_error])
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun Context.connErrorToast() {
    try {
        this as Activity
        runOnUiThread {
            Toast.makeText(this, CONN_ERROR, Toast.LENGTH_LONG).show()
        }
    } catch (ex: ClassCastException) {
        throw ex
    }
}

/**
 * An extension function that invoke the recevier function after some delay
 * @receiver A function that returns nothing
 * @param[delay] Time to invoke the function after in millis
 */
fun (() -> Unit).doAfter(delay: Long) {
    Timer().schedule(timerTask { this@doAfter() }, delay)
}

/**
 * An extension function that sets the [View] visibility to [View.VISIBLE]
 * @receiver[View] - A view
 */
fun View.setVisible() {
    visibility = View.VISIBLE
}

/**
 * An extension function that sets the [View] visibility to [View.GONE]
 * @receiver[View] - A view
 */
fun View.setGone() {
    visibility = View.GONE
}

/**
 * An extension function that toggles the [View] visibility between [View.VISIBLE] and [View.GONE]
 * @receiver[View] - A View
 */
fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

/**
 * An etension function which return the length of an [Int]
 * @receiver[Int] - An Integer
 * @return An [Int] containint the length of the receiver
 */
fun Int.length() = when (this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

/**
 * An extension function which starts an activity with transaction, without finish and extras
 * @param[ac] The [Activity] to start
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.go(ac: Class<T>) {
    startActivity(Intent(this, ac))
}

/**
 * An extension function which starts an activity with transaction and extras, without finish
 * @param[ac] The [Activity] to start
 * @param[extras] A [Map] containing the name and the extra to send
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goWithExtras(ac: Class<T>, extras: Map<String, Any>) {
    startActivity(Intent(this, ac)
        .apply {
            extras.forEach { (name, ex) ->
                putAny(name, ex)
            }
        })
}

/**
 * An extension function which starts an activity whitout transations, finish and extras
 * @param[ac] The [Activity] to start
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goNoTransaction(ac: Class<T>) {
    try {
        this as Activity
        startActivity(Intent(this, ac))
        overridePendingTransition(0, 0)
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        throw ex
    }
}

/**
 * An extension function which starts an activity whitout transations and finish and with extras
 * @param[ac] The [Activity] to start
 * @param[extras] A [Map] containing the name and the extra to send
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goNoTransactionWithExtras(ac: Class<T>, extras: Map<String, Any>) {
    try {
        this as Activity
        startActivity(Intent(this, ac)
            .apply {
                extras.forEach { (name, ex) ->
                    putAny(name, ex)
                }
            })
        overridePendingTransition(0, 0)
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        throw ex
    }
}

/**
 * An extension function which starts an activity with transations and finish and without extras
 * @param[ac] The [Activity] to start
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goFinish(ac: Class<T>) {
    try {
        this as Activity
        startActivity(Intent(this, ac))
        finish()
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        throw ex
    }
}

/**
 * An extension function which starts an activity with transations, finish and extras
 * @param[ac] The [Activity] to start
 * @param[extras] A [Map] containing the name and the extra to send
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goFinishWithExtras(ac: Class<T>, extras: Map<String, Any>) {
    try {
        this as Activity
        startActivity(Intent(this, ac)
            .apply {
                extras.forEach { (name, ex) ->
                    putAny(name, ex)
                }
            })
        finish()
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        throw ex
    }
}

/**
 * An extension function which starts an activity with finish and without transations and extras
 * @param[ac] The [Activity] to start
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goFinishNoTransaction(ac: Class<T>) {
    try {
        this as Activity
        startActivity(Intent(this, ac))
        overridePendingTransition(0, 0)
        finish()
        overridePendingTransition(0, 0)
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        throw ex
    }
}

/**
 * An extension function which starts an activity with finish and extras and without transations
 * @param[ac] The [Activity] to start
 * @param[extras] A [Map] containing the name and the extra to send
 * @receiver[Context] - The context of an [Activity]
 * @throws[ClassCastException] If the receiver [Context] cannot be casted to [Activity]
 */
fun <T : Activity> Context.goFinishNoTransactionWithExtras(ac: Class<T>, extras: Map<String, Any>) {
    try {
        this as Activity
        startActivity(Intent(this, ac)
            .apply {
                extras.forEach { (name, ex) ->
                    putAny(name, ex)
                }
            })
        overridePendingTransition(0, 0)
        finish()
        overridePendingTransition(0, 0)
    } catch (ex: ClassCastException) {
        ex.printStackTrace()
        throw ex
    }
}

/**
 * A function that shows a [MaterialDialog] with given title, message, buttons text and actions
 * @param[c] A Context
 * @param[title] A [StringRes] id of a string to be displayed as the dialog title
 * @param[message] A [StringRes] id of a string to be displayed as the dialog message
 * @param[positive] A [StringRes] id of a string to be displayed as the positive button text, if null the button will not be displayed
 * @param[negative] A [StringRes] id of a string to be displayed as the negative button text, if null the button will not be displayed
 * @param[posAction] A function to be invoked on positive button click
 * @param[negAction] A function to be invoked on negative button click
 */
fun Context.dialog(@StringRes title: Int, @StringRes message: Int, @StringRes positive: Int?, @StringRes negative: Int?, posAction: (MaterialDialog) -> Unit, negAction: (MaterialDialog) -> Unit) {
    MaterialDialog(this).show {
        title(res = title)
        message(res = message).apply {
            if (positive != null) positiveButton(res = positive) {
                posAction(this)
            }
            if (negative != null) negativeButton(res = negative) {
                negAction(this)
            }
        }
    }
}

/**
 * An extension function which adds an [Any] extra to an [Intent] and throws an [IllegalArgumentException] if the extra type isn't allowed
 * @param[name] The name of the extra
 * @param[ex] The extra
 * @receiver[Intent] - The intent you put the extra into
 * @throws[IllegalArgumentException] If the extra type isn't allowed
 */
fun Intent.putAny(name: String, ex: Any) {
    when (ex) {
        is Bundle -> putExtra(name, ex)
        is Parcelable -> putExtra(name, ex)
        is Boolean -> putExtra(name, ex)
        is BooleanArray -> putExtra(name, ex)
        is Byte -> putExtra(name, ex)
        is ByteArray -> putExtra(name, ex)
        is String -> putExtra(name, ex)
        is Char -> putExtra(name, ex)
        is CharArray -> putExtra(name, ex)
        is CharSequence -> putExtra(name, ex)
        is Double -> putExtra(name, ex)
        is DoubleArray -> putExtra(name, ex)
        is Float -> putExtra(name, ex)
        is FloatArray -> putExtra(name, ex)
        is Int -> putExtra(name, ex)
        is IntArray -> putExtra(name, ex)
        is Long -> putExtra(name, ex)
        is LongArray -> putExtra(name, ex)
        is Short -> putExtra(name, ex)
        is ShortArray -> putExtra(name, ex)
        is java.io.Serializable -> putExtra(name, ex)
        else -> throw java.lang.IllegalArgumentException("Illegal extra type")
    }
}

fun AppCompatActivity.takePicture(picture: (Bitmap?) -> Unit): ActivityResultLauncher<Intent>? {
    return try {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK)
                picture(it.data?.extras?.get("data") as Bitmap)
        }
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        picture(null)
        null
    }
}