@Suppress(
    // known false positive: https://youtrack.jetbrains.com/issue/KTIJ-19369
    "DSL_SCOPE_VIOLATION"
)
plugins {
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.spotless)
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat()
    }
}

dependencies {
    api(libs.gdx.core)
}

java {
    withJavadocJar()
    withSourcesJar()
}

val libraryArtifact = "library"
val libraryName = "Library"
val libraryDescription = "Library description"
val libraryUrl = "https://www.github.com/fourlastor-alexandria/library"
val licenseUrl = "$libraryUrl/blob/main/LICENSE"
val licenseName = "MIT License"
val developerId = "fourlastor"
val developerName = "Daniele Conti"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = libraryArtifact
            from(components["java"])
            pom {
                name.set(libraryName)
                description.set(libraryDescription)
                url.set(libraryUrl)
                licenses {
                    license {
                        name.set(licenseName)
                        url.set(licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(developerId)
                        name.set(developerName)
                    }
                }
                scm {
                    connection.set("scm:git:$libraryUrl.git")
                    developerConnection.set("scm:git:$libraryUrl.git")
                    url.set(libraryUrl)
                }
            }
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}
