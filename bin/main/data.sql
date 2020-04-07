--Admin
INSERT INTO admin(login, password, photo, presentation) VALUES 
( 'nagar33', '123456', '', 'BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB'),
( 'laur33', '987654', '', 'CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC');

--Client
INSERT INTO client(nom, prenom, adresse, code_postal, ville, email, telephone, ref_devis, ref_facture) VALUES
( 'DUPONT', 'Claude', 'avenue de la liberté', '33000', 'Bordeaux', 'monadresse1@email.com', '05.56.59.84.78.', 'DEV1', 'FAC1'),
( 'LOUIS', 'Léon', 'boulevard de la nation', '33200', 'Caudéran', 'monadresse2@email.com', '06.66.59.84.78.', 'DEV2', 'FAC2'),
( 'DURAND', 'Etienne', 'rue de la paix', '33340', 'Lesparre', 'monadresse3@email.com', '07.76.59.84.78.', 'DEV3', 'FAC3');

--Message
INSERT INTO message(expediteur, objet, contenu, client, date) VALUES
('DURAND','demande de devis', 'blablablablablablablablablablablabla', true, '2020-03-25' );

--Prestation
INSERT INTO prestation(contenu, intitule) VALUES
('', 'Etude du PLU'),
('', 'Relevé sur site'),
('', 'Déclaration Préalable de Travaux (extension, abri de jardin, carport, clôture, modification de la façade)'),
('', 'Permis de Construire pour surface de moins de 150m² (extension ou nouvelle construction)'),
('', 'Conception-Plan d''aménagment intérieur'),
('', 'Volumétrie de votre projet avec une maquette 3D');

--Type
INSERT INTO type(libelle) VALUES
('Rénovations'),
('Extensions'),
('Piscines'),
('Plans');

--Projet
INSERT INTO projet(description, intitule, admin_login, client_id, type_id) VALUES
('Conception', 'Maison1', 'nagar33', 1, 4),
('Renovation', 'Rénovation1', 'nagar33', 2, 1),
('Construction piscine', 'Piscine1', 'laur33', 3, 3);

--Photo
INSERT INTO photo(lien, nom, categorie, projet_id) VALUES
('C:\\jean\\NLProjet\\src\\assets\\plan-maison-plans.fr_.jpg', 'photo1', 'accueil', 1),
('photo2', 'rénovation1', 'présentation projet', 2),
('photo3', 'piscine1', 'description projet', 3);

--Prestation projets
INSERT INTO prestation_projet(prestation_id, projet_id) VALUES
(1, 1),
(2, 3),
(3, 2);
