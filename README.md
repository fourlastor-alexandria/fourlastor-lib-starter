# Library
<!--
Change shields links
[![Library on Maven Central](https://img.shields.io/maven-central/v/io.github.fourlastor.gdx/library?label=library)](https://search.maven.org/artifact/io.github.fourlastor.gdx/library)
-->

Library description

## Setup

Library is hosted at Maven Central, you can get it by adding the following dependencies:

```kts
// Core library
implementation("io.github.fourlastor.gdx:library:$libraryVersion")
```

## GWT

Add the following to your GWT xml file:

```xml
<module rename-to="html">
  <inherits name="io.github.fourlastor.Library" />
</module>
```
