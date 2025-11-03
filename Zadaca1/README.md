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

puniIdentitet() vraća ime i prezime
oznZemljeP() vraća oznaku zemlje
