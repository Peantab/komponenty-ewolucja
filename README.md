# Komponenty-Ewolucja

Projekt zespołowy na przedmiot **Techniki komponentowe**.

## Wiki projektu

Znajduje się [tutaj](https://github.com/Peantab/komponenty-ewolucja/wiki).

## Uruchamianie
* Zbuduj projekt umieszczony w folderze `logger`: będąc wewnątrz, wywołaj `mvn package`.
* Umieść powstały plik `.jar` w podkatalogu `bin` katalogu głównego repozytorium, pod nazwą `Logger.jar` (tak, jak jest wymieniony w pliku `bin/configuration/config.ini`).
* Będąc w katalogu `bin` wywołaj `java -jar org.ecpse.osgi_3.13.300.v20190218-1622.jar` *Obecnie nic nie wywołuje udostępnianych funkcji, stąd nic nie zostanie wyświetlone*
* Można też włączyć konsolę **OSGi** `java -jar org.ecpse.osgi_3.13.300.v20190218-1622.jar -console` i za pomocą komendy `ss` przekonać się, że komponent `Logger` jest uruchomiony.

## Wymagania

Moduł loggera do działania wymaga dostępu do bazy danych *ElasticSearch* uruchomionej lokalnie na porcie 9200. Testowana wersja to `7.0.1`