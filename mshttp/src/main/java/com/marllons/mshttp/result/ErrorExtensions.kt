package com.marllons.mshttp.result

import com.marllons.mshttp.domain.model.MSHttpError

const val GENERIC_ERROR_TITLE = "Error"
const val GENERIC_ERROR_MESSAGE = "Unknown Error"

fun ErrorResponse.toError() = MSHttpError(
    title = GENERIC_ERROR_TITLE,
    message = this.message?.ifEmpty { GENERIC_ERROR_MESSAGE } ?: GENERIC_ERROR_MESSAGE
)
