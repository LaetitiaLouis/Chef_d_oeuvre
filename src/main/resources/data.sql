--Admin
INSERT INTO admin(login, compte_valide, password, photo, presentation, role, prenom) VALUES
( 'nagar33',true, '$2y$10$QRASxzQWKUdzWTbTAFrZIOm5AqvCRBmWI6UHRAshWs.PTlC39bWjO', 'https://www.dropbox.com/s/65o0j4dfhwkg1rx/photo%20Nad%C3%A8g.jpg?dl=1',
'Passionnée par l''architecture et la construction, j''ai entrepris en 2019 une orientation professionnelle vouée au bâtiment. J''ai suivi des formations de dessin en batîment, et suis diplômée d''un titre professionnel de Technicien d''études du bâtiment - Dessin de projets (2019). Les enjeux environnementaux actuels et futurs étant primordiaux, construire doit être un acte responsable, aussi je m''attache à concevoir dans une démarche bioclimatique. Je considère également le design comme primordial.
La conception architecturale, tant fonctionnelle qu''esthétique doit aussi être initialement parfaitement pensée. J''exerce mon activité de dessinateur principalement sur le logiciel Autocad mais suis également compétent sur d''autres logiciels d''architecture sur lesquels j''ai aussi été formée.', 'ADMIN', 'Nadège'),
( 'laur33', true, '987654', 'https://www.dropbox.com/s/1wuvh05xq9uh7jf/Laurence%20portrait%201%20NB.jpeg?dl=1', 'Architecte depuis plus de 15 ans, j''ai tout au long de mon parcours professionnel géré des projets de tailles et natures différentes (santé, industrie, tertiaire, éducation, etc.).
Mon expérience d’architecte est complète : avant tout femme de terrain, je connaît sur le bout des doigts les problématiques de la gestion du projet architectural pour l’ensemble des phases, de la création architecturale, jusqu’à sa concrétisation.
En perpétuelle remise en question, c’est dans l’exercice de mon métier que naît l’envie d’exploiter de nouveaux outils (visualisation), et c’est dans la volonté de transmettre et de combiner compétence et originalité que naît celle de faire vivre le partenariat LNProjet. Aussi, mes conceptions sont principalement réalisées via Revit et Autocad.','ADMIN', 'Laurence');

--Client
INSERT INTO client(nom, prenom, adresse, code_postal, ville, email, telephone, ref_devis, ref_facture) VALUES
( 'DUPONT', 'Claude', 'avenue de la liberté', '33000', 'Bordeaux', 'monadresse1@email.com', '05.56.59.84.78.', 'DEV1', 'FAC1'),
( 'LOUIS', 'Léon', 'boulevard de la nation', '33200', 'Caudéran', 'monadresse2@email.com', '06.66.59.84.78.', 'DEV2', 'FAC2'),
( 'DURAND', 'Etienne', 'rue de la paix', '33340', 'Lesparre', 'monadresse3@email.com', '07.76.59.84.78.', 'DEV3', 'FAC3'),
( 'GATET', 'Laurent', 'Parvis des remparts', '33100', 'Bordeaux', 'monadresse4@email.com', '06.85.14.63.08.', 'DEV4', 'FAC4'),
( 'HABDOU', 'Said', '11 rue Charles Lamoureux', '33400', 'Pessac', 'monadresse5@email.com', '05.56.66.48.23.', 'DEV5', 'FAC5');

--Message
INSERT INTO message(objet, contenu, vu,  date, client_id, statut_client) VALUES
('demande de devis', 'blablabla blablabla blablabla blablabla', true, '2020-01-15', 3, false ),
('demande de renseignement', 'blablabla blablabla blablabla blablabla', false, '2020-03-18', 1, false ),
('demande de devis', 'blablabla blablabla blablabla blablabla', false, '2020-03-03', 2, false );


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
('Plans'),
('Réaménagements');

--Projet
INSERT INTO projet(description, intitule, admin_login, client_id, type_id) VALUES
('Conception de plans pour maison de moins de 150m², avant-projet, réalisation du dossier de déclaration préalable de travaux', 'Plans maison Mr X', 'nagar33', 1, 4),
('Renovation d''une grange en habitation' , 'Rénovation grange', 'nagar33', 2, 1),
('Réalisation d''une piscine de 10m*6m', 'Piscine', 'laur33', 2, 3),
('Extension d''une batisse extistante par la cuisine', 'Extension', 'laur33', 3, 2),
('Rénovation et extension d''une maison individuelle en R+1, avant-projet, réalisation du dossier de déclaration préalable de travaux','Rénovation et extension d''une maison individuelle','nagar33', 2, 4),
('Relevé de l''existant, conception de la rénovation, de l''extension et l''aménagement des combles, plans projet, exploitation des volumes, visualisations 3D, rénovation thermique, coordination des travaux, suivi chantier', 'Rénovation d''un appartement','laur33',1,5),
('Agrandissement de l''espace habitable d''un appartement situé au niveau des caves d''une maison de ville. Aménagement d''un toit terrasse végétalisé', 'Extension et réaménagement d''un appartement','nagar33', 4, 5 );

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
('https://www.dropbox.com/s/hl84s7m61lk4sxh/image%20croquis%203.JPG?dl=1', 'croquis', 'carousel', null ),
('https://www.dropbox.com/s/t9ojcs1750siw7l/thumb_25737318.jpg?dl=1', 'Plans', 'projet', 5),
('https://www.dropbox.com/s/x6qd8ixdmijie3i/thumb_25737321.jpg?dl=1', 'Interieur3D','projet', 5),
('https://www.dropbox.com/s/7ozi4mc6qcc8p2o/thumb_25737319.jpg?dl=1', 'Interieur3D', 'accueil', 5);

--Prestation_liste_projets
INSERT INTO prestation_liste_projets(prestations_id, liste_projets_id) VALUES
(1, 1),
(2, 3),
(3, 2),
(1, 4),
(2, 5),
(3, 5),
(7, 4);




