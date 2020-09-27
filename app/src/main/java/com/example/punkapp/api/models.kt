package com.example.punkapp.api
import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("abv")
    val abv: Double?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("ingredients")
    val ingredients: Ingredients?,
    @SerializedName("method")
    val method: Method?,
)

data class Ingredients(
    @SerializedName("hops")
    val hops: List<Hop>?,
    @SerializedName("malt")
    val malt: List<Malt>?
)

data class Method(
    @SerializedName("fermentation")
    val fermentation: Fermentation?,
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp>?,
    @SerializedName("twist")
    val twist: String?
)

data class Hop(
    @SerializedName("add")
    val add: String?,
    @SerializedName("amount")
    val amount: Amount?,
    @SerializedName("attribute")
    val attribute: String?,
    @SerializedName("name")
    val name: String?
)

data class Malt(
    @SerializedName("amount")
    val amount: Amount?,
    @SerializedName("name")
    val name: String?
)

data class Amount(
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("value")
    val value: Double?
)

data class Fermentation(
    @SerializedName("temp")
    val temp: Temp?
)

data class MashTemp(
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("temp")
    val temp: Temp?
)

data class Temp(
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("value")
    val value: Int?
)




