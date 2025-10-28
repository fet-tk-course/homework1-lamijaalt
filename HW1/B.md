# Zadaća 1B 

## Cilj
- Provjeriti razumijevanje rada sa **interfejsima**, **klasama**, **nasljeđivanjem** i **funkcionalnim operacijama** (`fold`, `reduce`, `aggregate`).  
- Osigurati da student zna koristiti napredne Kotlin funkcije za grupisanje i obradu podataka.  
- Ova zadaća je most između osnovnih Kotlin pojmova i kasnijeg rada sa **API podacima i lokalnom obradom rezultata** u Android aplikacijama.

---

## Kontekst
Imate kolekciju podataka o različitim tipovima inženjera koji rade u jednoj kompaniji.  
Svaki inženjer ima svoje osnovne osobine (ime, titulu, iskustvo, ekspertize), ali i dodatne podatke specifične za njegovu oblast.  
Zadatak je analizirati podatke koristeći napredne funkcije za obradu i prikazati sumarne rezultate.

---

## Obavezne komponente

### 1) **Interfejs – “Osoba”**
- Definisati **interfejs** koji propisuje osnovno ponašanje svih osoba u sistemu.  
- Interfejs mora deklarisati najmanje dvije metode:
  - metoda koja vraća identitet osobe (ime i prezime),  
  - metoda koja vraća njenu profesionalnu titulu.  
- Ovaj interfejs će implementirati sve klase inženjera.

---

### 2) **Osnovna klasa – “Inženjer”**
- Kreirati **osnovnu klasu** koja implementira interfejs.  
- Klasa mora sadržavati:
  - ime i prezime,  
  - profesionalnu titulu (npr. “Softverski inženjer”, “Inženjer elektrotehnike”),  
  - broj godina iskustva (pozitivna vrijednost),  
  - skup ekspertiza (tehnologije, alati, oblasti).  
- Ugraditi **validaciju podataka** (npr. da lista ekspertiza nije prazna, broj godina iskustva ≥ 0).  
- Ova klasa predstavlja zajedničke osobine svih tipova inženjera.  
- Treba omogućiti prikaz osnovnih informacija o inženjeru (npr. u metodi za ispis).

---

### 3) **Izvedene klase – specifični tipovi inženjera**
- Iz osnovne klase izvesti najmanje **dva tipa inženjera**:  
  1. **Softverski inženjer** – dodatno sadrži broj projekata na kojima je radio.  
  2. **Inženjer elektrotehnike** – dodatno sadrži broj certifikata koje je stekao.  
- Svaka izvedena klasa mora dodati svoje specifično ponašanje ili dodatne metode (npr. izračun uspješnosti na osnovu broja projekata/certifikata).  
- Obezbijediti da sve izvedene klase zadrže osnovne metode interfejsa.

---

## Operacije nad podacima

### 4) **Grupisanje sa `fold()`**
- Kreirati funkcionalnost koja grupiše inženjere po ekspertizama koje posjeduju.  
- U svakoj grupi treba biti samo inženjeri koji imaju više od 5 godina iskustva.  
- Ova operacija treba koristiti **`fold()`** da bi pokazala razumijevanje ručnog akumuliranja vrijednosti.  
- Rezultat treba biti mapa:  
  ekspertiza → lista inženjera koji ispunjavaju uslov.

---

### 5) **Odabir najiskusnijeg sa `reduce()`**
- Implementirati funkcionalnost koja, unutar svake grupe tipova inženjera (softverski, elektrotehnički), pronalazi onog sa najviše godina iskustva.  
- Korištenjem **`reduce()`**, student treba pronaći najiskusnijeg u svakoj kategoriji.  
- Cilj: razumjeti razliku između `reduce()` (gdje kolekcija već postoji) i `fold()` (gdje se akumulator inicijalizuje posebno).

---

### 6) **Agregacija sa `aggregate()`**
- Kreirati funkcionalnost koja koristi **`aggregate()`** da izračuna ukupan broj:
  - projekata svih softverskih inženjera,  
  - certifikata svih elektrotehničkih inženjera.  
- Rezultat treba biti zbir obje vrijednosti — ukupni broj svih projekata i certifikata u kompaniji.  
- Ova funkcija simulira sabiranje ukupnih rezultata iz različitih kategorija (koncept blizak “statistikama” koje se kasnije prikazuju u mobilnim aplikacijama).

---

## Analiza i poređenje
- U README datoteci (ili u komentarima) student treba:
  - objasniti razliku između funkcija `fold`, `reduce` i `aggregate`,  
  - navesti prednosti i ograničenja svake.  
- Treba navesti **kada je koja funkcija najprikladnija** (npr. `reduce` za pronalaženje maksimuma, `fold` za građenje nove kolekcije, `aggregate` za izračune po ključevima).

---

## Prikaz rezultata

### 7) **Ispis podataka**
- Definisati operaciju koja prikazuje informacije o svim inženjerima:
  - ime i prezime,  
  - titulu,  
  - godine iskustva,  
  - ekspertize,  
  - specifičan podatak (projekti ili certifikati).  
- Ispis mora jasno razlikovati tipove inženjera.  
- Format može biti tekstualni prikaz (npr. “Amina – Softverski inženjer – 8 godina – Projekti: 15”).

---

### 8) **Glavni tok programa**
- Kreirati **listu inženjera** koja uključuje oba tipa (softverske i elektrotehničke).  
- Pozvati sve implementirane funkcionalnosti redom:
  - grupisanje po ekspertizama (`fold`),  
  - pronalazak najiskusnijeg (`reduce`),  
  - sabiranje ukupnih vrijednosti (`aggregate`),  
  - ispis rezultata.  
- Rezultati trebaju biti prikazani na jasan i čitljiv način.

---

## Provjere ispravnosti
Student treba uključiti najmanje tri kratke provjere da bi dokazao ispravnost:
- provjera da li je najiskusniji inženjer zaista onaj s najvećim brojem godina,  
- provjera da li `aggregate()` vraća tačan zbir,  
- provjera da li `fold()` filtrira samo inženjere s više od 5 godina iskustva.

---

## README (obavezno)
README datoteka treba sadržavati:
- opis strukture klasa i odnosa (interfejs → osnovna klasa → izvedene klase),  
- objašnjenje korištenih funkcionalnih operacija,  
- poređenje `fold`, `reduce`, `aggregate`,  
- način pokretanja programa,  
- napomenu o korištenju AI alata (ako je bilo).

---

## Pravila o AI i provjeri znanja
- AI alati mogu se koristiti za objašnjenje i testiranje, ali ne za pisanje čitavih dijelova koda.  
- U README-u i komentarima mora biti navedeno gdje je AI korišten.  
- Tokom provjere **AI nije dozvoljen**.  
- Student će dobiti **izmijenjenu verziju zadatka** i mora je implementirati bez pomoći AI alata 
- **Bez uspješne odbrane – zadaća ne nosi bodove.**

---

## Pravila 
- Kod mora biti čitljiv, organizovan i logički podijeljen po klasama i funkcijama.  
- Imena funkcija, klasa i svojstava moraju jasno opisivati njihovu svrhu.  
- Svi stringovi i brojevi trebaju biti validirani.  
- Podaci se ne smiju unositi ručno tokom izvođenja – svi primjeri trebaju biti kreirani u kodu.

---