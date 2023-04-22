import java.time.LocalDate;
import java.util.*;

interface IBiletomat{
    public void dodajBiletDoOferty(String rodzaj, double cena);
    void wydrukujTransakcje(LocalDate date);
    void sprzedaz();
}

public class Biletomat implements IPieniadz,ISerwis,IBiletomat{

    String id;
    String lokalizacja;

    public Biletomat(String lokalizacja) {
        this.lokalizacja = lokalizacja;
        this.id = this.lokalizacja.charAt(1) + this.lokalizacja.charAt(10) + this.lokalizacja.charAt(15)+"";
        this.ofertaBiletow = new ArrayList<>();
        this.listaTransakcji = new ArrayList<>();
        this.iloscBlankietow = 100;
        this.iloscMonet = 50;
        this.awaria = false;
        this.blokada = false;
        this.komunikacja = true;
        this.czynnosciSerwisowe = new ArrayList<>();
    }

    ArrayList<Bilet> ofertaBiletow;
    public void dodajBiletDoOferty(String rodzaj, double cena){
        Bilet bilet = new Bilet(rodzaj, cena);
        this.ofertaBiletow.add(bilet);
    }

    public int iloscBlankietow;
    double iloscMonet;
    boolean awaria;
    boolean blokada;
    boolean komunikacja;
    ArrayList <String> czynnosciSerwisowe;

    public ArrayList<Transakcja> listaTransakcji;
    public void wydrukujTransakcje(LocalDate date){
        for(Transakcja i : this.listaTransakcji) {
            if (i.data.toString().equals(date.toString()))
                System.out.println(i);
        }
    }

    public void sprzedaz(){
        Scanner in = new Scanner(System.in);
        Transakcja tr = new Transakcja();

        //process wyboru biletow
        boolean koniec = false;
        while(!koniec && !this.awaria && !this.blokada){
            System.out.println("Bilety w ofercie: ");
            for(Bilet i : this.ofertaBiletow){
                System.out.println(this.ofertaBiletow.indexOf(i)+ " - " + i.toString());
            }
            System.out.println("Prosze podac numer biletu: ");
            String numerBiletu = in.nextLine();
            System.out.println("Prosze podac liczbe biletow: ");
            String liczbaBiletow = in.nextLine();
            tr.rodzajLiczba.add(this.ofertaBiletow.get(Integer.parseInt(numerBiletu)).rodzaj + "("+liczbaBiletow+")");
            tr.kwota += this.ofertaBiletow.get(Integer.parseInt(numerBiletu)).cena*Integer.parseInt(liczbaBiletow);
            this.iloscBlankietow-= Integer.parseInt(liczbaBiletow);
            System.out.println("Chcesz wybrac inny rodzaj biletu(tak/nie): ");
            String odp = in.nextLine();
            if(Objects.equals(odp, "nie")) koniec = true;
            sprawdzAwarie(this);
        }

        System.out.println("Do zaplaty: " + tr.kwota + ". Podaj rodzaj platnosci(karta/gotowka): ");
        String rodzPl = in.nextLine();

        zrealizujPlatnosc(rodzPl, tr);

        this.iloscMonet+=tr.kwota;
        this.listaTransakcji.add(tr);

        System.out.println("Bilet wydrukowany.");

        System.out.println("Kolejny zakup?(tak/nie)");
        String zakup = in.nextLine();
        if(Objects.equals(zakup, "tak")) this.sprzedaz();
    }

    @Override
    public String toString(){
        for(Transakcja i : listaTransakcji) {
            System.out.println(i.toString());
        }
        return "";
    }

    /**main block*/
    public static void main(String[] args) {
        //tworzymy biletomaty
        Biletomat b1 = new Biletomat("Krakow, Basztowa 8");
        Biletomat b2 = new Biletomat("Poznan, Dluga 74");

        b1.iloscBlankietow = 0;
        b1.sprawdzAwarie(b1);
        b1.listaCzynnosci(b1);

        System.out.println("\n");

        b2.iloscMonet = 0;
        b2.sprawdzAwarie(b2);
        b2.listaCzynnosci(b2);

    }
}

class Transakcja{
    LocalDate data;
    ArrayList<String> rodzajLiczba;
    double kwota;

    public Transakcja() {
        this.data = LocalDate.now();
        this.rodzajLiczba = new ArrayList<>();
        this.kwota = 0;
    }

    @Override
    public String toString() {
        return String.join(" : ", data.toString(), String.join(",", rodzajLiczba), ""+kwota);
    }
}

class Bilet{
    /**tworzony przez biletomat**/

    String rodzaj;
    double cena;

    public Bilet(String rodzaj, double cena) {
        this.rodzaj = rodzaj;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Bilet " + rodzaj + ", " + cena + "zl";
    }
}

interface ISerwis{
    default void sprawdzAwarie(Biletomat b){
        if(b.iloscBlankietow==0 || b.iloscMonet==0 || !b.komunikacja){
            b.awaria = true;
            String powod = (b.iloscBlankietow == 0) ? "brak blankietow" : ((b.iloscMonet == 0) ? "brak monet" : "brak komunikacji");
            b.czynnosciSerwisowe.add(LocalDate.now() + " - " + powod);
        }
        b.czynnosciSerwisowe.add(LocalDate.now() + " - sprawdzenie awarii");
    }

    default void zmienUstawieniaBiletu(ArrayList<Bilet> ofertaBiletow, int numerBiletuDoZmiany){
        //przykladowa zmiana ceny biletu
        ofertaBiletow.get(3).cena+=1.0;
    }

    default void stanBiletomatu(Biletomat b){
        sprawdzAwarie(b);
        System.out.println("Biletomat " + b.id);
        System.out.println("Ilosc blankietow: " + b.iloscBlankietow);
        System.out.println("Ilosc monet: " + b.iloscMonet);
        System.out.println("Komunikacja: " + b.komunikacja);
        System.out.println("Awaria: " + b.awaria);
        System.out.println("Blokada: " + b.blokada);
    }

    default void zablokuj(Biletomat b){
        b.blokada=true;
        b.czynnosciSerwisowe.add(LocalDate.now()+" - blokada");
    }

    default void listaCzynnosci(Biletomat b){
        System.out.println("Biletomat " + b.id);
        System.out.println(String.join("\n",b.czynnosciSerwisowe));
    }
}

interface IPieniadz{
     default void zrealizujPlatnosc(String rodzPl, Transakcja tr){
        if(Objects.equals(rodzPl, "karta")){
            System.out.println("Prosze zblizyc karte...");
        }
        else if(Objects.equals(rodzPl, "gotowka")){
            System.out.println("Prosze podac kwote monet: ");
            Scanner in = new Scanner(System.in);
            double kwotaMonet = in.nextDouble();
            if(kwotaMonet!=tr.kwota){
                System.out.println("Reszta do odebrania: " + (kwotaMonet-tr.kwota));
            }
        }
        else System.out.println("Prosze podac wlasciwy srodek platnosci!");
    }
}
