
# Locoronando-Backend
Die Repository implementiert das Locoronando-Backend - basierend auf Spring.

## Bauen
### Maven
Du kannst mit [Maven](https://maven.apache.org) die aktuelle Version des Backends selber bauen:
1. Clonen: `git clone https://github.com/Locoronando/Locoronando-Backend.git`
2. Wechsle in den Ordner `Locoronando-Backend`.
3. Installieren: `mvn clean install`
Bemerkung: Es wird eventuell (einmalig) eine Vielzahl an Abhängigkeiten heruntergeladen. (Dauer von ~ 10 Minuten)

### Docker
Falls du Docker auf deinem Server oder lokal installiert hast, kannst du folgende Befehle ausführen:
1. Clonen: `git clone https://github.com/Locoronando/Locoronando-Backend.git`
2. Wechsle in den Ordner `Locoronando-Backend`.
3. Image bauen: `docker image build -t backend:1.0.0`
4. Container starten: `docker container run --publish 127.0.0.1:8080:8080 --detach --name backend backend:1.0.0`

## Entwicklung
Aktuell (Prototyp) verfolgen wir einen klassischen Monolithen, der über eine REST-Schnittstelle angesprochen werden kann.
Um vom Backend gezielt das Frontend anzusprechen benutzen wir Web-Sockets basierend auf dem Protokoll STOMP.

## Kontakt
### Mitmachen
Falls du Interesse an dem Projekt bekommen hast, lese dir zunächst unseren [CODE_OF_CONDUCT](https://github.com/Locoronando/Locoronando-Backend/blob/master/CODE_OF_CONDUCT.md) durch.
Anschließend kannst du ein ein neues [Issue](https://github.com/Locoronando/Locoronando-Backend/issues/new) öffnen und dich somit an uns wenden.

Falls du nur kleine Änderungen vornehmen möchtest, schaue nach [offene Issues](https://github.com/Locoronando/Locoronando-Backend/issues?q=is%3Aopen+is%3Aissue).

### Dokumentation
Für weitere Informationen zu Locoronando schaue bei unserer [Dokumentation](https://github.com/Locoronando/Documentation) vorbei!

### Sonstiges
Wenn du Fehler, Verbesserungsvorschläge oder sonstige Anliegen hast, öffne gerne ein [Issue](https://github.com/Locoronando/Locoronando-Backend/issues/new)  und wir können in Kontakt treten.
