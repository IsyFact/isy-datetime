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
- org.apache.che.maven.plugins:maven-gpg-plugin von 3.0.1 auf 3.2.8
- org.apache.maven.plugins:maven-jar-plugin von 3.3.0 auf 3.4.2

