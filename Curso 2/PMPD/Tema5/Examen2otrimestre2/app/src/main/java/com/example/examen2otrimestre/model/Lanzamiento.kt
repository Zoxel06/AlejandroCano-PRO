package com.example.examen2otrimestre.model

import java.io.Serializable

data class Lanzamiento (
    val links: Links? = null,
    val details: String? = null,
    val name: String? = null
) : Serializable

data class Links (
    val patch: Patch? = null,
)

data class Patch (
    val small: String? = null,
)
