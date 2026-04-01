[![License](https://img.shields.io/badge/License-Apache_2.0-orange)](https://opensource.org/licenses/Apache-2.0)
![GitHub Sprache](https://img.shields.io/badge/Language-Java_17-orange)

# isy-datetimesfdfsfsdfsf

Diese Bibliothek stellt Funktionen für die Anzeige, Eingabe und Speicherung von Datums- und Zeitwerten bereit.

## IsyFact

isy-datetime ist ein Teil der IsyFact-Standards, einem Open-Source-Projekt für Softwarelösungen, querschnittliche Anwendungen sowie Programmierbibliotheken und -vorlagen zur Erstellung von Geschäftsanwendungen.

## Features

Die Repräsentation eines Zeitraums im Sinne des Konzepts wird nicht durch die Java 8 Date and Time API bereitgestellt. Für diesen Zweck stellt isy-datetime die Klasse __Zeitraum__ bereit.

Ein Zeitraum kann aus zwei Datumsangaben, zwei Datums- und Zeitangaben oder nur aus Zeitangaben erstellt werden. Das angegebene Ende ist immer exklusiv und nicht Teil des Zeitraums. Ein Zeitraum, der nur aus Zeiten besteht, kann nicht länger als 24 Stunden sein, aber über einen Tageswechsel (z.B. 22:00 – 06:00) gehen. Bei Zeiträumen, die nur aus Datumsangaben bestehen, ist die Anfangszeit 00:00 Uhr des Anfangstages und die Endzeit 00:00 Uhr des Endtages.

Intern werden Anfang und Ende mit Angabe der Zeitzone gespeichert, um die Dauer bei Zeitumstellungen korrekt berechnen zu können. Wird bei der Erstellung keine Zeitzone angegeben, wird die Standard-Zeitzone der JVM verwendet.

Zur formatierten Ein- und Ausgabe stellt isy-datetime die Klassen __InFormat__ und __OutFormat__ bereit. Diese beinhalten vorkonfigurierte DateTimeFormatter für die im Konzept beschriebenen Formate. Die Verarbeitung in der ISO-8601-konformen Darstellung wird direkt durch die Klassen der Java 8 Date and Time API unterstützt.

## Installation

### Dokumentation
Die Dokumentation zu Konzept und Nutzungsvorgaben des IsyFact Moduls isy-datetime ist auf den Seiten  
[Konzept](docs/modules/ROOT/pages/konzept.adoc) und [Nutzungsvorgaben](docs/modules/ROOT/pages/nutzungsvorgaben.adoc) zu finden.

### Mithelfen
Wir freuen uns über Beiträge zur Weiterentwicklung von isy-datetime. 

### Lizenz

Die Software des Projekts ist unter der Apache License, Version 2.0 ([Apache-2.0](LICENSE)) lizenziert.
Die Dokumentation des Projekts ist unter der Namensnennung 4.0 International ([CC-BY-4.0](docs/LICENSE)) lizenziert.

### Kontakt

__Vielen Dank für die Verwendung von isy-datetime!__ <br> 
Bei Fragen oder Anmerkungen können Sie uns unter folgender E-Mail-Adresse kontaktieren: [isyfact@bva.bund.de](mailto:isyfact@bva.bund.de)

