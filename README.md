# Giełdowe Statystyki v.0.1
## Geneza aplikacji
Aplikacja służy do nadzoru i obserwacji wyników pracowników giełdy. Tworzy ona baze danych z pracownikami, wczytuje i 
zapisuje do ponownego odczytu. Na podstawie historii transakcji pobranej z platformy maklerskiej xStation5 tworzy historie
operacji dla danego pracownika i oblicza jego statystyki. Pozwala również na usuwanie pracowników z bazy oraz wyznaczenie najlepszego pracownika.
## Sposób użycia
Włączając aplikacje zostajemy zapytani o wybór jednej z kilku opcji. Wpisując wybraną opcję w konsole wykonujemy poszczególne
zadania związane z bazą danych. By zacząć ją tworzyć nalerzy wybrać opcje dodania pracownika, podać imię i nazwiko, a następnie
nazwe pliku .csv z historią operacji. Program jest przystosowany do odczytu plików z rozdzielnikiem **";"**. Po dodaniu pracownika
możemy zapisać bazę danych, wyświetlić jego statystyki, wyszukując go po nazwiku, znaleźć najlepszego pracownika pod wybranym kryterium
czy też usunąć go.

### Szczegóły

1. Pliki są generowane, odczytywane i zapisywane do folderu *files*
2. Gdy podjęto próbę wyświetlenia informacji o obiekcie, który nie isnieje, jesteśmy o tym powiadamiani, a program odsyła
do głównego menu lub prosi by podać komende jeszcze raz.
3. Przy wyświetlaniu statystyk mamy opcję wyświetlenia wszystkich lub z ostatniego miesiąca (dołączony plik testowy z historią
operacji nie posiada operacji z ostatniego miesiąca)
4. *"Składniki.txt"* - sformatowany zestaw argumentów, gotowy do przeklejenia do Prologa \
<sub>nazwy plików dopasowane do załączonego pliku RAW i jego zastosowania do stworzenia listy dań oraz składników
## Zalety
Aplikacja pozwala na sprawdzenie w jakich dziedzinach pracownik giełdy najlepiej się sprawdza, daje nam informacje o jego
przeciętnych zwyczajach, dzięki czemu osoba nadzorująca może w krótkim czasie stwierdzić jak nakierować pracownika w swojej
pracy, bez szerokiej analizy każdej jego operacji z osobna. Możliwość dołączenia wielu pracowników do bazy pozwala na 
porównanie ich ze sobą pod względem wybranych kryteriów.
