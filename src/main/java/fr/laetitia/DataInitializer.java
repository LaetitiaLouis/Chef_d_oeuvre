//package fr.laetitia;
//
//import fr.laetitia.model.*;
//import fr.laetitia.repository.*;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
////import org.springframework.boot.CommandLineRunner;
////import org.springframework.stereotype.Component;
////
//@Slf4j
//@AllArgsConstructor
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final AdminRepository adminRepository;
//    private final ClientRepository clientRepository;
//    private final MessageRepository messageRepository;
//    private final PhotoRepository photoRepository;
//    private final PrestationRepository prestationRepository;
//    private final ProjetRepository projetRepository;
//    private final TypeRepository typeRepository;
//
//    public String initData() {
//
//        try {
//            Admin laur33 = new Admin("laur33", true, 123456, "https://www.dropbox.com/s/mvvv8v4cdrm28z9/Laurence%20portrait%201%20NB.jpeg?dl=1", "BBBBBBBBBBBBBBB, NNNNNNNNNNNNNNNNN, LLLLLLLLLLL.");
//            Admin nagar33 = new Admin("nagar33", true, 456123, "https://www.dropbox.com/s/mvvv8v4cdrm28z9/Laurence%20portrait%201%20NB.jpeg?dl=1", "BBBBBBBBBBBBBBB, NNNNNNNNNNNNNNNNN, LLLLLLLLLLL.");
//
//            Client dupont = new Client("DUPONT", "Claude", "avenue de la liberté", "33000", "Bordeaux", "monadresse1@email.com", "05.56.59.84.78.", "DEV1", "FAC1");
//            Client louis = new Client("LOUIS", "Léon", "boulevard de la nation", "33200", "Caudéran", "monadresse2@email.com", "06.66.59.84.78.", "DEV2", "FAC2");
//            Client durand = new Client("DURAND", "Etienne", "rue de la paix", "33340", "Lesparre", "monadresse3@email.com", "07.76.59.84.78.", "DEV3", "FAC3");
//
//            Message devis = new Message("demande de devis", "blablabla blablabla blablabla blablabla", true, 2020 01 15  , 3);
//            Message renseignement = new Message("demande de renseignement", "blablabla blablabla blablabla blablabla", true,2020 03 15, 1);
//            Message devis2 = new Message("demande de devis", "blablabla blablabla blablabla blablabla", true,2020 02 03 , 2);
//
//            Prestation analyse = new Prestation("Analyse PLU", "Autorisation d'urbanise");
//            Prestation faisabilite = new Prestation ("Faisabilité", "Autorisation d'urbanisme");
//            Prestation pc = new Prestation ("Permis de Construire (PC)", "Autorisation d'urbanisme");
//            Prestation dp = new Prestation ("Déclaration Préalable (DP)", "Autorisation d'urbanisme");
//            Prestation releve = new Prestation ("Relevé", "Conception");
//            Prestation etat = new Prestation ("Etat des lieux", "Conception");
//            Prestation etude = new Prestation ("Etude de projet", "Conception");
//            Prestation amenagement = new Prestation ("Aménagement intérieur", "Conception");
//            Prestation accessibilite = new Prestation("Accessibilité PMR", "Conception");
//            Prestation plans2D = new Prestation("Plans 2D", "Dessin");
//            Prestation maquette = new Prestation("Maquette 3D", "Dessin");
//            Prestation images = new Prestation("Images 3D photo réaliste", "Dessin");
//            Prestation animation = new Prestation("Animation", "Dessin");
//
//            Type renovations = new Type("Rénovations");
//            conception.addType(renovations);
//            Type extensions = new Type("Extensions");
//            Type piscines = new Type("Piscines");
//            Type plans = new Type("Plans");
//
//            Projet conception = new Projet ("Conception", "Maison1", "nagar33");
//            dupont.addProjet(conception);
//            analyse.addProjet(conception);
//            pc.addProjet(conception);
//            renovations.addProjet(conception);
//            photo1.addProjet(conception);
//            Projet renovation = new Projet("Rénovation", "Rénovation1", "nagar33");
//            etude.addProjet(renovation);
//            plans2D.addProjet(renovation);
//            maquette.addProjet(renovation);
//            louis.addProjet(renovation);
//            Projet construction = new Projet("Construction piscine", "Piscine1", "laur33");
//            durand.addProjet(construction);
//            dp.addProjet(construction);
//
//            Photo photo1 = new Photo("https://www.dropbox.com/s/g21cay0wqcjsbe7/plan-maison-plans.fr_.jpg?dl=1", "photo1", "accueil");
//            Photo renovation1 = new Photo("https://www.dropbox.com/s/ow2w6kekujhwqd2/golfmarcuspointe-com-12353.jpg?dl=1", "rénovation1", "présentation projet", 2);
//            Photo photo3 = new Photo("https://www.dropbox.com/s/yt8ww4ydhc9o89i/PROJET%204%20RENDU%20AMENAGEMENT.jpg?dl=1", "piscine1", "accueil", 3);
//
//
//
//        } catch (final Exception ex) {
//            return ("Exeption while inserting mock data {0}");
//        }
//        return (null);
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//    }
//}

