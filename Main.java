import java.util.*;
import java.util.function.Predicate;

public class Main {

    public static <T> void afficherListe(List<T> liste) {
        for (T e : liste) {
            System.out.println(e);
        }
    }

    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T e : liste) {
            if (critere.test(e)) {
                resultat.add(e);
            }
        }
        return resultat;
    }

    public static <T> void copierCollection(Collection<T> source, Collection<T> destination) {
        destination.addAll(source);
    }

    public static void main(String[] args) {

        // ETAPE 1 : Hiérarchie de classes (Media, Livre, CD)
        Livre l1 = new Livre("1984", 1949, "George Orwell", 328);
        Livre l2 = new Livre("Clean Code", 2008, "Robert Martin", 464);
        Livre l3 = new Livre("Effective Java", 2018, "Joshua Bloch", 416);

        CD c1 = new CD("Thriller", 1982, "Michael Jackson", 42);
        CD c2 = new CD("Random Access Memories", 2013, "Daft Punk", 74);

        List<Media> medias = new ArrayList<>();
        medias.add(l1);
        medias.add(l2);
        medias.add(l3);
        medias.add(c1);
        medias.add(c2);

        // ETAPE 2 : Classe Membre
        Membre m1 = new Membre("Alice", 1);
        Membre m2 = new Membre("Bob", 2);
        Membre m3 = new Membre("Alex", 3);

        Set<Membre> membres = new HashSet<>();
        membres.add(m1);
        membres.add(m2);
        membres.add(m3);

        // ETAPE 3 : Collections (List, Set, Map)
        Map<Membre, List<Media>> emprunts = new HashMap<>();
        emprunts.put(m1, new ArrayList<>());
        emprunts.put(m2, new ArrayList<>());

        m1.emprunterMedia(l1);
        m1.emprunterMedia(c1);
        emprunts.get(m1).add(l1);
        emprunts.get(m1).add(c1);

        m2.emprunterMedia(l3);
        emprunts.get(m2).add(l3);

        afficherListe(medias);

        // ETAPE 4 : Méthodes génériques
        List<Media> mediasApres2010 =
                filtrer(medias, m -> m.getAnneePublication() > 2010);

        afficherListe(mediasApres2010);

        List<Membre> membresNomA =
                filtrer(new ArrayList<>(membres), m -> m.getNom().startsWith("A"));

        afficherListe(membresNomA);

        // ETAPE 5 : Tri et comparateurs
        medias.sort((mA, mB) -> {
            int cmp = Integer.compare(mB.getAnneePublication(), mA.getAnneePublication());
            if (cmp != 0) return cmp;
            return mA.getTitre().compareTo(mB.getTitre());
        });

        afficherListe(medias);

        List<Livre> livres = new ArrayList<>();
        livres.add(l1);
        livres.add(l2);
        livres.add(l3);

        livres.sort((a, b) -> {
            int cmp = a.getAuteur().compareTo(b.getAuteur());
            if (cmp != 0) return cmp;
            return a.getTitre().compareTo(b.getTitre());
        });

        afficherListe(livres);

        // ETAPE 6 : Défis avancés
        List<Media> copieMedias = new ArrayList<>();
        copierCollection(medias, copieMedias);
        afficherListe(copieMedias);

        Set<Media> mediasEmpruntes = new HashSet<>();
        for (List<Media> liste : emprunts.values()) {
            mediasEmpruntes.addAll(liste);
        }
        for (Media m : mediasEmpruntes) {
            System.out.println(m);
        }

        List<Media> uniquementLivres =
                filtrer(medias, m -> m instanceof Livre);

        afficherListe(uniquementLivres);

        // ETAPE 7 : Pour aller plus loin (polymorphisme)
        for (Media m : medias) {
            m.afficherDetails();
        }
    }
}
