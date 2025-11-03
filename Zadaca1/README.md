# RMAS-2025 
#🎓 Zadaća -  A 
---
## Uvod
Ovaj projekat implementira sistem za **organizaciju meetup događaja**.  
Cilj je upravljati listom učesnika koji se dijele na **Backend** i **Frontend** developere, s dodatnim informacijama specifičnim za svaki tip developera, te analizirati njihove programske jezike i iskustvo.  
Sistem koristi **Kotlin**, a u kodu su demonstrirane sljedeće tehnike:
- interfejsi (`Osoba`)
- nasljeđivanje (`Programer`, `BackendDeveloper`, `FrontendDeveloper`)
- kolekcije i funkcije višeg reda (`map`, `filter`, `flatMap`, `groupingBy`, `eachCount`)
- ručne i automatske agregacije (`prosjekIskustvaPoJeziku`, `brProgrameraPoJeziku`)
- provjere ispravnosti podataka (`require`)
  
---
## Interfejs Osoba
```kotlin
interface Osoba {
    fun puniIdentitet(): String
    fun oznZemljeP(): String
}
```
Ovaj interface sadrži dvije metode.
- puniIdentitet() vraća ime i prezime
- oznZemljeP() vraća oznaku zemlje porijekla

## Klasa Programer
```kotlin
open class Programer(val ime: String, val prezime: String, val brGodIskustva: Int, val oznakaZemlje: String,                     
    jezici: List<String>) : Osoba
	{ val skupJezika: List<String> = jezici.map { jezik -> jezik.lowercase() } 
    
    init {
        require(ime.isNotBlank()){"Ime ne može biti prazno."}
        require(prezime.isNotBlank()) { "Prezime ne može biti prazno" }
        require(brGodIskustva >= 0) { "Broj godina iskustva ne može biti negativan." }
   		require(jezici.isNotEmpty()) { "Lista jezika ne smije biti prazna." }
    }
    override fun puniIdentitet() = "$ime $prezime"
    override fun oznZemljeP() = oznakaZemlje
}
```
Programer je osnovna klasa koja implementira interface Osoba i sadrži osnovne osobine svih programera.
- Osobine : ime, prezime, godine iskustva, oznaka zemlje i skup jezika.
Init blok provjerava validnost podataka korištenjem "require" funkcije.

## Klase BackendDeveloper i FrontendDeveloper

```kotlin
class BackendDeveloper(ime: String, prezime: String, brGodIskustva: Int, oznakaZemlje: String, jezici: List<String>,
    val framework: String) : Programer(ime, prezime, brGodIskustva, oznakaZemlje, jezici)

class FrontendDeveloper(ime: String, prezime: String, brGodIskustva: Int, oznakaZemlje: String, jezici: List<String>,
    val framework: String) : Programer(ime, prezime, brGodIskustva, oznakaZemlje, jezici)
```
Ove klase nasljeđuju osnovnu klasu Programer i dodaju jedno novo svojstvo – framework. 
Parametri konstruktora:
- ime, prezime, brGodIskustva, oznakaZemlje, jezici → prenose se direktno u super klasu Programer.
- framework → novo polje koje opisuje koji framework backend developer koristi.
- Nasljeđivanje: : Programer(...) znači da BackendDeveloper nasljeđuje sve osobine i metode iz Programer.

Dakle, klase BackendDeveloper i FrontendDeveloper imaju sve što i Programer, plus svoju posebnu informaciju framework.
Sve metode iz Programer (npr. puniIdentitet() ili oznZemljeP()) mogu se koristiti i na backend i frontend developerima.

## Funkcija koja vraća podatke koliko programera koristi određeni programski jezik. 
``` kotlin 

fun brProgrameraPoJeziku(programeri: List<Programer>): Map<String, Int> {
    return programeri.flatMap{programer -> programer.skupJezika }.groupingBy{jezik -> jezik }.eachCount()
}

```
- Funkcija prima listu programera (programeri: List<Programer>).
- Vraća mapu (Map<String, Int>) gdje je:
- kljuc → naziv programskog jezika (npr. "Python", "Java")
- value → broj programera koji koriste taj jezik.
