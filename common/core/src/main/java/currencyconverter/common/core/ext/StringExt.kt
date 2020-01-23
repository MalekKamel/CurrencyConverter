package currencyconverter.common.core.ext

import android.content.Context


fun String.withTitle(title: String): String {
    return "$title: $this"
}

fun String?.withTitleRes(title: Int, context: Context): String {
    return "${context.getString(title)}: $this"
}

fun String.withSuffix(suffix: String): String {
    return "$this $suffix"
}

fun String.withSuffixRes(suffix: Int, context: Context): String {
    return "$this ${context.getString(suffix)}"
}

fun String.withPrefix(suffix: String): String {
    return "$suffix $this"
}

fun String.withPrefixRes(suffix: Int, context: Context): String {
    return "${context.getString(suffix)} $this"
}