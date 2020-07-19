package reprator.axxess.util.retrofit.converter

import okhttp3.ResponseBody
import reprator.axxess.util.retrofit.FailureException
import reprator.axxess.util.retrofit.wrapperModal.EnvelopeResponse
import retrofit2.Converter

internal class EnvelopeResponseConverter<T>(private val delegate: Converter<ResponseBody, EnvelopeResponse<T>>) :
    Converter<ResponseBody, T> {

    @Throws(FailureException::class)
    override fun convert(responseBody: ResponseBody): T? {
        val envelope = delegate.convert(responseBody)
        return envelope?.data
    }
}