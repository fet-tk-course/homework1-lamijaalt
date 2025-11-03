# RMAS-2025 
# 🎓 Zadaća -  A 
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

## Funkcija brProgrameraPoJeziku. 
``` kotlin 

fun brProgrameraPoJeziku(programeri: List<Programer>): Map<String, Int> {
    return programeri.flatMap{programer -> programer.skupJezika }.groupingBy{jezik -> jezik }.eachCount()
}

```
Funkcija brProgrameraPoJeziku služi da iz liste programera izvuče informacije o tome koliko programera koristi svaki programski jezik. Funkcija prima listu objekata tipa Programer i vraća mapu čiji su ključevi nazivi jezika, a vrijednosti broj programera koji poznaju taj jezik.

Unutar funkcije prvo se koristi metoda flatMap, koja prolazi kroz sve programere i iz svakog uzima njegov skup jezika. Rezultat je jedna velika lista svih programskih jezika koje koriste programeri. Nakon toga primjenjuje se funkcija groupingBy, koja organizuje sve elemente liste u grupe po jeziku, tako da svi ponovljeni jezici budu u istoj grupi. Na kraju, funkcija eachCount prolazi kroz te grupe i broji koliko elemenata pripada svakoj grupi, tj. koliko programera koristi svaki jezik.

## Funkcija brProgrameraPoJeziku, druga verzija.
``` kotlin

fun brProgrameraPoJezikuRucno(programeri: List<Programer>): Map<String, Int> {
    val brPoJeziku = mutableMapOf<String, Int>()
    for (programer in programeri) {
        for (jezik in programer.skupJezika) {
            brPoJeziku[jezik] = (brPoJeziku[jezik] ?: 0) + 1
        }
    }
    return brPoJeziku
}

```
Funkcija brProgrameraPoJezikuRucno radi istu stvar kao prethodna verzija, ali je implementirana “ručno”, koristeći petlje umjesto ugrađenih funkcija za grupisanje. Funkcija prima listu programera i vraća mapu u kojoj su ključevi nazivi programskih jezika, a vrijednosti predstavljaju koliko programera koristi taj jezik.

Unutar funkcije kreira se prazna mutabilna mapa koja će čuvati broj programera po jeziku. Zatim se prolazi kroz svaki programer u listi, a unutar tog prolaza kroz sve jezike koje taj programer poznaje. Za svaki jezik provjerava se da li već postoji u mapi – ako postoji, njegova vrijednost se uvećava za jedan, a ako ne postoji, postavlja se na jedan. Na kraju funkcija vraća mapu sa svim jezicima i odgovarajućim brojem programera koji ih koriste.

## Funkcije prosjekIskustvaPoJeziku

``` kotlin
fun prosjekIskustvaPoJeziku(programeri: List<Programer>): Map<String, Double> {
    return programeri.flatMap { programer -> programer.skupJezika.map {jezik -> jezik to programer.brGodIskustva} }
        .groupBy(
            { par -> par.first },
            { par -> par.second }
        ).mapValues { (_, lista) -> lista.average() }
}

fun prosjekIskustvaPoJezikuRucno(programeri: List<Programer>): Map<String, Double> {
    val suma = mutableMapOf<String, Int>()
    val broj = mutableMapOf<String, Int>()

    for (programer in programeri) {
        for (jezik in programer.skupJezika) {
            suma[jezik] = (suma[jezik] ?: 0) + programer.brGodIskustva
            broj[jezik] = (broj[jezik] ?: 0) + 1
        }
    }

    val prosjek = mutableMapOf<String, Double>()
    for (jezik in suma.keys) {
        prosjek[jezik] = suma[jezik]!!.toDouble() / broj[jezik]!!
    }
    return prosjek
}

``` 

Ove dvije funkcije služe za izračunavanje prosječnog radnog iskustva programera po programskim jezicima, ali su implementirane na dva različita načina. Prva verzija koristi ugrađene funkcije za grupisanje. Funkcija prvo “raspakira” sve jezike koje programeri koriste i poveže ih sa njihovim godinama iskustva, kreirajući parove jezik-godine. Zatim se ti parovi grupišu po jeziku, a za svaku grupu se izračunava prosjek godina iskustva. Rezultat je mapa u kojoj je svaki ključ naziv jezika, a vrijednost predstavlja prosječno iskustvo svih programera koji taj jezik koriste.

Druga verzija radi isto, ali bez funkcija. Tu se kreiraju dvije mape: jedna za zbir godina iskustva po jeziku, a druga za broj programera koji poznaju taj jezik. Petljama se prolazi kroz sve programere i njihove jezike, sabiraju se godine iskustva i broje programeri. Na kraju se za svaki jezik računa prosjek dijeljenjem ukupnog iskustva sa brojem programera, i rezultat se smješta u mapu koja se vraća iz funkcije. 


## Funkcija filterpoFrameworku
``` kotlin
fun filterPoFrameworku(programeri: List<Programer>, fw: String): List<Programer> {
    return programeri.filter { programer ->
        (programer is BackendDeveloper && programer.framework == fw) ||
        (programer is FrontendDeveloper && programer.framework == fw)
    }
}
``` 
Ova funkcija služi za izdvajanje programera koji koriste određeni framework. Funkcija prima listu svih programera i naziv frameworka koji želimo filtrirati. Unutar funkcije se koristi ugrađena metoda filter, koja prolazi kroz sve elemente liste i provjerava uslove definisane u lambda izrazu.
Lambda izraz provjerava da li je programer instanca klase BackendDeveloper ili FrontendDeveloper i da li mu je framework polje jednako proslijeđenom nazivu frameworka. Samo oni programeri koji zadovoljavaju ove uslove ostaju u rezultujućoj listi.

## Funkcija za ispis programera 

``` kotlin

fun prikaziProgramere(programeri: List<Programer>) {
    for (programer in programeri) {
        val tip = if (programer is BackendDeveloper)
            "Backend developer"
        else if (programer is FrontendDeveloper)
            "Frontend developer"
        else
            "Programer"
        
        val framework = if (programer is BackendDeveloper)
            programer.framework
        else if (programer is FrontendDeveloper)
            programer.framework
       	else
        ""
        println("${programer.puniIdentitet()} - $tip — jezici:${programer.skupJezika.joinToString(", ")} — framework:$framework")
    }
}

```
Ova funkcija služi za pregled i ispis podataka o programerima. Funkcija prima listu programera i prolazi kroz svaki element liste koristeći petlju for.

- Za svakog programera se prvo određuje njegov tip: ako je instanca klase BackendDeveloper, tip se označava kao "Backend developer", a ako je instanca klase FrontendDeveloper, tip se označava kao "Frontend developer". Ako programer nije ni backend ni frontend, tip se jednostavno označava kao "Programer".
- Zatim se provjerava da li programer ima pripadajući framework (samo backend i frontend developeri ga imaju). Ako ima, vrijednost se sprema u varijablu framework, a ako ne, ostaje prazna.
- Na kraju, println ispisuje sve relevantne informacije za programera.

## MAIN 
``` kotlin

fun main() {

    val programeri = listOf(
        BackendDeveloper("Lamija", "Altumbabić", 1, "BA", listOf("Python","Java"), "Spring Boot"),
        FrontendDeveloper("Mujo", "Alić", 2, "DE", listOf("TypeScript", "Kotlin"), "Angular"),
        BackendDeveloper("Lejla", "Šarić", 5, "BA", listOf("Java","C#"), "Node.js"),
        FrontendDeveloper("Lamija", "Ahmetašević", 2, "BA", listOf("HTML", "CSS"), "React"),
        BackendDeveloper("Hasan", "Avdić", 6, "BA", listOf("TypeScript", "Python"), "Spring Boot")
    )

    println("\n - Programeri : ")
    prikaziProgramere(programeri)

    println("\n - Broj programera po jeziku (prva metoda) :")
    println(brProgrameraPoJeziku(programeri))
    println("\n - Broj programera po jeziku (rucno) :")
    println(brProgrameraPoJezikuRucno(programeri))

    println("\n - Prosječno iskustvo po jeziku (prva metoda) : ")
    println(prosjekIskustvaPoJeziku(programeri))
    println("\n - Prosječno iskustvo po jeziku (rucno) : ")
    println(prosjekIskustvaPoJezikuRucno(programeri))

   val frameworks = listOf("Spring Boot", "React", "Angular", "Node.js")
   
	for (fw in frameworks) {
    println("\n - Filtriranje po frameworku '$fw' : ")
    val filtrirani = filterPoFrameworku(programeri, fw)
        prikaziProgramere(filtrirani)
    
	}
    
    println("\n - Provjere ispravnosti  : ")
	programeri.forEach { p -> try {
       require(p.ime.isNotBlank() && p.prezime.isNotBlank()) { "Programer mora imati ime i prezime! (${p.ime} ${p.prezime})" }
       require(p.brGodIskustva >= 0) { "Programer ${p.puniIdentitet()} ima negativno iskustvo!" }
       require(p.skupJezika.isNotEmpty()) { "Programer ${p.puniIdentitet()} mora poznavati bar jedan programski jezik!" }
    } catch (e: IllegalArgumentException) {
        println("Greška: ${e.message}")
    	}
	}
    
}

``` 
Ovaj dio koda predstavlja glavni program i služi za demonstraciju rada cijelog sistema koji smo prethodno definisali.
Prvo se kreira lista programeri koja sadrži nekoliko instanci klasa BackendDeveloper i FrontendDeveloper. Svaki programer ima uneseno ime, prezime, broj godina iskustva, oznaku zemlje, listu poznatih programskih jezika i framework koji koristi.
Zatim se poziva funkcija prikaziProgramere koja ispisuje sve programere sa svim relevantnim informacijama na pregledan način.
Nakon toga, glavni program prikazuje statistike:
- Broj programera po jeziku
- Prosječno iskustvo po jeziku
- Filtriranje po frameworku
Na kraju, glavni program provodi provjeru ispravnosti podataka koristeći require izraze. Ova provjera osigurava da svaki programer ima ispravno uneseno ime, prezime, ne negativan broj godina iskustva i barem jedan programski jezik. Ukoliko neki podatak nije validan, ispisuje se odgovarajuća greška.

## Upotreba AI alata
AI alat je korišten za razumjevanje funkcije require(). Također, korišten je za formiranje finalne provjere ispravnosti. Upotreba AI alata je bila neophodna s obzirom da provjere validnosti nismo prelazili na lab. vježbama. 

## Konzolni ispis 
```text
 - Programeri : 
Lamija Altumbabić - Backend developer — jezici:python, java — framework:Spring Boot
Mujo Alić - Frontend developer — jezici:typescript, kotlin — framework:Angular
Lejla Šarić - Backend developer — jezici:java, c# — framework:Node.js
Lamija Ahmetašević - Frontend developer — jezici:html, css — framework:React
Hasan Avdić - Backend developer — jezici:typescript, python — framework:Spring Boot

 - Broj programera po jeziku (prva metoda) :
{python=2, java=2, typescript=2, kotlin=1, c#=1, html=1, css=1}

 - Broj programera po jeziku (rucno) :
{python=2, java=2, typescript=2, kotlin=1, c#=1, html=1, css=1}

 - Prosječno iskustvo po jeziku (prva metoda) : 
{python=3.5, java=3.0, typescript=4.0, kotlin=2.0, c#=5.0, html=2.0, css=2.0}

 - Prosječno iskustvo po jeziku (rucno) : 
{python=3.5, java=3.0, typescript=4.0, kotlin=2.0, c#=5.0, html=2.0, css=2.0}

 - Filtriranje po frameworku 'Spring Boot' : 
Lamija Altumbabić - Backend developer — jezici:python, java — framework:Spring Boot
Hasan Avdić - Backend developer — jezici:typescript, python — framework:Spring Boot

 - Filtriranje po frameworku 'React' : 
Lamija Ahmetašević - Frontend developer — jezici:html, css — framework:React

 - Filtriranje po frameworku 'Angular' : 
Mujo Alić - Frontend developer — jezici:typescript, kotlin — framework:Angular

 - Filtriranje po frameworku 'Node.js' : 
Lejla Šarić - Backend developer — jezici:java, c# — framework:Node.js

 - Provjere ispravnosti  : 



