import java.util.*;
import java.util.function.Predicate;

public class BibliothequeTest {

    public static <T> void afficherListe(List<T> liste) {
        for (T element : liste) {
            System.out.println(element);
        }
    }

    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T element : liste) {
            if (critere.test(element)) {
                resultat.add(element);
            }
        }
        return resultat;
    }

    public static <T> void copierCollection(Collection<T> source, Collection<T> destination) {
        destination.addAll(source);
    }

    public static void main(String[] args) {

        List<Media> mediasDisponibles = new ArrayList<>();
        Set<Membre> membres = new HashSet<>();
        Map<Membre, List<Media>> empruntsParMembre = new HashMap<>();

        Media livre1 = new Livre("Clean Code", 2008, "Robert C. Martin", 464);
        Media livre2 = new Livre("Effective Java", 2018, "Joshua Bloch", 416);
        Media livre3 = new Livre("Harry Potter", 2000, "J.K. Rowling", 500);
        Media cd1 = new CD("Random Access Memories", 2013, "Daft Punk", 74);
        Media cd2 = new CD("Thriller", 1982, "Michael Jackson", 42);

        mediasDisponibles.addAll(List.of(livre1, livre2, livre3, cd1, cd2));

        Membre m1 = new Membre("Alice", 1);
        Membre m2 = new Membre("Bob", 2);
        Membre m3 = new Membre("Antoine", 3);

        membres.addAll(Set.of(m1, m2, m3));

        m1.emprunterMedia(livre1);
        m1.emprunterMedia(cd1);
        empruntsParMembre.put(m1, m1.getMediasEmpruntes());

        m2.emprunterMedia(livre2);
        empruntsParMembre.put(m2, m2.getMediasEmpruntes());

        m3.emprunterMedia(cd2);
        m3.emprunterMedia(livre3);
        empruntsParMembre.put(m3, m3.getMediasEmpruntes());

        afficherListe(mediasDisponibles);
        afficherListe(new ArrayList<>(membres));

        afficherListe(filtrer(mediasDisponibles, m -> m.getAnneePublication() > 2010));
        afficherListe(filtrer(new ArrayList<>(membres), m -> m.getNom().startsWith("A")));

        List<Media> mediasTries = new ArrayList<>(mediasDisponibles);
        mediasTries.sort(
                Comparator.comparing(Media::getAnneePublication).reversed()
                          .thenComparing(Media::getTitre)
        );
        afficherListe(mediasTries);

        List<Livre> livres = new ArrayList<>();
        for (Media m : mediasDisponibles) {
            if (m instanceof Livre l) {
                livres.add(l);
            }
        }

        livres.sort(
                Comparator.comparing(Livre::getAuteur)
                          .thenComparing(Livre::getTitre)
        );
        afficherListe(livres);

        Set<Media> mediasEmpruntesSansDoublons = new HashSet<>();
        for (List<Media> liste : empruntsParMembre.values()) {
            mediasEmpruntesSansDoublons.addAll(liste);
        }
        afficherListe(new ArrayList<>(mediasEmpruntesSansDoublons));

        afficherListe(filtrer(mediasDisponibles, m -> m instanceof Livre));

        List<Media> copie = new ArrayList<>();
        copierCollection(mediasDisponibles, copie);
        afficherListe(copie);

        for (Media m : mediasDisponibles) {
            m.afficherDetails();
        }
    }
}
