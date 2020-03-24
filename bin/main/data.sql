--Admin
INSERT INTO admin(login, password, photo, presentation) VALUES 
( 'nagar33', '123456', '', 'BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB'),
( 'laur33', '987654', '', 'CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC');

--Admin projets
INSERT INTO admin_projets(admin_login, projets_id) VALUES
( 'nagar33', 1),
( 'laur33', 2);

--Client
INSERT INTO client(nom, prenom, adresse, code_postal, ville, email, telephone, ref_devis, ref_facture) VALUES
( 'DUPONT', 'Claude', 'avenue de la liberté', '33000', 'Bordeaux', 'monadresse1@email.com', '05.56.59.84.78.', 'DEV1', 'FAC1'),
( 'LOUIS', 'Léon', 'boulevard de la nation', '33200', 'Caudéran', 'monadresse2@email.com', '06.66.59.84.78.', 'DEV2', 'FAC2'),
( 'DURAND', 'Etienne', 'rue de la paix', '33340', 'Lesparre', 'monadresse3@email.com', '07.76.59.84.78.', 'DEV3', 'FAC3');

--Client projets
INSERT INTO client_projets(client_id, projets_id) VALUES
( 1, 1);

--Message
INSERT INTO message (objet, contenu, client) VALUES
('demande de devis', 'blablablablablablablablablablablabla', true);

--Photo
INSERT INTO photo(lien, nom, projet_id) VALUES
('photo1', 'maison1', 1),
('photo2', 'rénovation1', 2),
('photo3', 'piscine1', 3);

--Prestation
INSERT INTO prestation(contenu, intitule) VALUES
('', 'Etude du PLU'),
('', 'Relevé sur site'),
('', 'Déclaration Préalable de Travaux (extension, abri de jardin, carport, clôture, modification de la façade)'),
('', 'Permis de Construire pour surface de moins de 150m² (extension ou nouvelle construction)'),
('', 'Conception-Plan d''aménagment intérieur'),
('', 'Volumétrie de votre projet avec une maquette 3D');

--Prestation projets
INSERT INTO prestation_projets (prestation_id, projets_id) VALUES
(1, 1),
(2, 1),
(3, 1);


--Projet
INSERT INTO projet (description, intitule, admin_login, client_id, type_id) VALUES
('Conception avec Plan d\'aménagement d\’une Echoppe avant achat', 'Maison d\'habitation1', 'nagar33', 1, 4),
('Renovation d\'une maison', 'Rénovation1', 'laur33', 2, 1),
('Construction d\'une piscine', 'Piscine1', 'nagar33', 3, 3);

--Projet photos
INSERT INTO projet_photos (projet_id, photos_id) VALUES
(1, 1),
(2, 2),
(3, 3);

--Projet prestations
INSERT INTO projet_prestations (projet_id, prestations_id) VALUES
(1, 1),
(2, 5),
(3, 2);

--Type
INSERT INTO type(libelle) VALUES
('Rénovations'),
('Extensions'),
('Piscines'),
('Plans');

--Type_projets
INSERT INTO type_projets(type_id, projets_id) VALUES
(1, 4),
(2, 1),
(3, 3);
