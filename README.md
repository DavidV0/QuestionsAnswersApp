
# QuestionsAnswersApp

QuestionsAnswersApp ist eine Java-Kommandozeilenanwendung, die es Benutzern ermöglicht, Fragen und Antworten zu verwalten. Die Anwendung ermöglicht das Hinzufügen von Fragen und ihren entsprechenden Antworten sowie das Abrufen von Antworten zu gespeicherten Fragen.

## Inhaltsverzeichnis

- [Features](#features)
- [Installation](#installation)
- [Verwendung](#verwendung)
- [Beispiele](#beispiele)
- [Technische Details](#technische-details)
- [Beitragende](#beitragende)
- [Lizenz](#lizenz)

## Features

- **Fragen hinzufügen**: Benutzer können Fragen mit einer oder mehreren Antworten hinzufügen.
- **Antworten abrufen**: Benutzer können gespeicherte Antworten auf eine bestimmte Frage abrufen.
- **Standardantwort**: Wenn eine Frage nicht in der Datenbank gefunden wird, gibt das Programm eine Standardantwort zurück.

## Installation

1. **Klonen Sie das Repository**:

   ```bash
   git clone https://github.com/DavidV0/QuestionsAnswersApp.git
   ```

2. **Navigieren Sie zum Projektverzeichnis**:

   ```bash
   cd QuestionsAnswersApp
   ```

3. **Kompilieren Sie das Projekt**:

   Verwenden Sie einen Java-Compiler, um die Datei zu kompilieren:

   ```bash
   javac -d bin src/com/david_velickovic/QuestionsAnswersApp.java
   ```

4. **Führen Sie die Anwendung aus**:

   ```bash
   java -cp bin com.david_velickovic.QuestionsAnswersApp
   ```

## Verwendung

1. **Starten Sie das Programm**, um das Hauptmenü zu sehen.
2. **Option 1**: Fragen Sie eine gespeicherte Frage ab.
3. **Option 2**: Fügen Sie eine neue Frage und ihre Antworten hinzu.
4. **Option 3**: Beenden Sie das Programm.

## Beispiele

### Beispiel: Frage hinzufügen

```
Bitte wählen Sie eine Option:
1. Frage stellen
2. Frage und Antworten hinzufügen
3. Beenden
```

**Eingabe**: `2`

**Eingabeaufforderung**:
```
Geben Sie Ihre Frage gefolgt von Antworten im Format ein: <question>? "<answer1>" "<answer2>" ...
```

**Beispiel**:
```
Was ist deine Lieblingsfarbe? "Blau" "Grün"
```

### Beispiel: Frage abrufen

```
Bitte wählen Sie eine Option:
1. Frage stellen
2. Frage und Antworten hinzufügen
3. Beenden
```

**Eingabe**: `1`

**Eingabeaufforderung**:
```
Geben Sie Ihre Frage ein (muss mit einem '?' enden):
```

**Beispiel**:
```
Was ist deine Lieblingsfarbe?
```

**Ausgabe**:
```
Blau
Grün
```

## Technische Details

- **Programmiersprache**: Java
- **Datenstruktur**: `HashMap<String, List<String>>` wird verwendet, um Fragen und ihre Antworten zu speichern.
- **Fehlerbehandlung**: Das Programm prüft, ob die Eingaben gültig sind und bietet Fehlermeldungen bei ungültigen Eingaben.

## Beitragende

- **David Velickovic** - Entwicklung und Wartung

## Lizenz

Dieses Projekt ist lizenziert unter der MIT-Lizenz - siehe die [LICENSE](LICENSE) Datei für Details.
