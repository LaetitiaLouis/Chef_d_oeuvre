--Admin
INSERT INTO admin(login, compte_valide, password, photo, presentation, role, prenom) VALUES
( 'nagar33',true, '$2y$10$QRASxzQWKUdzWTbTAFrZIOm5AqvCRBmWI6UHRAshWs.PTlC39bWjO', 'https://www.dropbox.com/s/joarugxs5n5b4it/photo%20Nad%C3%A8geModifi%C3%A9e.jpg?dl=1', 'BBBBBBBBBBBBBBBBBBBB BBBBBBBBBBBBBBBB BBBBBBBBBBBBBBBBB BBBBBBBBBBBBBBB BBBBBBBBBBBBBBB', 'ADMIN', 'Nadège'),
( 'laur33', true, '987654', 'https://www.dropbox.com/s/1wuvh05xq9uh7jf/Laurence%20portrait%201%20NB.jpeg?dl=1', 'CCCCCCCCCCCC CCCCCCCCCCCC CCCCCCCCCCCCCCC CCCCCCCCCCCCCCCC CCCCCCCCCCCCCCC CCCCCCCCCCCCC','ADMIN', 'Laurence');

--Client
INSERT INTO client(nom, prenom, adresse, code_postal, ville, email, telephone, ref_devis, ref_facture) VALUES
( 'DUPONT', 'Claude', 'avenue de la liberté', '33000', 'Bordeaux', 'monadresse1@email.com', '05.56.59.84.78.', 'DEV1', 'FAC1'),
( 'LOUIS', 'Léon', 'boulevard de la nation', '33200', 'Caudéran', 'monadresse2@email.com', '06.66.59.84.78.', 'DEV2', 'FAC2'),
( 'DURAND', 'Etienne', 'rue de la paix', '33340', 'Lesparre', 'monadresse3@email.com', '07.76.59.84.78.', 'DEV3', 'FAC3');

--Message
INSERT INTO message(objet, contenu, vu,  date, client_id, statut_client) VALUES
('demande de devis', 'blablabla blablabla blablabla blablabla', true, '2020-01-15', 3, false ),
('demande de renseignement', 'blablabla blablabla blablabla blablabla', true, '2020-03-18', 1, false ),
('demande de devis', 'blablabla blablabla blablabla blablabla', true, '2020-03-03', 2, false );


--Prestation
INSERT INTO prestation(intitule, categorie) VALUES
('Analyse PLU', 'Autorsation d''urbanisme'),
('Faisabilité', 'Autorsation d''urbanisme'),
('Permis de Construire (PC)', 'Autorsation d''urbanisme'),
('Declaration Prealable (DP)', 'Autorsation d''urbanisme'),
('Relevé', 'Conception'),
('Etat des lieux', 'Conception'),
('Etude de projet', 'Conception'),
('Aménagement intérieur', 'Conception'),
('Accessibilité PMR', 'Conception'),
('Plans 2D', 'Dessin'),
('Maquette 3D', 'Dessin'),
('Images 3D photo réaliste', 'Dessin'),
('Animation', 'Dessin');

--Type
INSERT INTO type(libelle) VALUES
('Rénovations'),
('Extensions'),
('Piscines'),
('Plans');

--Projet
INSERT INTO projet(description, intitule, admin_login, client_id, type_id) VALUES
('Conception de plans pour maison de moins de 150m²', 'Plans maison Mr X', 'nagar33', 1, 4),
('Renovation d''une grange en habitation' , 'Rénovation grange', 'nagar33', 2, 1),
('Réalisation d''une piscine de 10m*6m', 'Piscine', 'laur33', 2, 3),
('Extension d''une batisse extistante par la cuisine', 'Extension', 'laur33', 3, 2);

--Photo
INSERT INTO photo(lien, nom, categorie, projet_id) VALUES
('https://www.dropbox.com/s/rxvun9vcujbpxwy/DemiNiveau_petit-01-Avant.jpg?dl=1', 'Interieur3D', 'accueil', 1),
('https://www.dropbox.com/s/rxvun9vcujbpxwy/DemiNiveau_petit-01-Avant.jpg?dl=1', 'Interieur3D', 'accueil',2),
('https://www.dropbox.com/s/rxvun9vcujbpxwy/DemiNiveau_petit-01-Avant.jpg?dl=1', 'Interieur3D', 'accueil', 3),
('https://www.dropbox.com/s/rxvun9vcujbpxwy/DemiNiveau_petit-01-Avant.jpg?dl=1', 'Interieur3D', 'accueil', 4),
('https://www.dropbox.com/s/ld6zimvevcjgwoy/golfmarcuspointe-com-12353.jpg?dl=1', 'Façade', 'projet', 1),
('https://www.dropbox.com/s/ld6zimvevcjgwoy/golfmarcuspointe-com-12353.jpg?dl=1', 'Façade', 'projet', 2),
('https://www.dropbox.com/s/guvo26lwax2lizu/plan-maison-plans.fr_.jpg?dl=1', 'Maisonpiscine', 'projet', 3),
('https://www.dropbox.com/s/guvo26lwax2lizu/plan-maison-plans.fr_.jpg?dl=1', 'Maisonpiscine', 'projet', 4),
('https://www.dropbox.com/s/tfizlynee8y5yvt/carousel.jpeg?dl=1', 'plans', 'carousel', null ),
('https://www.dropbox.com/s/bj39us6woanjwx1/IMG_1707.jpg?dl=1', 'photo', 'carousel', null ),
('https://www.dropbox.com/s/hl84s7m61lk4sxh/image%20croquis%203.JPG?dl=1', 'croquis', 'carousel', null );

--Prestation_liste_projets
INSERT INTO prestation_liste_projets(prestations_id, liste_projets_id) VALUES
(1, 1),
(2, 3),
(3, 2);

--Type_liste_projets
INSERT INTO type_liste_projets(type_id, liste_projets_id) VALUES
(1, 1),
(2, 3),
(3, 2);

--Client liste de messages
--INSERT INTO client_liste_messages (client_id, liste_messages_id) VALUES
--(3, 1),
--(2, 3),
--(1, 2);
