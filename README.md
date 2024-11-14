[![License](https://img.shields.io/badge/License-Apache_2.0-orange)](https://opensource.org/licenses/Apache-2.0)
![GitHub Sprache](https://img.shields.io/badge/Language-Java_17-orange)

# isy-datetime

Diese Bibliothek stellt Funktionen für die Anzeige, Eingabe und Speicherung von Datums- und Zeitwerten bereit.

## IsyFact

isy-datetime ist ein Teil der IsyFact-Standards, eines Open-Source-Projekts für Softwarelösungen, querschnittliche Anwendungen, sowie Programmierbibliotheken und -vorlagen zur Erstellung von Geschäftsanwendungen.

## Features

Die Repräsentation eines Zeitraums im Sinne des Konzepts wird nicht durch die Java 8 Date and Time API bereitgestellt. Für diesen Zweck stellt isy-datetime die Klasse __Zeitraum__ bereit.

Ein Zeitraum kann aus zwei Datumsangaben, zwei Datums- und Zeitangaben oder nur aus Zeitangaben erstellt werden. Das angegebene Ende ist immer exklusive und nicht Teil des Zeitraums. Ein Zeitraum, der nur aus Zeiten besteht, kann nicht länger als 24 Stunden sein, aber über einen Tageswechsel (22:00 – 06:00) gehen. Bei Zeiträumen die nur aus Datumsangaben bestehen, ist die Anfangszeit 00:00 Uhr des Anfangstages und die Endzeit 00:00 Uhr des Endtages.

Intern werden Anfang und Ende mit Angabe der Zeitzone gespeichert, um die Dauer bei Zeitumstellungen korrekt berechnen zu können. Wird bei der Erstellung keine Zeitzone angegeben, wird die Standard-Zeitzone der JVM verwendet.

Zur formatierten Ein- und Ausgabe stellt isy-datetime die Klassen __InFormat__ und __OutFormat__ bereit. Diese beinhalten vorkonfigurierte DateTimeFormatter für die im Konzept beschriebene Formate. Die Verarbeitung in der ISO-8601-konformen Darstellung wird direkt durch die Klassen der Java 8 Date and Time API unterstützt.

## Installation

### Dokumentation
Die Dokumentation zu Konzept und Nutzungsvorgaben des IsyFact Moduls isy-datetime ist auf den Seiten  
[Konzept](docs/modules/ROOT/pages/konzept/master.adoc) und [Nutzungsvorgaben](docs/modules/ROOT/pages/nutzungsvorgaben/master.adoc) zu finden.

### Mithelfen
Wir freuen uns über Beiträge zur Weiterentwicklung von isy-datetime. 

### Lizenz

Dieses Projekt ist unter der Apache-2-Lizenz lizenziert. Weitere Informationen findest du in der [Lizenzdatei](license/LICENSE).

### Kontakt

__Vielen Dank für die Verwendung von isy-datetime!__

