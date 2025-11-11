package org.thiha.thant.sin.foa

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform