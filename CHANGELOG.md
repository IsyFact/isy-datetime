# 4.1.0
## Features
- `IFS-4710`: Kennzeichnung der Lizenz vereinheitlichen
- `IFS-4655`: Update von Maven Checkstyle Plugin auf Version 3.6.0
- `IFS-4531`: Update von Flatten Maven Plugin auf Version 1.7.1

## Dokumentation
- `IFS-4764`: Bereinigung technischer Schulden

## Migrationsleitfaden

### `IFS-4764`: Bereinigung technischer Schulden
Für das Release 4.1.0 muss die Online-Dokumentation angepasst werden.

#### Dokumentation
- Die Antora-Komponente von `isy-datetime` heißt jetzt `datetime` anstatt `isy-datetime-docs`.
- Das Konzept wird mittels `konzept.adoc` anstatt `konzept/master.adoc` referenziert.
- Die Nutzungsvorgaben werden mittels `nutzungsvorgaben.adoc` anstatt `nutzungsvorgaben/master.adoc` referenziert.

#### Playbook
- Der Parameter `start_path` der Content Source für `isy-datetime` muss auf `docs` anstatt `isy-datetime-doc` gesetzt werden.

#### Build
- `.github/workflows/antora-build.yml`: `sparse-checkout` muss auf `docs` anstatt `isy-datetime-doc` gesetzt werden.

### Dependency Upgrades
- Update com.github.spotbugs:spotbugs-maven-plugin von 4.8.5.0 auf 4.9.4.1
- org.apache.maven.plugins:maven-source-plugin from 3.2.1 to 3.3.1
- org.codehaus.mojo:tidy-maven-plugin from 1.3.0 to 1.4.0
- org.apache.commons:commons-text von 1.10.0 auf 1.14.0
- org.codehaus.mojo:flatten-maven-plugin von 1.7.1 auf 1.7.2
- org.apache.maven.plugins:maven-surefire-plugin von 3.2.5 auf 3.5.3
- org.apache.maven.plugins:maven-compiler-plugin von 3.11.0 auf 3.14.0
- org.apache.maven.plugins:maven-jar-plugin von 3.3.0 auf 3.4.2
- org.apache.maven.plugins:maven-enforcer-plugin von 3.5.0 auf 3.6.1
- org.apache.maven.plugins:maven-jar-plugin 3.3.0 auf 3.4.2
