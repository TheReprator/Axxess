package reprator.axxess.search.datasource.remote.modal

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "id",
    "title",
    "description",
    "datetime",
    "cover",
    "cover_width",
    "cover_height",
    "images"
)
class SearchEntity(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("datetime")
    val datetime: Long?,
    @JsonProperty("cover")
    val cover: String?,
    @JsonProperty("cover_width")
    val coverWidth: Int?,
    @JsonProperty("cover_height")
    val coverHeight: Int?,
    @JsonProperty("images")
    val images: List<ImageEntity> = emptyList()
)


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "id",
    "link",
    "comment_count",
    "favorite_count",
    "ups",
    "downs",
    "points",
    "score"
)
class ImageEntity(

    @JsonProperty("id")
    val id: String,
    @JsonProperty("link")
    val link: String?,
    @JsonProperty("comment_count")
    val commentCount: String?,
    @JsonProperty("favorite_count")
    val avoriteCount: String?,
    @JsonProperty("ups")
    val ups: String?,
    @JsonProperty("downs")
    val downs: String?,
    @JsonProperty("points")
    val points: String?,
    @JsonProperty("score")
    val score: String?
)