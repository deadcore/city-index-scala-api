// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "io.igu"

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

// License of your choice
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://igu.io"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/deadcore/city-index-scala-api"),
    "scm:git@github.com:deadcore/city-index-scala-api.git"
  )
)
