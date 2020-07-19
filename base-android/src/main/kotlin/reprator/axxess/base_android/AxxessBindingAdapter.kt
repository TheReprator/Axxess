package reprator.axxess.base_android

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.api.load
import reprator.axxess.base_android.extensions.drawableFromViewContext

@BindingAdapter("visible")
fun visible(view: View, value: Boolean) {
    view.isVisible = value
}

@BindingAdapter(
    value = ["imageUrl",
        "placeHolder",
        "errorDrawable",
        "dimension"],
    requireAll = false
)
fun imageLoad(
    view: ImageView, imageUrl: String?,
    placeHolder: Drawable?, @DrawableRes errorDrawable: Int?,
    dimension: String?
) {
    if (imageUrl.isNullOrBlank()) {
        val drawable = placeHolder ?: view.drawableFromViewContext(
            errorDrawable ?: R.drawable.ic_circles_loader
        )
        view.load(drawable)
    } else {

        val url = if (dimension.isNullOrEmpty())
            imageUrl
        else
            "$imageUrl?$dimension"

        view.load(url) {
            val placeHolderDrawable =
                placeHolder ?: view.drawableFromViewContext(R.drawable.ic_circles_loader)
            placeholder(placeHolderDrawable)
        }
    }
}