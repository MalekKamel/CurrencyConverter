import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 28
    const val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val baseUrlProd = "http://data.fixer.io/api/"
    const val baseUrlDev = "http://data.fixer.io/api/"
}
