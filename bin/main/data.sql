--Admin
INSERT INTO admin(login, password, photo, presentation) VALUES 
( 'nagar33', '123456', '', 'BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB'),
( 'lau33', '987654', '', 'CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC');

--Photo
INSERT INTO photo(lien, nom, projet_id) VALUES
(),
();

--Prestation
INSERT INTO prestation(contenu, intitule) VALUES
('', 'Etude du PLU'),
('', 'Relevé sur site'),
('', 'Déclaration Préalable de Travaux (extension, abri de jardin, carport, clôture, modification de la façade'),
('', 'Permis de Construire pour surface de moins de 150m² (extension ou nouvelle construction'),
('', 'Conception-Plan d''aménagment intérieur'),
('', 'Volumétrie de votre projet avec une maquette 3D');

--Projet
INSERT INTO projet (description, intitule, admin_login, type_id) VALUES
('Conception avec Plan d’aménagement d’une Echoppe avant achat', 'Maison d''habitation', 'nagar33', '1'),
(),
();

--Prospect
INSERT INTO prospect (nom, prenom, email, telephone, objet, message) VALUES
(),
(),
();

--Type
INSERT INTO type(libelle) VALUES
('Rénovations'),
('Extensions'),
('Piscines'),
('Plans');
