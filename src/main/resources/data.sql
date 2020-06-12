--Admin
INSERT INTO admin(login, compte_valide, password, photo, presentation, role, prenom) VALUES
( 'nagar33',true, '$2y$10$QRASxzQWKUdzWTbTAFrZIOm5AqvCRBmWI6UHRAshWs.PTlC39bWjO', 'https://www.dropbox.com/s/65o0j4dfhwkg1rx/photo%20Nad%C3%A8g.jpg?dl=1',
'Passionnée par l''architecture et la construction, j''ai entrepris en 2019 une orientation professionnelle vouée au bâtiment. J''ai suivi des formations de dessin en batîment, et suis diplômée d''un titre professionnel de Technicien d''études du bâtiment - Dessin de projets (2019). Les enjeux environnementaux actuels et futurs étant primordiaux, construire doit être un acte responsable, aussi je m''attache à concevoir dans une démarche bioclimatique. Je considère également le design comme primordial.
La conception architecturale, tant fonctionnelle qu''esthétique doit aussi être initialement parfaitement pensée. J''exerce mon activité de dessinateur principalement sur le logiciel Autocad mais suis également compétente sur d''autres logiciels d''architecture sur lesquels j''ai aussi été formée.', 'ADMIN', 'Nadège'),
( 'laur33', true, '$2y$10$r0TSuVjApcOziqVm/izA3OLvtLJjm7Dsh7HzKyEqHWyyGfU6aRIWa', 'https://www.dropbox.com/s/1wuvh05xq9uh7jf/Laurence%20portrait%201%20NB.jpeg?dl=1', 'Architecte depuis plus de 15 ans, j''ai tout au long de mon parcours professionnel géré des projets de tailles et natures différentes (santé, industrie, tertiaire, éducation, etc.).
Mon expérience d’architecte est complète : avant tout femme de terrain, je connais sur le bout des doigts les problématiques de la gestion du projet architectural pour l’ensemble des phases, de la création architecturale, jusqu’à sa concrétisation.
En perpétuelle remise en question, c’est dans l’exercice de mon métier que naît l’envie d’exploiter de nouveaux outils (visualisation), et c’est dans la volonté de transmettre et de combiner compétence et originalité que naît celle de faire vivre le partenariat LNProjet. Aussi, mes conceptions sont principalement réalisées via Revit et Autocad.','ADMIN', 'Laurence');

--Client
INSERT INTO client(nom, prenom, adresse, code_postal, ville, email, telephone, ref_devis, ref_facture) VALUES
( 'Dupont', 'Claude', 'Avenue de la liberté', '33000', 'Bordeaux', 'monadresse1@email.com', '05.56.59.84.78.', 'DEV1', 'FAC1'),
( 'Louis', 'Léon', 'Boulevard de la nation', '33200', 'Caudéran', 'monadresse2@email.com', '06.66.59.84.78.', 'DEV2', 'FAC2'),
( 'Durand', 'Etienne', 'Rue de la paix', '33340', 'Lesparre', 'monadresse3@email.com', '07.76.59.84.78.', 'DEV3', 'FAC3'),
( 'Gatet', 'Laurent', 'Parvis des remparts', '33100', 'Bordeaux', 'monadresse4@email.com', '06.85.14.63.08.', 'DEV4', 'FAC4'),
( 'Habdou', 'Said', '11 rue Charles Lamoureux', '33400', 'Pessac', 'monadresse5@email.com', '05.56.66.48.23.', 'DEV5', 'FAC5');

--Message
INSERT INTO message(objet, contenu, vu,  date, client_id, statut_client) VALUES
('Demande de devis', 'Bonjour, je me permets de vous contacter car j''ai un projet de réaménegement pour ma résidence principale. Je souhaiterais avoir un devis pour la ralisation de plans2D et maquette 3D pour avoir une idée du rendu. Vous en remerciant par avance, Mme Tournesol Elsa', true, '2020-01-15', 3, false ),
('Offre de partenariat', 'Nous souhaiterions vous rencontrer afin de vous proposer un partenariat en vous confiant certains travaux de dessin demandés par nos clients. Merci de bien vouloir prendre contact avec nous si cela pouvait vous inteéresser.Cordiaement, Le cabinet d''étude Malom', false, '2020-03-18', 1, false ),
('Demande de renseignements', 'Bonjour, mon épouse et moi avons un projet d''agrandissement et nous souhaiterions au préalable réaliser une étude de projet. Merci de bien vouloir nous recontacter. Mr Léonard Jackie' , true, '2020-03-03', 2, false ),
('Demande de devis', 'Bonjour, je souhaiterais obtenir des informations pour un projet de piscine. Mr Soliur', false, '2020-05-15', null, false ),
('Renseignements', 'Nous souhaiterions rénover une grange. Merci de bien vouloir prendre contact nous afin que nous puissions vous exposer notre projet. Mr et Mme JABAM', true, '2020-02-18', null, false ),
('Demande de tarifs', 'Bonjour, quels seraient vos tarifs pour le réaménagement d''un appartement, merci Mr LOZERRE' , false, '2020-06-03', 2, false );


--Prestation
INSERT INTO prestation(intitule, categorie) VALUES
('Analyse PLU', 'Autorsation d''urbanisme'),
('Faisabilité', 'Autorsation d''urbanisme'),
('Permis de Construire (PC)', 'Autorsation d''urbanisme'),
('Déclaration Préalable (DP)', 'Autorsation d''urbanisme'),
('Relevé', 'Conception'),
('Etat des lieux', 'Conception'),
('Etude de projet', 'Conception'),
('Aménagement intérieur', 'Conception'),
('Accessibilité PMR', 'Conception'),
('P 2D', 'Dessin'),
('Maquette 3D', 'Dessin'),
('Images 3D photo réaliste', 'Dessin'),
('Animation', 'Dessin');

--Type
INSERT INTO type(libelle) VALUES
('Aménagements intérieur'),
('Extensions'),
('Rénovations'),
('Réhabilitations'),
('Piscines');

--Projet
INSERT INTO projet(description, intitule, admin_login, client_id, type_id) VALUES
('Réaménagement et création d’une extension. Le projet consistait à étendre une maison d''habitation en créant un salon et en réaménageant l''espace salle à manger bureau, mais aussi un agréable petit coin pour le chat. L''écriture de l''extension se fait donc très simple : un volume toit terrasse, plus contemporain, qui vient se glisser sous l''avant-toit de la maison existante. Cet agrandissement sera un prolongement de la pièce à vivre existante pour y aménager un espace salle à manger, salon et bureau. Un bardage rendra uniforme l''existant et l''extension. Surface : 17m²', 'Extension d''une maison individuelle – MERIGNAC (33)', 'nagar33', 1, 2),
('Agrandir l’existant pour créer une nouvelle entrée et espace bureau. Le projet consistait à agrandir pour réaménager les espaces tout en gardant le style de l''existant. ', 'Extension d''une maison d’habitation - PESSAC (33)', 'nagar33', 1, 2),
('Une recherche d''espace supplémentaire. Le projet consistait à réaménager le rez-de-chaussée d''une maison existante tout en créant une extension avec étage. Ce nouveau volume a été conçu dans la continuité du volume existant.', 'Réaménagement et extension d''une maison d’habitation – PESSAC (33)', 'nagar33', 4, 1),
('Délimiter les espaces et créer du rangement avec un espace bureau. Le projet consistait à revoir l''aménagement de ce studio en créant un bel espace cuisine, un coin salon et bureau, avec un maximum de rangement. Le tout dans un souci éco-responsable en réutilisant au maximum le mobilier existant ou le trouver sur le marché de l’occasion.' , 'Réaménagement d''un studio – MERIGNAC (33)', 'nagar33', 2, 1),
('Créer une piscine de 10.00m x 3.00m. Création d''une piscine maçonnée, dossier Déclaration Préalable à faire pour la Mairie. ', 'Construction d''une piscine – SAINTE-HELENE (33)','laur33', 1,5),
('Le projet consistait à réaliser le dossier de Déclaration Préalable pour la mairie, pour la construction d''une piscine maçonnée de 6.00m x 3.00m.', 'Construction d''une piscine Moulis en Médoc (33)', 'laur33', 3, 5),
('Transformer un garage en 2 logements pour la location aux Normes PMR (Personnes à Mobilité Réduite). Le projet consistait à reprendre une partie d''un existant et réaliser 2 appartements (T3 et T4) tout en respectant les contraintes du PLU et les Normes de l''accessibilité. Un des objectifs de ces appartements était de pouvoir les rendre indépendants en cas de vente. 1 Appartement de type 3 de 60m² et 1 appartement de type 4 de 78m²', 'DOSSIER PC - Construction de 2 logements locatifs – Castelnau de Médoc (33)', 'laur33', 3, 3),
('Le projet consistait à réaliser le dossier de Déclaration Préalable pour la mairie, pour la construction d’une piscine maçonnée de 7.00m x 3.00m.', 'DOSSIER DP – Construction d''une piscine MERIGNAC (33)', 'laur33', 4, 5),
('Un réaménagement complet avec un bel espace de vie et de détente. Le projet consistait à réaménager entièrement la maison existante et créer un espace détente dans le petit jardin. Une terrasse couverte propose une prolongation de la pièce de vie.La volonté était de créer avec 2 chambres tout confort et un bel espace de vie. Surface : 110m²', 'Réaménagement d''une échoppe – Arsac (33)', 'nagar33', 5, 1);

--Photo
INSERT INTO photo(lien, nom, categorie, projet_id) VALUES
('https://www.dropbox.com/s/bj39us6woanjwx1/IMG_1707.jpg?dl=1', 'photo', 'caroussel', null ),
('https://www.dropbox.com/s/bu2tdi21udc5tyu/image%20croquis%203%20%281%29.JPG?dl=1', 'croquis', 'caroussel', null ),
('https://www.dropbox.com/s/tfizlynee8y5yvt/carousel.jpeg?dl=1', 'plans', 'caroussel', null ),
('https://www.dropbox.com/s/kyh5qnlgncgloaw/MAISON%20INDIVIDUELLE%20MERIGNAC%20EXTENSION2.JPG?dl=1', 'Extension d''une maison d''habitation - Mérignac (33)', 'accueil', 1),
('https://www.dropbox.com/s/mhba9cp5rrex8c5/Image%2006.png?dl=1', 'Extension d''une maison d''habitation - Mérignac (33)', 'projet', 1),
('https://www.dropbox.com/s/rzvludnqiex0l64/Image%2007.png?dl=1', 'Extension d''une maison d''habitation - Mérignac (33)', 'projet', 1),
('https://www.dropbox.com/s/cj8erjya4nyc000/photo%20nord%20DP5.JPG?dl=1', 'Extension d''une maison d''habitation - Mérignac (33)', 'projet', 1),
('https://www.dropbox.com/s/yhogiulp5j03zvx/MAISON%20INDIVIDUELLE%20MERIGNAC%20EXTENSION1.JPG?dl=1', 'Extension d''une maison d''habitation - Mérignac (33)', 'projet', 1),
('https://www.dropbox.com/s/ncrf539tokg6yla/MAISON%20INDIVIDUELLE%20%20MERIGNAC%20EXTENSION.JPG?dl=1', 'Extension d''une maison d''habitation - Pessac (33)', 'projet', 2),
('https://www.dropbox.com/s/jciuh2iw2fosjz4/MAISON%20INDIVIDUELLE%20%20MERIGNAC%20EXTENSION1.JPG?dl=1', 'Extension d''une maison d''habitation - Pessac (33)','accueil', 2),
('https://www.dropbox.com/s/kpt6jppek6m2r17/modele%203d.JPG?dl=1', 'Extension d''une maison d''habitation - Pessac (33)','projet', 2),
('https://www.dropbox.com/s/7haeez8gato7fys/PHOTO%20APRES.JPG?dl=1', 'Réaménagement et extension d''une maison d''habitation – PESSAC (33)', 'accueil', 3),
('https://www.dropbox.com/s/swnxzyikzfkuk9r/PLANS%20RDC.JPG?dl=1', 'Réaménagement et extension d''une maison d''habitation – PESSAC (33)', 'projet', 3),
('https://www.dropbox.com/s/pthhohbcspvmmr7/FACADE%20RUE.JPG?dl=1', 'Réaménagement et extension d''une maison d''habitation – PESSAC (33)', 'projet', 3),
('https://www.dropbox.com/s/5llj9y2zvd3c02a/FACADE%20RUE%20AVANT.JPG?dl=1', 'Réaménagement et extension d''une maison d''habitation – PESSAC (33)', 'projet', 3),
('https://www.dropbox.com/s/0qmofrpa99v5xcr/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR2.JPG?dl=1', 'appartement mérignac après', 'accueil', 4),
('https://www.dropbox.com/s/mduead7vifr36pd/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR1.JPG?dl=1', 'appartement mérignac après', 'projet', 4),
('https://www.dropbox.com/s/5uuep5rctreokaj/IMG_1295.JPG?dl=1', 'appartement mérignac après', 'projet', 4),
('https://www.dropbox.com/s/yu0uome8t8zx3qa/plan%20am%C3%A9nagement.JPG?dl=1', 'appartement mérignac plan', 'projet', 4),
('https://www.dropbox.com/s/wh6llqfg2vgu70s/APPARTEMENT%20INDIVIDUEL%20AMENAGEMENT%20INTERIEUR.JPG?dl=1', 'appartement mérignac avant', 'projet', 4),
('https://www.dropbox.com/s/66miq6zk92zio4q/IMG_1303.JPG?dl=1', 'appartement mérignac avant', 'projet', 4),
('https://www.dropbox.com/s/lp037g6oiyh4754/insertion%20paysag%C3%A8re.JPG?dl=1', 'Construction piscine - STE-HELENE (33)', 'accueil', 5),
('https://www.dropbox.com/s/eurkeoq6j10zqlf/PHOTO%20GOOGLE%20EARTH.JPG?dl=1', 'Construction piscine - STE-HELENE (33)', 'projet', 5),
('https://www.dropbox.com/s/zeehl52k196ytul/PLAN%20PROJET.JPG?dl=1', 'Construction piscine - STE-HELENE (33)', 'projet', 5),
('https://www.dropbox.com/s/7656lge8g72acsp/PIECE%20GRAPHIQUE%20DP.JPG?dl=1', 'maison moulis piscine projection', 'projet', 6),
('https://www.dropbox.com/s/ld4s2k5lm1iq6lv/PIECE%20PLAN%20DE%20MASSE%20DP.JPG?dl=1', 'maison moulis piscine projection', 'projet', 6),
('https://www.dropbox.com/s/lwj3bo4kb5zomej/MAISON%20INDIVIDUELLE%20MOULIS%20PISCINE.JPG?dl=1', 'maison mérignac piscine avant', 'accueil', 6),
('https://www.dropbox.com/s/j6e15q08jdtggp7/DOSSIER%20PC%20PIECE%20GRAPHIQUE.JPG?dl=1', 'logements castelneau 3D', 'projet', 7),
('https://www.dropbox.com/s/ax0bhr2lfoot18l/DOSSIER%20PC%20PIECE%20FACADES.JPG?dl=1', 'logements castelneau plans', 'accueil', 7),
('https://www.dropbox.com/s/c2ece5gtsyhji45/DOSSIER%20PC%20PMR.JPG?dl=1', 'logements castelneau plans', 'projet', 7),
('https://www.dropbox.com/s/fuszcj5v37un4bf/LOGEMENTS%20LOCATIFS%20%20CASTELNAU%20DE%20MEDOC%20CONSTRUCTION.JPG?dl=1', 'logements castelneau avant', 'projet', 7),
('https://www.dropbox.com/s/hta7fugy5f7o46x/DP%20PLANS%20MASSE.JPG?dl=1', 'DP piscine', 'projet', 8),
('https://www.dropbox.com/s/ihxoz169g8jsj85/DP%20PIECE%20GRAPHIQUE.JPG?dl=1', 'DP piscine', 'accueil', 8),
('https://www.dropbox.com/s/lowac8quthu67pk/DP%20COUPES.JPG?dl=1', 'DP piscine', 'projet', 8),
('https://www.dropbox.com/s/zwb9eq30sbkg9g0/MAISON%20INDIVIDUELLE%20ARSAC%20REHABILITATION2.JPG?dl=1', 'maison arsac projection', 'accueil', 9),
('https://www.dropbox.com/s/pv9919245hdvq13/PROJET%204%20RENDU%20AMENAGEMENT.jpg?dl=1', 'maison arsac projection', 'projet', 9),
('https://www.dropbox.com/s/976dh8nhiiwhy9v/MAISON%20INDIVIDUELLE%20ARSAC%20REHABILITATION1.JPG?dl=1', 'maison arsac plan', 'projet', 9),
('https://www.dropbox.com/s/875l3o5b00pffqf/IMG_3039.jpeg?dl=1', 'maison arsac plan', 'projet', 9),
('https://www.dropbox.com/s/osbovieel41ex8s/MAISON%20INDIVIDUELLE%20ARSAC%20REHABILITATION.JPG?dl=1', 'maison arsac avant', 'projet', 9);



--Prestation_liste_projets
INSERT INTO prestation_liste_projets(prestations_id, liste_projets_id) VALUES
(1, 1),
(2, 3),
(3, 2),
(1, 4),
(2, 5),
(3, 5),
(7, 4);




