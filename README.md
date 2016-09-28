Weather Forecast 10 days for Rateboard
===================

Task
-------------
Gehen Sie davon aus es gibt eine Liste von Ortschaften f�r welche man die 10-Tages Wetter-Vorhersagen abfragen m�chte. F�r jeden Tag sind folgende Daten relevant: H�chsttemperatur, Tiefsttemperatur, Conditions, Niederschlag, Schneefall. Diese Daten bekommt �ber diese API: 
http://api.wunderground.com/weather/api/d/docs?d=data/forecast10day. 

Ihre Aufgabe besteht darin 
* die n�tigen Datenstrukturen zu schaffen,
* eine �Service-Klasse� zu erstellen mit welcher die Vorhersagen f�r eine Liste von Ortschaften abgefragt werden kann.

Die Persistierung der Abfrageergebnisse ist nicht notwendig, aber wenn Sie hier eine tolle Idee haben die wir unbedingt sehen sollten dann finden wir das nat�rlich auch interessant. :)
Das ganze sollte in Java programmiert werden und  beliebige Bibliotheken d�rfen verwendendet werden.
Eine grafische Oberfl�che ist nicht notwendig. Ideal w�re wenn Sie einige Testf�lle in JUnit definieren um Ihre L�sung zu �berpr�fen.

API-Key: *****************
Da es sich hierbei allerdings um einen Entwickler-Key handelt sind nur 10 Abfragen/Minute bzw. 500 Abfragen/Tag erlaubt.

----------


Dokument
-------------

#### <i class="icon-file"></i> Anforderungsanalyse

Kunden m�chte eine System um Weather Forecast Informationen von API Server bekommen. Eine Stadt Liste sind vorhanden. Wir sollen die JSON Datei via Web zugreifen und parsen danach in Datenbank  persistieren(wenn m�glich).

#### <i class="icon-folder-open"></i> Technologie
Mit Framework Spring d�rfen man auf schnell Weise eine Produkt entwickeln, der k�nnte auch leicht mit eine Persistierung Framework wie Hibernate integrieren. Vergleich zu Strut1-2, Spring MVC ben�tigt weniger Configuration und besser Learning Curve.
MySQL ist eine kostenfrei und stabile relationale Datenbank
, es ist auch viele leicht als Produkt von Oracle. 


#### <i class="icon-pencil"></i> Produkt Beschreibung
Diese Applikation lauft auf Tomcat. Bei erst Besuch wurde die Kontroller eine initiate Datei von 16 St�dte erzeugen. Besucher konnte eine Stadt klicken. Wenn Kontroller diese Nachfrage bekommen, wurde es erst in Datenbank durchsuchen um zu schauen, ob eine Record schone vorhanden ist. Wenn ja, schicken Kontroller die Wetterinformation nach View um zu zeigen. Wenn nicht schicken Kontroller eine Request zu API Server. Kontroller soll erst die Response �berpr�fen ob es eine erfolgreich Ergebnis ist. Wenn Ja speichern die Ergebnis als JSON in Datenbank und liefert auch nach View Ebene.
Die Caching ist sehr wichtig weil die API Key hat nur 500 Abfragen/Tag erlaubt. 
Eine Task Klasse lauft in Hintergrund ein mal in jeder 60 Minuten. Die wurde die neuest Wetter Vorhersagen von vorhanden St�dte aktualisieren.
Zwei Data-bean sind definiert. City.class und Weather10Day. 
Und die zweite Klasse hat nur eine field "result" als haupt Column mit Datentype String f�r eine raw JSON.
Die Grund dass ich nicht mehr komplizierter Objekt definiert habe ist, es ist nicht so sinnvoll um solche Multi-Hierarchie Datenstruktur erst abbauen und in viele unterschiedlich Tabelle abspeichern. Es wurde eine sehr h�he Belastung zu Server bringen. Meine Meinung ist, diese Ergebnis soll direkt nach Front-End geliefert und mit Javascript geparsen werden. Dann belastet diese Aufgabe bei Millionen Anfrage nicht mehr auf unsere Server sondern  auf die Rechner von jeder Besucher :D


#### <i class="icon-refresh"></i>Zusammsungfassung
Ich bin nicht so sicher mit diese Teil "eine �Service-Klasse� zu erstellen mit welcher die Vorhersagen f�r eine Liste von Ortschaften abgefragt werden kann." und ich machte eine Default Liste. 
Bei bedarf kann ich auch eine Suchen-Funktion mit Auto Complete API aufbauen, damit Benutzer neue Stadt eingeben kann. Aber es ist noch nicht in diese Version. 
Wenn es etwas gibt, die ich nicht ganz richtig verstanden, bitte mir unbedingt eine Hinweise und Feedback geben. 
