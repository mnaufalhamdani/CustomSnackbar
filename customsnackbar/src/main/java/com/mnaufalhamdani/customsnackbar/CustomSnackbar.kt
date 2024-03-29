package com.mnaufalhamdani.customsnackbar

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

enum class TypeMessage {
    SUCCESS, ERROR, INFO, WARNING
}

class CustomSnackbar(
    private val view: View,
    private val typeMessage: TypeMessage,
    private val message: String = "unknown Message",
    private val duration: Int = Snackbar.LENGTH_SHORT,
    private val paddingBottom: Int = 0,
) {
    private lateinit var snackbar: Snackbar

    @SuppressLint("RestrictedApi", "InflateParams")
    fun show(actionText: String? = null, action: ((Snackbar) -> Unit?)? = null) {
        dismiss()

        val drawableId = when(typeMessage) {
            TypeMessage.SUCCESS -> R.drawable.img_anim_success
            TypeMessage.INFO    -> R.drawable.img_anim_info
            TypeMessage.WARNING -> R.drawable.img_anim_warning
            TypeMessage.ERROR   -> R.drawable.img_anim_error
        }

        val colorId = when(typeMessage) {
            TypeMessage.SUCCESS -> R.color.green_A700
            TypeMessage.INFO    -> R.color.blue_A700
            TypeMessage.WARNING -> R.color.amber_A700
            TypeMessage.ERROR   -> R.color.red_A700
        }

        snackbar = Snackbar.make(view, "", duration)
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        snackbarLayout.setPadding(0, 0, 0, paddingBottom)

        val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM
        snackbar.view.layoutParams = params

        val snackView = LayoutInflater.from(view.context).inflate(R.layout.custom_snackbar_message, null)
        snackView.findViewById<MaterialCardView>(R.id.card).apply {
            strokeColor = ContextCompat.getColor(view.context, colorId)
        }
        snackView.findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            dismiss()
        }
        snackView.findViewById<TextView>(R.id.message).text = message

        snackView.findViewById<ImageView>(R.id.icon).load(drawableId, imageLoader = loaderOfImage(view.context))

        actionText?.let {
            snackView.findViewById<Button>(R.id.action).apply {
                visibility = View.VISIBLE
                text = it
                setOnClickListener { action?.invoke(snackbar) }
            }
        }

        snackbarLayout.addView(snackView, 0)
        snackbar.show()
    }

    fun dismiss() {
        if (::snackbar.isInitialized) {
            snackbar.dismiss()
        }
    }

}