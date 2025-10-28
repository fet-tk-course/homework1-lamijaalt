# Zadaća 1C 

## Cilj
- Provjeriti sposobnost rada sa **data klasama**, **kolekcijama**, **funkcijama višeg reda** i **obradu podataka** na način koji se koristi u Android aplikacijama.  
- Pripremiti studente za rad sa **API podacima**, **RecyclerView listama** i **ViewModel logikom** kroz slične obrasce obrade.

---

## Kontekst
Pripremate Kotlin logiku za buduću Android aplikaciju nalik Google Play Store-u.  
Svaka aplikacija u sistemu ima osnovne informacije — naziv, kategoriju, broj preuzimanja, prosječnu ocjenu i veličinu.  
Zadatak je da analizirate i filtrirate aplikacije pomoću Kotlin skupova i funkcija višeg reda.

---

## Obavezne komponente

### 1) **Data klasa – “Aplikacija”**
- Definisati **data klasu** koja predstavlja jednu aplikaciju.  
- Klasa treba sadržavati sljedeće atribute:
  - naziv aplikacije,  
  - kategoriju (npr. Productivity, Games, Education...),  
  - broj preuzimanja,  
  - prosječnu ocjenu (decimalna vrijednost),  
  - veličinu aplikacije u MB.  
- Svi atributi moraju imati realne vrijednosti (npr. broj preuzimanja > 0, veličina > 0).  
- Dodati osnovnu validaciju podataka prilikom inicijalizacije.

---

### 2) **Kolekcija testnih podataka**
- Kreirati **listu od najmanje 10 aplikacija** različitih kategorija.  
- Podaci mogu biti izmišljeni
- Liste treba sadržavati aplikacije sa različitim ocjenama, veličinama i brojem preuzimanja.

---

## Funkcionalnosti koje treba implementirati

### 3) **Filtriranje**
- Kreirati funkciju koja vraća sve aplikacije čija je ocjena veća od određene vrijednosti (npr. 4.5).  
- Implementirati drugu verziju koja koristi **lambda izraz i `filter()`**.

---

### 4) **Grupisanje po kategoriji**
- Kreirati funkciju koja grupiše aplikacije po kategorijama.  
- Rezultat treba biti mapa: kategorija → broj aplikacija u toj kategoriji.  
- Implementirati sa postojećim funkcijama **`groupingBy()`** ili **`associate()`** i bez istih.

---

### 5) **Sortiranje po broju preuzimanja**
- Implementirati funkciju koja sortira aplikacije po broju preuzimanja u opadajućem redoslijedu.  
- Implementirati sa postojećom funkcijom  **`sortedByDescending()`** i bez iste. 
- Rezultat treba prikazati u čitljivom formatu.

---

### 6) **Prosječna veličina po kategoriji**
- Kreirati funkciju koja računa prosječnu veličinu aplikacija unutar svake kategorije.  
- Može se implementirati pomoću **`fold()`**, **`aggregate()`** ili kombinacijom funkcija iz kolekcija.  
- Rezultat treba biti mapa: kategorija → prosječna veličina aplikacija.

---

### 7) **Pretraga po imenu**
- Omogućiti funkcionalnost pretrage aplikacije po nazivu.  
- Ako aplikacija postoji, prikazati njene podatke; ako ne, prikazati poruku da nije pronađena.  
- Koristiti funkciju **`find()`** ili **`firstOrNull()`**.

---

## Dodatni zadatak (opcionalno)
- Definisati klasu **Developer** koja ima:
  - ime i prezime developera,  
  - državu,  
  - listu aplikacija koje je razvio.  
- Implementirati funkcije koje:
  - pronalaze developera sa najvećim ukupnim brojem preuzimanja,  
  - izračunavaju prosječnu ocjenu svih njegovih aplikacija.  


---

## Ispis podataka
- Kreirati funkciju koja ispisuje sve aplikacije u formatu:  
  `App: MindSpace | Category: Productivity | Rating: 4.8 | Downloads: 1M+ | Size: 35MB`  
- Prikaz treba biti uredan i razumljiv, sa oznakama za svako polje.  
- Ispis koristiti za prikaz rezultata svake funkcije (filtriranje, sortiranje, grupisanje).

---

## Provjere ispravnosti
U glavnom dijelu programa dodati najmanje tri logičke provjere:
- da li filtriranje vraća samo aplikacije sa ocjenom iznad zadate granice,  
- da li grupisanje ispravno računa broj aplikacija po kategoriji,  
- da li pretraga po imenu vraća tačan rezultat.

---

## README (obavezno)
README datoteka treba sadržavati:
- opis strukture projekta i svrhe svake funkcije,  
- objašnjenje kako bi se ove funkcionalnosti koristile u Android aplikaciji (npr. za prikaz i filtriranje u RecyclerView-u),  
- razloge izbora određenih funkcija (`map`, `filter`, `fold`...),  
- napomenu o korištenju AI alata (ako je bilo).

---

## Pravila o AI i provjeri znanja
- Dozvoljena je upotreba AI alata samo za objašnjenja, testiranje i optimizaciju koda, **ne za generisanje čitavih funkcija**.  
- U README datoteci i kodu mora biti jasno naznačeno gdje je AI korišten.  
- Tokom provjere **AI alati nisu dozvoljeni**.  
- Tokom odbrane student će dobiti **izmijenjen zadatak** i mora ga implementirati samostalno.  
- Zadaća **ne nosi bodove** bez uspješne odbrane.  
- Boduje se **razumijevanje i sposobnost prilagođavanja zadatka**, a ne samo postojanje koda.

---

## Pravila 
- Kod mora biti jasan, konzistentan i logički organizovan po datotekama (modeli, funkcije, prikaz).  
- Svi brojevi moraju imati validne vrijednosti (npr. ocjene u rasponu 0–5).  
- Koristiti **nepromjenjive kolekcije** gdje je to moguće.  
- Podaci moraju biti hardkodirani u kodu — **nema ručnog unosa** tokom izvršavanja programa.

---
