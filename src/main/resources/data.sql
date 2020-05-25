--Admin
INSERT INTO admin(login, compte_valide, password, photo, presentation, role, prenom) VALUES
( 'nagar33',true, '$2y$10$QRASxzQWKUdzWTbTAFrZIOm5AqvCRBmWI6UHRAshWs.PTlC39bWjO', 'https://www.dropbox.com/s/65o0j4dfhwkg1rx/photo%20Nad%C3%A8g.jpg?dl=1',
'Passionnée par l''architecture et la construction, j''ai entrepris en 2019 une orientation professionnelle vouée au bâtiment. J''ai suivi des formations de dessin en batîment, et suis diplômée d''un titre professionnel de Technicien d''études du bâtiment - Dessin de projets (2019). Les enjeux environnementaux actuels et futurs étant primordiaux, construire doit être un acte responsable, aussi je m''attache à concevoir dans une démarche bioclimatique. Je considère également le design comme primordial.
La conception architecturale, tant fonctionnelle qu''esthétique doit aussi être initialement parfaitement pensée. J''exerce mon activité de dessinateur principalement sur le logiciel Autocad mais suis également compétente sur d''autres logiciels d''architecture sur lesquels j''ai aussi été formée.', 'ADMIN', 'Nadège'),
( 'laur33', true, '987654', 'https://www.dropbox.com/s/1wuvh05xq9uh7jf/Laurence%20portrait%201%20NB.jpeg?dl=1', 'Architecte depuis plus de 15 ans, j''ai tout au long de mon parcours professionnel géré des projets de tailles et natures différentes (santé, industrie, tertiaire, éducation, etc.).
Mon expérience d’architecte est complète : avant tout femme de terrain, je connais sur le bout des doigts les problématiques de la gestion du projet architectural pour l’ensemble des phases, de la création architecturale, jusqu’à sa concrétisation.
En perpétuelle remise en question, c’est dans l’exercice de mon métier que naît l’envie d’exploiter de nouveaux outils (visualisation), et c’est dans la volonté de transmettre et de combiner compétence et originalité que naît celle de faire vivre le partenariat LNProjet. Aussi, mes conceptions sont principalement réalisées via Revit et Autocad.','ADMIN', 'Laurence');

--Client
INSERT INTO client(nom, prenom, adresse, code_postal, ville, email, telephone, ref_devis, ref_facture) VALUES
( 'dupont', 'claude', 'avenue de la liberté', '33000', 'bordeaux', 'monadresse1@email.com', '05.56.59.84.78.', 'DEV1', 'FAC1'),
( 'louis', 'léon', 'boulevard de la nation', '33200', 'caudéran', 'monadresse2@email.com', '06.66.59.84.78.', 'DEV2', 'FAC2'),
( 'durand', 'etienne', 'rue de la paix', '33340', 'lesparre', 'monadresse3@email.com', '07.76.59.84.78.', 'DEV3', 'FAC3'),
( 'gatet', 'laurent', 'parvis des remparts', '33100', 'bordeaux', 'monadresse4@email.com', '06.85.14.63.08.', 'DEV4', 'FAC4'),
( 'habdou', 'said', '11 rue Charles Lamoureux', '33400', 'pessac', 'monadresse5@email.com', '05.56.66.48.23.', 'DEV5', 'FAC5');

--Message
INSERT INTO message(objet, contenu, vu,  date, client_id, statut_client) VALUES
('demande de devis', 'blablabla blablabla blablabla blablabla', true, '2020-01-15', 3, false ),
('demande de renseignement', 'blablabla blablabla blablabla blablabla', false, '2020-03-18', 1, false ),
('demande de devis', 'blablabla blablabla blablabla blablabla', false, '2020-03-03', 2, false );


--Prestation
INSERT INTO prestation(intitule, categorie) VALUES
('analyse PLU', 'autorsation d''urbanisme'),
('faisabilité', 'autorsation d''urbanisme'),
('permis de construire (PC)', 'autorsation d''urbanisme'),
('déclaration Préalable (DP)', 'autorsation d''urbanisme'),
('relevé', 'conception'),
('état des lieux', 'conception'),
('étude de projet', 'conception'),
('aménagement intérieur', 'conception'),
('accessibilité PMR', 'conception'),
('plans 2D', 'dessin'),
('maquette 3D', 'dessin'),
('images 3D photo réaliste', 'dessin'),
('animation', 'dessin');

--Type
INSERT INTO type(libelle) VALUES
('aménagements intérieur'),
('extensions'),
('rénovations'),
('réhabilitations'),
('piscines');

--Projet
INSERT INTO projet(description, intitule, admin_login, client_id, type_id) VALUES
('Conception de plans pour maison de moins de 150m², avant-projet, réalisation du dossier de déclaration préalable de travaux', 'extension maison individuelle mérignac', 'nagar33', 1, 2),
('Aménagement intérieur d''un appartement avec optimisation du rangement,  avant-projet, réalisation du dossier de déclaration préalable de travaux' , 'aménagement intérieur appartement ', 'nagar33', 2, 1),
('Extension d''un cabinet médical. Relevé de l''existant, conception de la rénovation, de l''extension et l''aménagement des combles, plans projet, exploitation des volumes, visualisations 3D, rénovation thermique, coordination des travaux, suivi chantier' , 'extension d''un cabinet médical à pessac', 'nagar33', 2, 2),
('Réalisation projection piscine d''une piscine de 10m*6m,  avant-projet, réalisation du dossier de déclaration préalable de travaux', 'piscine', 'laur33', 2, 5),
('Construction de logements locatifs', 'logements locatifs à castelneau du médoc', 'laur33', 3, 3),
('Rénovation d''une maison individuelle', 'rénovation maison individuelle mérignac', 'laur33', 4, 3),
('Réhabilitation d''une maison individuelle avec piscine', 'réhabilitation maison individuelle arsac avec construction piscine', 'nagar33', 5, 4),
('Réhabilitation maison individuelle en 2 logements locatifs. Relevé de l''existant, conception de la rénovation, de l''extension et l''aménagement des combles', 'réhabilitation maison individuelle en  2 logements locatifs isle saint georges', 'nagar33', 3, 4),
('Réalisation d''une piscine, plans projet et projection', 'rénovation d''un appartement','laur33', 1,2);

--Photo
INSERT INTO photo(lien, nom, categorie, projet_id) VALUES
('https://www.dropbox.com/s/tfizlynee8y5yvt/carousel.jpeg?dl=1', 'plans', 'caroussel', null ),
('https://www.dropbox.com/s/bj39us6woanjwx1/IMG_1707.jpg?dl=1', 'photo', 'caroussel', null ),
('https://www.dropbox.com/s/hl84s7m61lk4sxh/image%20croquis%203.JPG?dl=1', 'croquis', 'caroussel', null ),
('https://www.dropbox.com/s/ncrf539tokg6yla/MAISON%20INDIVIDUELLE%20%20MERIGNAC%20EXTENSION.JPG?dl=1', 'maison mérignac avant', 'accueil', 1),
('https://www.dropbox.com/s/jciuh2iw2fosjz4/MAISON%20INDIVIDUELLE%20%20MERIGNAC%20EXTENSION1.JPG?dl=1', 'maison mérignac projection','projet', 1),
('https://www.dropbox.com/s/mduead7vifr36pd/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR1.JPG?dl=1', 'appartement mérignac avant', 'accueil', 2),
('https://www.dropbox.com/s/oliynakxmc01exm/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR0.JPG?dl=1', 'appartement mérignac avant', 'projet', 2),
('https://www.dropbox.com/s/wh6llqfg2vgu70s/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR.JPG?dl=1', 'appartement mérignac après', 'projet', 2),
('https://www.dropbox.com/s/0qmofrpa99v5xcr/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR2.JPG?dl=1', 'appartement mérignac après', 'projet', 2),
('https://www.dropbox.com/s/fzifv3op5hgcvbc/CABINET%20MEDICAL%20PESSAC%20EXTENSION.JPG?dl=1', 'cabinet médical pessac avant', 'accueil', 3),
('https://www.dropbox.com/s/n9p1375z28149if/CABINET%20MEDICAL%20PESSAC%20EXTENSION2.JPG?dl=1', 'cabinet médical pessac photosynthèse', 'projet', 3),
('https://www.dropbox.com/s/a7ke7g6zbqqj2h5/MAISON%20INDIVIDUELLE%20MERIGNAC%20PISCINE.JPG?dl=1', 'maison mérignac piscine avant', 'accueil', 4),
('https://www.dropbox.com/s/1rw0w5ksgenvygz/MAISON%20INDIVIDUELLE%20MERIGNAC%20PISCINE1.JPG?dl=1', 'maison mérignac piscine projection', 'projet', 4),
('https://www.dropbox.com/s/fuszcj5v37un4bf/LOGEMENTS%20LOCATIFS%20%20CASTELNAU%20DE%20MEDOC%20CONSTRUCTION.JPG?dl=1', 'logements castelneau avant', 'accueil', 5),
('https://www.dropbox.com/s/w1ch21bb10jx9g0/LOGEMENTS%20LOCATIFS%20%20CASTELNAU%20DE%20MEDOC%20CONSTRUCTION1.JPG?dl=1', 'logements castelneau plans', 'projet', 5),
('https://www.dropbox.com/s/kyh5qnlgncgloaw/MAISON%20INDIVIDUELLE%20MERIGNAC%20EXTENSION2.JPG?dl=1', 'maison mérignac extension projection', 'accueil', 6),
('https://www.dropbox.com/s/yhogiulp5j03zvx/MAISON%20INDIVIDUELLE%20MERIGNAC%20EXTENSION1.JPG?dl=1', 'maison mérignac extension projection', 'projet', 6),
('https://www.dropbox.com/s/osbovieel41ex8s/MAISON%20INDIVIDUELLE%20ARSAC%20REHABILITATION.JPG?dl=1', 'maison arsac avant', 'accueil', 7),
('https://www.dropbox.com/s/pv9919245hdvq13/PROJET%204%20RENDU%20AMENAGEMENT.jpg?dl=1', 'maison arsac projection', 'projet', 7),
('https://www.dropbox.com/s/bt31xgvi8wsdk19/MAISON%20INDIVIDUELLE%20ISLE%20SAINT%20GEOGES%20REHABILITATION%202%20LOGEMENTS.JPG?dl=1', 'maison isle st georges avant', 'accueil', 8),
('https://www.dropbox.com/s/guvo26lwax2lizu/plan-maison-plans.fr_.jpg?dl=1', 'maisonpiscine', 'accueil', 9);



--Prestation_liste_projets
INSERT INTO prestation_liste_projets(prestations_id, liste_projets_id) VALUES
(1, 1),
(2, 3),
(3, 2),
(1, 4),
(2, 5),
(3, 5),
(7, 4);




