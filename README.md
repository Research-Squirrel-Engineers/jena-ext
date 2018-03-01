# jena-ext

An extension library for Apache Jena

[![build](https://api.travis-ci.org/linkedgeodesy/jena-ext.svg?branch=master)](https://travis-ci.org/linkedgeodesy/jena-ext) [![version](https://img.shields.io/badge/version-1.0--SNAPSHOT-green.svg)](#)  [![java](https://img.shields.io/badge/jdk-1.8-red.svg)](#)  [![maven](https://img.shields.io/badge/maven-3.5.0-orange.svg)](#) [![output](https://img.shields.io/badge/output-jar-red.svg)](#)  [![docs](https://img.shields.io/badge/apidoc-v0.1-lightgrey.svg)](https://linkedgeodesy.github.io/jena-ext/)  [![license](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/linkedgeodesy/jena-ext/blob/master/LICENSE)

## Prerequisites

The code is developed using and tested with:

* maven 3.5.0
* Netbeans 8.2
* Apache Tomcat 8.0.27.0
* JDK 1.8

## Maven

The `jena-ext` library is build using `maven` as JAR-file.

For details have a look at [pom.xml](https://github.com/linkedgeodesy/jena-ext/blob/master/pom.xml).

[Download](http://maven.apache.org/download.cgi) and  [install](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) `maven` and [run](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) it.

## Setup

Download and install `maven`.

Run git clone `https://github.com/linkedgeodesy/jena-ext.git` to create a local copy of this repository.

Run `mvn install` to install all required dependencies.

Run `mvn clean install site` for cleaning, building, testing and generating the documentation files.

Run the jar-file using `mvn exec:java`.

In order to run the Main Class in Netbeans use `Run / Debug`.

Running `mvn test` will run the unit tests with `JUnit`.

## Documentation

Look at [GitHub Pages](https://linkedgeodesy.github.io/jena-ext/) for the latest developer documentation like `maven` and `javadoc`.

## Developer Hints

Look at [Gist](https://gist.github.com/florianthiery/0f8c0c015555939c96eb13428bbf1cd4) hints for `Configurations for JAVA projects using Maven`.

For loading your prefixes please add the [`prefixes.csv`](https://github.com/linkedgeodesy/jena-ext/blob/master/src/main/resources/prefixes.csv) into your [`resources`](https://github.com/linkedgeodesy/jena-ext/tree/master/src/main/resources) folder and modify it.

## Dependency Information

*latest stabile version: v0.1*

**Apache Maven** *via jitpack.io*

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.linkedgeodesy</groupId>
  <artifactId>jena-ext</artifactId>
  <version>${version}</version>
</dependency>
```

**gradle** *via jitpack.io*

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.linkedgeodesy:jena-ext:${version}'
}
```

**sbt** *via jitpack.io*

```
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.linkedgeodesy" % "jena-ext" % "${version}"
```

**leiningen** *via jitpack.io*

```
:repositories [["jitpack" "https://jitpack.io"]]   
:dependencies [[com.github.linkedgeodesy/jena-ext "${version}"]]
```

## Developers and License Holder

Florian Thiery<sup>1</sup>, i3mainz<sup>2</sup>, RGZM<sup>3</sup>

<sup>1</sup> Florian Thiery M.Sc., [`ORCID:0000-0002-3246-3531`](http://orcid.org/0000-0002-3246-3531)

<sup>2</sup> i3mainz - Institute for Spatial Information and Surveying Technology at School of Technology, Hochschule Mainz
University of Applied Sciences ([`Website`](http://i3mainz.hs-mainz.de/))

<sup>3</sup> Römisch-Germanisches Zentralmuseum Mainz, Leibniz-Forschungsinstitut für Archäologie
University of Applied Sciences ([`Website`](http://rgzm.de/))
