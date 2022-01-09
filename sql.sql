-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 09 jan. 2022 à 00:13
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `anticovid`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `heure_debut` time NOT NULL,
  `heure_fin` time NOT NULL,
  `id_lieu` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`id`, `nom`, `date`, `heure_debut`, `heure_fin`, `id_lieu`, `id_user`) VALUES
(9, 'Test', '2021-12-07', '15:00:00', '16:00:00', 2, 1),
(6, 'Prendre un cafe', '2021-12-13', '16:00:00', '16:30:00', 2, 1),
(8, 'Coiffeur', '2021-12-14', '15:00:00', '16:00:00', 5, 1),
(10, 'Test2', '2021-12-20', '06:00:00', '07:00:00', 5, 1),
(11, 'Test3', '2021-11-09', '08:00:00', '12:00:00', 5, 1),
(13, 'Pause', '2021-12-13', '16:20:00', '16:50:00', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `amis`
--

DROP TABLE IF EXISTS `amis`;
CREATE TABLE IF NOT EXISTS `amis` (
  `id_user1` int(10) NOT NULL,
  `id_user2` int(10) NOT NULL,
  PRIMARY KEY (`id_user1`,`id_user2`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `amis`
--

INSERT INTO `amis` (`id_user1`, `id_user2`) VALUES
(1, 2),
(2, 3),
(9, 1);

-- --------------------------------------------------------

--
-- Structure de la table `demande_ami`
--

DROP TABLE IF EXISTS `demande_ami`;
CREATE TABLE IF NOT EXISTS `demande_ami` (
  `id_notification` int(10) NOT NULL,
  `accepte` tinyint(1) NOT NULL,
  `refuse` tinyint(1) NOT NULL,
  `id_user_src` int(10) NOT NULL,
  PRIMARY KEY (`id_notification`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `code_postal` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id`, `nom`, `adresse`, `code_postal`) VALUES
(6, 'Magasin', '362 boulevard joffre', 54000),
(2, 'Terrasse', '8 rue des terrasses', 54500),
(5, 'Coiffeur', '9 rue des terrasses', 54500);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) NOT NULL,
  `message` varchar(500) NOT NULL,
  `type` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `id_user`, `message`, `type`) VALUES
(14, 2, 'Test Test vous a retiré de ses amis.', 2),
(32, 4, 'Test Test vous a retiré de ses amis.', 2),
(13, 2, 'Vous avez accepté la demande d ami de Test Test.', 2),
(17, 1, 'Admin Admin a accepté votre demande d ami.', 2),
(18, 2, 'Vous avez accepté la demande d ami de Test Test.', 2),
(22, 2, 'Test Tests est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(20, 2, 'Jean Test a accepté votre demande d ami.', 2),
(21, 3, 'Vous avez accepté la demande d ami de Admin Admin.', 2),
(23, 4, 'Vous avez été au contact d une personne positive au Covid lors de votre activité Plongee le 2021-11-11. Veuillez vous faire tester.', 2),
(27, 4, 'Test Tests est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(25, 1, 'Paul Legros a accepté votre demande d ami.', 2),
(26, 4, 'Vous avez accepté la demande d ami de Test Tests.', 2),
(28, 4, 'Vous avez été au contact d une personne positive au Covid lors de votre activité Plongee le 2021-11-11. Veuillez vous faire tester.', 2),
(36, 3, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(30, 1, 'Vous avez refuse la demande d ami de Paul Legros.', 2),
(33, 3, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(34, 9, 'Test Test a accepté votre demande d ami.', 2),
(35, 1, 'Vous avez accepté la demande d ami de Photo Photo.', 2),
(37, 3, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(38, 1, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(39, 3, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(40, 1, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(41, 3, 'Admin Admin est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.', 2),
(42, 1, 'Vous avez été au contact d une personne positive au Covid lors de votre activité Prendre un cafe le 2021-12-13. Veuillez vous faire tester.', 2);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `naissance` date NOT NULL,
  `role` varchar(10) NOT NULL,
  `a_covid` tinyint(1) NOT NULL,
  `url_photo` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`,`login`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `nom`, `prenom`, `naissance`, `role`, `a_covid`, `url_photo`) VALUES
(1, 'Test', 'Testtest1', 'Test', 'Test', '1970-01-03', 'basic_user', 0, 'https://www.apprendre-en-ligne.net/info/images/lena.gif'),
(2, 'admin', 'Adminmin1', 'Admin', 'Admin', '2021-09-07', 'admin', 0, 'https://www.f-legrand.fr/scidoc/figures/image/niveaux/images/imA.png'),
(3, 'Test2', 'Testtest2', 'Test', 'Jean', '1984-01-01', 'basic_user', 0, 'https://www.f-legrand.fr/scidoc/figures/image/niveaux/images/imA.png'),
(4, 'PaulPOP', 'Testtest1', 'Legros', 'Paul', '1984-03-16', 'basic_user', 0, 'https://www.f-legrand.fr/scidoc/figures/image/niveaux/images/imA.png'),
(8, 'Suppp', 'Testtest1', 'suuuuu', 'suuuuuu', '1970-01-01', 'basic_user', 0, 'null'),
(9, 'Photo', 'Testtest1', 'Photo', 'Photo', '1970-01-01', 'basic_user', 0, 'https://www.media.pokekalos.fr/img/pokemon/sprites/dp2/465.png'),
(10, 'SansPhoto', 'Testtest1', 'SansPhoto', 'SansPhoto', '1970-01-01', 'basic_user', 0, 'https://cdn-icons-png.flaticon.com/512/64/64572.png');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
