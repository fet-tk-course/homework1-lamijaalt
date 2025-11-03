// Interfejs - "Osoba" - definiše osnovno ponašanje koje svaka osoba mora imati .
interface Osoba {
    fun puniIdentitet(): String
    fun oznZemljeP(): String
}

// Osnovna klasa - "Programer" - implementira interface "Osoba" i sadrži osnovne osobine svih programera.
// Definisane osnovne osobine
open class Programer(val ime: String, val prezime: String, val brGodIskustva: Int, val oznakaZemlje: String,                     
    jezici: List<String>) : Osoba //Obrađeni nazivi jezika (pretvoreni u mala slova)
	{ val skupJezika: List<String> = jezici.map { jezik -> jezik.lowercase() } 
    
// Init blok u kojem se izvršava potrebna validacija podataka

    init {
        require(ime.isNotBlank()){"Ime ne može biti prazno."}
        require(prezime.isNotBlank()) { "Prezime ne može biti prazno" }
        require(brGodIskustva >= 0) { "Broj godina iskustva ne može biti negativan." }
   		require(jezici.isNotEmpty()) { "Lista jezika ne smije biti prazna." }
    }
    
// Implementacija metoda iz interface "Osoba"
    override fun puniIdentitet() = "$ime $prezime"
    override fun oznZemljeP() = oznakaZemlje
}

/* Izvedene klase - "BackendDeveloper" i "FrontendDeveloper", nasljeđuju klasu "Programer" sa dodatnom informacijom
o framework */

class BackendDeveloper(ime: String, prezime: String, brGodIskustva: Int, oznakaZemlje: String, jezici: List<String>,
    val framework: String) : Programer(ime, prezime, brGodIskustva, oznakaZemlje, jezici)

class FrontendDeveloper(ime: String, prezime: String, brGodIskustva: Int, oznakaZemlje: String, jezici: List<String>,
    val framework: String) : Programer(ime, prezime, brGodIskustva, oznakaZemlje, jezici)

/* Funkcija koja vraća podatke koliko programera koristi određeni programski jezik. Ova verzija je odrađena korištenjem 
 ugrađenih funkcija za grupisanje.  */

fun brProgrameraPoJeziku(programeri: List<Programer>): Map<String, Int> {
    return programeri.flatMap{programer -> programer.skupJezika }.groupingBy{jezik -> jezik }.eachCount()
}

//Druga verzija funkcije iznad, odrađena ručno korištenjem petlji

fun brProgrameraPoJezikuRucno(programeri: List<Programer>): Map<String, Int> {
    val brPoJeziku = mutableMapOf<String, Int>()
    for (programer in programeri) {
        for (jezik in programer.skupJezika) {
            brPoJeziku[jezik] = (brPoJeziku[jezik] ?: 0) + 1
        }
    }
    return brPoJeziku
}

/* Funkcija koja vraća podatke o prosječnom radnom iskustvu programera koji koriste određeni jezik. Ova verzija je odrađena 
korištenjem ugrađenih funkcija za grupisanje */

fun prosjekIskustvaPoJeziku(programeri: List<Programer>): Map<String, Double> {
    return programeri.flatMap { programer -> programer.skupJezika.map {jezik -> jezik to programer.brGodIskustva} }
        .groupBy(
            { par -> par.first },
            { par -> par.second }
        ).mapValues { (_, lista) -> lista.average() }
}

//Druga verzija funkcije iznad, odrađena ručno korištenjem petlji

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

// Funkcija za filtriranje programera po framework koji koriste
fun filterPoFrameworku(programeri: List<Programer>, fw: String): List<Programer> {
    return programeri.filter { programer ->
        (programer is BackendDeveloper && programer.framework == fw) ||
        (programer is FrontendDeveloper && programer.framework == fw)
    }
}


fun developersUsingAll(programeri: List<Programer>, jezici: List<String>) : List<Programer> {
    val filtriraniJezici = jezici.map { it.lowercase() }
    return programeri.filter { programer -> filtriraniJezici.all { jezik -> jezik in programer.skupJezika } }
}


//Funkcija za ispis programera
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

// Glavni program
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
  
    val langs = listOf("Typescript")
	println("\n Programeri koji znaju sve jezike iz liste : $langs:")
	val filtered = developersUsingAll(programeri, langs)
	prikaziProgramere(filtered)
    
    
    //Provjera ispravnosti sa izuzecima.
    // U ovom dijelu koda je korišten AI alat, koji je predložio rješenje sa require provjerama
    
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
