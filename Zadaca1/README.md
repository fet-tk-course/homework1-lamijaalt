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

- puniIdentitet() vraća ime i prezime
- oznZemljeP() vraća oznaku zemlje

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
Programer je osnovna klasa koja implementira interface Osoba.
Polja: ime, prezime, godine iskustva, oznaka zemlje i skup jezika.
init blok provjerava validnost podataka korištenjem "require" funkcije.
