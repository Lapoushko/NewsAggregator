package com.lapoushko.network.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("category")
data class CategoryDto(

    val domain: String,

    @XmlValue
    val value: String
)