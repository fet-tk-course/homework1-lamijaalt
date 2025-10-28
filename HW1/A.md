# Zadaća 1A 

## Cilj
- Provjeriti razumijevanje **interfejsa**, **klasa**, **nasljeđivanja**, rada s **kolekcijama** i **funkcijama višeg reda**.  
- Pripremiti **model podataka (data model)** koji se kasnije može direktno koristiti u Android aplikacijama.

---

## Kontekst
Potrebno je da kreirate sistem koji će podržati organizaciju meetup događaja. Svaki učesnik ima lične podatke, godine iskustva, zemlju i skup programskih jezika koje koristi.  
Učesnici se dijele na dvije grupe: **backend** i **frontend** developere, a svaki tip ima dodatne specifične podatke (npr. alat ili framework koji koriste).

---

## Obavezne komponente

### 1) **Interfejs – “Osoba”**
- Definisati **interfejs** koji predstavlja osobu.  
- Interfejs treba deklarisati najmanje dvije metode:
  - jedna metoda treba vraćati puni identitet osobe (npr. ime i prezime zajedno),
  - druga metoda treba vraćati oznaku zemlje porijekla ili boravka.  
- Cilj interfejsa je da definiše osnovno ponašanje koje svaka osoba mora imati, bez obzira na to kojoj klasi pripada.  
- Kasnije će se taj interfejs implementirati u klasi koja predstavlja programera.

---

### 2) **Klasa – “Programer” (osnovni model)**
- Kreirati **osnovnu klasu** koja implementira prethodno definisani interfejs.  
- Klasa treba predstavljati **zajedničke osobine svih programera**, i sadržavati:
  - ime i prezime,  
  - broj godina iskustva (pozitivan broj),  
  - oznaku zemlje (npr. “BA”, “DE”),  
  - skup programskih jezika koje koristi.  
- Klasa mora uključivati osnovnu **validaciju podataka** (npr. ime ne može biti prazno, broj godina ne može biti negativan, lista jezika ne smije biti prazna).  
- Potrebno je osigurati da svi nazivi jezika budu obrađeni na isti način (npr. pretvoreni u mala slova prije poređenja).  
- Ova klasa je osnova iz koje će se izvoditi specifični tipovi developera.

---

### 3) **Izvedene klase – specifični tipovi developera**
- Kreirati **dva tipa klasa** koje nasljeđuju osnovnu klasu programera:
  1. **BackendDeveloper** – dodatno ima informaciju o glavnom backend frameworku (npr. Spring Boot, Ktor).  
  2. **FrontendDeveloper** – dodatno ima informaciju o glavnom frontend frameworku (npr. React, Vue.js).  
- Svaka od izvedenih klasa treba proširiti osnovno ponašanje, ali i dalje poštovati strukturu osnovne klase i interfejsa.  
- Potrebno je osigurati da se može lako prepoznati kojoj vrsti programera (backend ili frontend) pojedinac pripada.  
- Cilj ovih klasa je da student razumije **koncept nasljeđivanja** i korištenje **polimorfizma** kroz zajednički interfejs i osnovnu klasu.

---

## Operacije nad podacima

### 4) **Analiza jezika koje koriste programeri**
- Implementirati operaciju koja iz liste programera vraća podatke o tome koliko programera koristi određeni programski jezik.  
- Rezultat treba biti mapa gdje je ključ naziv jezika, a vrijednost broj developera koji ga koriste.  
- Potrebno je uraditi dvije verzije:
  1. korištenjem ugrađene funkcije za grupisanje (`groupingBy`, `eachCount`, i sl.),  
  2. ručno, pomoću prolaska kroz listu i brojanja elemenata.  
- Važno: u oba pristupa svi nazivi jezika moraju biti ujednačeni (npr. “kotlin”, ne “Kotlin” i “kOTLIN”).

---

### 5) **Prosječno iskustvo po jeziku**
- Implementirati operaciju koja računa prosječno radno iskustvo (u godinama) za sve programere koji koriste određeni jezik.  
- Rezultat treba biti mapa gdje je ključ jezik, a vrijednost decimalni broj (prosjek).  
- Potrebno je napraviti dva rješenja – jedno pomoću funkcija za grupisanje, a drugo bez njih.  
- Voditi računa o tačnom računanju prosjeka (djeljenje cijelih brojeva može dati pogrešan rezultat ako se ne pretvori u decimalni).

---

### 6) **Filtriranje po frameworku**
- Implementirati operaciju koja iz liste programera izdvaja one koji koriste određeni framework.  
- Treba omogućiti filtriranje i backend i frontend developera.  
- Cilj ove operacije je da se provjeri razumijevanje rada s **filter funkcijama** i **provjerom tipa** (npr. pomoću `is` operatora).

---

## Prikaz rezultata

### 7) **Ispis informacija o programeru**
- Kreirati operaciju koja ispisuje:
  - ime i prezime,
  - da li je backend ili frontend programer,
  - listu jezika koje koristi,
  - framework koji koristi (zavisno od tipa developera).  
- Prikaz treba biti čitljiv, jasan i dosljedan (isti redoslijed podataka za sve programere).  
- Na primjer:  
  “Amila — Backend developer — jezici: Kotlin, Java — framework: Spring Boot”

---

### 8) **Glavni tok programa**
- U glavnom dijelu programa kreirati **listu od najmanje pet programera** koji pokrivaju obje uloge i koriste više različitih jezika.  
- Pozvati sve funkcije redom:
  - prikaz svih programera,  
  - prebrojavanje jezika (oba pristupa),  
  - prosječno iskustvo po jeziku (oba pristupa),  
  - filtriranje po frameworku.  
- Očekuje se da program daje ispravan i smislen izlaz na konzolu.

---

## Pravila 
- Podaci i proračuni trebaju biti logički odvojeni (posebne funkcije ili klase).  
- Koristiti **nepromjenjive strukture (val, immutable collections)** gdje god je moguće.  
- Voditi računa o ujednačenom formatu ispisa i imenovanja.  
- Dodati bar tri provjere ispravnosti (npr. jednostavne “check” provjere u glavnom programu).

---

## README (obavezno)
README datoteka mora sadržavati:
- kratak opis strukture projekta i odnosa između interfejsa i klasa,  
- objašnjenje kako pokrenuti program,  
- uporednu analizu dva pristupa (sa i bez funkcije za grupisanje),  
- informaciju o eventualnoj upotrebi AI alata (koji alat i za koji dio).  

---

## Pravila o AI i provjeri znanja
- Dozvoljena je upotreba AI alata samo za objašnjenje grešaka, testiranje i prijedloge poboljšanja, uz **obavezno navođenje u README-u i komentarima**.  
- Tokom provjere **nije dozvoljena upotreba AI alata**.  
- Tokom provjere student će dobiti **izmijenjenu verziju zadatka**  i mora je **implementirati samostalno**.  
- Zadaća **ne nosi bodove** bez uspješne odbrane.  
- Boduje se isključivo **znanje i razumijevanje**, a ne samo postojanje koda.

