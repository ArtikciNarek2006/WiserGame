-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2023 at 11:55 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wiserweb_db`
--
CREATE DATABASE IF NOT EXISTS `wiserweb_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `wiserweb_db`;

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `text` text NOT NULL DEFAULT 'No Answer TEXT in DB',
  `media_type` enum('AUDIO','VIDEO','IMG') DEFAULT NULL,
  `media_file` varchar(50) DEFAULT NULL,
  `audio_volume` int(11) NOT NULL DEFAULT 100
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `text`, `media_type`, `media_file`, `audio_volume`) VALUES
(2, 'Answer TEST MEDIA video', 'VIDEO', '1.mp4', 100),
(3, 'Answer TEST MEDIA audio', 'AUDIO', '1.wav', 50),
(5, 'Answer TEST MEDIA img VERTICAL', 'IMG', 'v.png', 50),
(6, 'Answer TEST MEDIA img VERTICAL LONG TEXT :Answer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXT', 'IMG', 'v.png', 50),
(7, 'Answer TEST MEDIA img VERTICAL LONG TEXT 2 :Answer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXTAnswer TEST MEDIA img VERTICAL LONG TEXT', 'IMG', 'v.png', 50),
(100, 'Answer TEST MEDIA img LANDSCAPE', 'IMG', 'l.png', 50),
(101, 'Answer TEST no media', NULL, NULL, 100);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Monika_TEAM'),
(2, 'Laura_TEAM'),
(3, 'Irina_TEAM');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `text` text NOT NULL DEFAULT 'No Question TEXT in DB',
  `media_type` enum('AUDIO','VIDEO','IMG') DEFAULT NULL,
  `media_file` varchar(50) DEFAULT NULL,
  `media_volume` int(11) NOT NULL DEFAULT 100,
  `category_id` int(11) NOT NULL,
  `answer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `text`, `media_type`, `media_file`, `media_volume`, `category_id`, `answer_id`) VALUES
(1, 'TEST no media no answer', NULL, NULL, 100, 1, NULL),
(2, 'TEST MEDIA video NO ANSWER', 'VIDEO', '1.mp4', 50, 1, NULL),
(3, 'TEST MEDIA audio NO ANSWER', 'AUDIO', '1.wav', 50, 1, NULL),
(4, 'TEST MEDIA img LANDSCAPE NO ANSWER', 'IMG', 'l.png', 100, 1, NULL),
(5, 'TEST MEDIA img VERTICAL NO ANSWER', 'IMG', 'v.png', 100, 1, NULL),
(6, 'TEST no media with answer(100)', NULL, NULL, 100, 2, 100),
(7, 'TEST MEDIA video with answer(100)', 'VIDEO', '1.mp4', 50, 2, 100),
(8, 'TEST MEDIA audio with answer(100)', 'AUDIO', '1.wav', 50, 2, 100),
(9, 'TEST MEDIA img LANDSCAPE with answer(100)', 'IMG', 'l.png', 50, 2, 100),
(10, 'TEST MEDIA img VERTICAL with answer(100)', 'IMG', 'v.png', 50, 2, 100),
(11, 'TEST MEDIA AUDIO MAX VOL with answer(100)', 'AUDIO', '1.wav', 100, 2, 100),
(12, 'TEST MEDIA AUDIO MAX VOL with answer(101)', 'AUDIO', '1.wav', 100, 2, 101);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
