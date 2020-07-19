package reprator.axxess.util.retrofit.wrapperModal

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("data")
class EnvelopeResponse<out T>(val data: T
)



/*
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "data",
    "success",
    "status"
)
class EnvelopeResponse<T>(
    @JsonProperty("data")
    val data: T,
    @JsonProperty("success")
    val success: Boolean = false,
    @JsonProperty("status")
    val status: Int
)*/
