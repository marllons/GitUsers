package com.marllons.mshttp.result

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("documentation_url")
    val url: String? = null
) {

    fun isValid(): Boolean = message?.isNotEmpty() == true && url?.isNotEmpty() == true

}
