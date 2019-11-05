-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 02 juin 2019 à 17:35
-- Version du serveur :  10.1.40-MariaDB
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `cap2019`
--

-- --------------------------------------------------------

--
-- Structure de la table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `emp_name` varchar(50) NOT NULL,
  `emp_psw` varchar(100) NOT NULL,
  `emp_profession` varchar(50) NOT NULL,
  `emp_idF` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employees`
--

INSERT INTO `employees` (`id`, `emp_name`, `emp_psw`, `emp_profession`, `emp_idF`) VALUES
(-1, 'Guest', 'guest', '', ''),
(1, 'Lewis', 'lewis123', 'director', 'DIR'),
(2, 'Esteban', 'esteban123', 'senior executives', 'SEN-1'),
(3, 'Francis', 'francis123', 'senior executives', 'SEN-2'),
(4, 'Yann', 'yann123', 'network engineer', 'NET'),
(5, 'Chloé', 'chloe123', 'developers', 'DEV-A1'),
(6, 'Paul ', 'paul123', 'developers', 'DEV-A2'),
(7, 'Franck', 'franck123', 'developers', 'DEV-A3'),
(8, 'Marie', 'marie123', 'developers', 'DEV-A4'),
(9, 'Stephane', 'stephane123', 'developers', 'DEV-A5'),
(10, 'Karim', 'karim123', 'developers', 'DEV-B1'),
(11, 'Hamza', 'hamza123', 'developers', 'DEV-B2'),
(12, 'Leila', 'leila123', 'developers', 'DEV-B3'),
(13, 'Mamadou', 'mamadou123', 'developers', 'DEV-B4'),
(14, 'Donald', 'donald123', 'developers', 'DEV-B5'),
(15, 'Jean', 'jean123', 'developers', 'DEV-B6'),
(16, 'Marise', 'marise123', 'developers', 'DEV-B7'),
(17, 'Esther', 'esther123', 'secretary', 'SEC');

-- --------------------------------------------------------

--
-- Structure de la table `equipments`
--

CREATE TABLE `equipments` (
  `id` int(11) NOT NULL,
  `equipment_type` varchar(100) NOT NULL,
  `ipv4` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `equipments`
--

INSERT INTO `equipments` (`id`, `equipment_type`, `ipv4`) VALUES
(1, 'PC', '192.168.1.2'),
(2, 'PC', '192.168.1.3'),
(3, 'PC', '192.168.1.4'),
(4, 'PC', '192.168.1.5'),
(5, 'PC', '192.168.1.6'),
(6, 'PC', '192.168.1.7'),
(7, 'PC', '192.168.1.8'),
(8, 'PC', '192.168.1.9'),
(9, 'PC', '192.168.1.10'),
(10, 'PC', '192.168.1.11'),
(11, 'PC', '192.168.1.12'),
(12, 'PC', '192.168.1.13'),
(13, 'PC', '192.168.1.14'),
(14, 'PC', '192.168.1.15'),
(15, 'PC', '192.168.1.16'),
(16, 'PC', '192.168.1.17'),
(17, 'PC', '192.168.1.18'),
(18, 'PC', '192.168.1.19'),
(19, 'PC', '192.168.1.20'),
(20, 'PC', '192.168.1.21'),
(21, 'Printer', '192.168.1.22'),
(22, 'Printer', '192.168.1.23'),
(23, 'Video Projector', '192.168.1.24'),
(24, 'Server', '192.168.1.25'),
(25, 'Time Clock', '192.168.1.26');

-- --------------------------------------------------------

--
-- Structure de la table `ipconfig`
--

CREATE TABLE `ipconfig` (
  `id` int(11) NOT NULL,
  `subnet_mask` varchar(50) NOT NULL,
  `default_gateway` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ipconfig`
--

INSERT INTO `ipconfig` (`id`, `subnet_mask`, `default_gateway`) VALUES
(1, '192.168.1.1', '255.255.255.0');

-- --------------------------------------------------------

--
-- Structure de la table `meeting_rooms`
--

CREATE TABLE `meeting_rooms` (
  `id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `booking_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `meeting_rooms`
--

INSERT INTO `meeting_rooms` (`id`, `room_id`, `emp_id`, `booking_time`) VALUES
(2, 1, 6, '10am - 12am'),
(3, 1, 6, '10am - 12am');

-- --------------------------------------------------------

--
-- Structure de la table `messaging`
--

CREATE TABLE `messaging` (
  `id` int(11) NOT NULL,
  `name_exp` varchar(50) NOT NULL,
  `name_dest` varchar(50) NOT NULL,
  `object` varchar(100) NOT NULL,
  `message` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `nb_places` int(11) NOT NULL,
  `available` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rooms`
--

INSERT INTO `rooms` (`id`, `room_type`, `nb_places`, `available`) VALUES
(1, 'open space', 25, 0),
(2, 'executive office', 1, 1),
(3, 'executive management office', 3, 1),
(4, 'secretarial office', 2, 1),
(5, 'meeting room', 10, 1),
(6, 'network room', 3, 1),
(7, 'dining room', 30, 1),
(8, 'entrance_door', -1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `used_eqp_emp`
--

CREATE TABLE `used_eqp_emp` (
  `id` int(11) NOT NULL,
  `eqp_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `used_eqp_emp`
--

INSERT INTO `used_eqp_emp` (`id`, `eqp_id`, `emp_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 17),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12),
(13, 13, 13),
(14, 14, 14),
(15, 15, 15),
(16, 16, 16),
(17, 17, 4),
(18, 18, 4),
(19, 19, -1),
(20, 20, -1),
(21, 21, 5),
(22, 21, 6),
(23, 21, 7),
(24, 21, 8),
(25, 21, 9),
(26, 21, 10),
(27, 21, 11),
(28, 21, 12),
(29, 21, 13),
(30, 21, 14),
(31, 21, 15),
(32, 21, 16),
(33, 21, 17),
(34, 21, 2),
(35, 21, 3),
(36, 22, 5),
(37, 22, 6),
(38, 22, 7),
(39, 22, 8),
(40, 22, 9),
(41, 22, 10),
(42, 22, 11),
(43, 22, 12),
(44, 22, 13),
(45, 22, 14),
(46, 22, 15),
(47, 22, 16),
(48, 22, 17),
(49, 22, 2),
(50, 22, 3);

-- --------------------------------------------------------

--
-- Structure de la table `used_eqp_room`
--

CREATE TABLE `used_eqp_room` (
  `id` int(11) NOT NULL,
  `eqp_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `used_eqp_room`
--

INSERT INTO `used_eqp_room` (`id`, `eqp_id`, `room_id`) VALUES
(1, 23, 5),
(2, 25, 8);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `equipments`
--
ALTER TABLE `equipments`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ipconfig`
--
ALTER TABLE `ipconfig`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `meeting_rooms`
--
ALTER TABLE `meeting_rooms`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `messaging`
--
ALTER TABLE `messaging`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `used_eqp_emp`
--
ALTER TABLE `used_eqp_emp`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `used_eqp_room`
--
ALTER TABLE `used_eqp_room`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `equipments`
--
ALTER TABLE `equipments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `ipconfig`
--
ALTER TABLE `ipconfig`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `meeting_rooms`
--
ALTER TABLE `meeting_rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `messaging`
--
ALTER TABLE `messaging`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `used_eqp_emp`
--
ALTER TABLE `used_eqp_emp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `used_eqp_room`
--
ALTER TABLE `used_eqp_room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
