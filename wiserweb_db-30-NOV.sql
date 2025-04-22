-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2023 at 07:25 PM
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
(1, 'Alek Manukyan', 'IMG', 'Laura/1.jpeg', 100),
(2, 'Kirk Kerkoryan', 'IMG', 'Irina/2.jpeg', 100),
(3, 'Arshile Gorky', 'IMG', 'Monika/3.jpg', 100),
(4, 'Alain Marie Pascal Prost', 'IMG', 'Meri/4.webp', 100),
(5, 'Wiliam Saroyan', 'IMG', 'Laura/5.jpeg', 100),
(6, 'Gerard Leon Gafesjian', 'IMG', 'Irina/6.jpeg', 100),
(7, 'Cher', 'IMG', 'Monika/7.webp', 100),
(8, 'Asatour Sarafian (Oscar H. Banker)', 'IMG', 'Meri/8.png', 100),
(9, 'Yuri Oganessian', 'IMG', 'Laura/9.jpeg', 100),
(10, 'Flora Zabelle', 'IMG', 'Irina/10.jpeg', 100),
(11, 'Serj Tankian', 'IMG', 'Monika/11.jpg', 100),
(12, 'Հովհաննես Ղուկասյանը (Jan Józef Ignacy Łukasiewicz)', 'IMG', 'Meri/12.jpg', 100),
(13, 'Christopher Ter Hovhanisyan', 'IMG', 'Laura/13.jpeg', 100),
(14, 'Kim Kartashyan', 'IMG', 'Irina/14.jpeg', 100),
(15, 'Howard Kazanjian', 'IMG', 'Monika/15.webp', 100),
(16, 'Eduardo Eurnekian', 'IMG', 'Meri/16.jpeg', 100),
(17, 'Luther George Simijian', 'IMG', 'Laura/17.jpeg', 100),
(18, 'Ruben Mamulyan', 'IMG', 'Irina/18.jpg', 100),
(19, 'Alan Hovhaness', 'IMG', 'Monika/19.jpg', 100),
(20, 'Gabriel Kazanjian', NULL, NULL, 100),
(21, 'Hovannes Adamian', 'IMG', 'Laura/21.jpeg', 100),
(22, 'Andre Agassi', 'IMG', 'Meri/22.webp', 100),
(23, 'Zabel Yesayan', 'IMG', 'Meri/23.jpg', 100),
(24, 'Varaztad H. Kazanjian', 'IMG', 'Meri/24.jpg', 100);

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
(1, 'Mon'),
(2, 'Stars'),
(3, 'Lucky');

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
(1, 'Who has invented the hot and cold water faucet, which has been used all over the word since 1969.<br>Ո՞վ է հորինել տաք և սառը ջրի ծորակը, որն օգտագործվում է ամբողջ աշխարհում 1969 թվականից։', NULL, NULL, 100, 2, 1),
(2, 'Sometimes you don\'t know where you\'ll end up. When I was selling newspapers at the age of 9, I had no idea that one day those newspapers would write about me.<br>Երբեմն չգիտես, թե որտեղ կհասնես։ Երբ ինը տարեկանում թերթ էի վաճառում, չէի պատկերացնի, որ այդ թերթերը մի օր իմ մասին են գրելու։', NULL, NULL, 100, 3, 2),
(3, 'Which artist has represented his mother in his favorite canvas and expressed genocide in art?<br>Որ նկարիչն է իր հայտնի կտավում ներկայացրել մորը և արվեստում արտահայտել ցեղասպանությունը։', NULL, NULL, 100, 1, 3),
(4, 'His words are: \\\"Win as slowly as possible.\\\" He was a four-time Formula 1 champion. Considered the best racer of his years. Who is it about?<br>Նրա խոսքերն են՝«Հաղթիր ինչքան հնարավոր է  դանդաղ»:Նա եղել է «Ֆորմուլա 1» մրցաշարի  քառակի չեմպիոն: Համարվել է իր տարիների լավագույն մրցարշավորդը: Ո՞ւմ մասին է խոսքը:', NULL, NULL, 100, -1, 4),
(5, 'I am Armenian in California, Armenian in London, in Yerevan. I am Armenian everywhere and everywhere I am the same person... I don’t write in Armenian but look at world in Armenian.<br>Ես Կալիֆորնիայում հայ եմ, Լոնդոնում հայ եմ, Երևանում հայ եմ։ Ես ամենտեղ հայ եմ և ամենտեղ նույն մարդն եմ... Ես հայերեն չեմ խոսում, բայց աշխարհին նայում եմ հայերեն։', NULL, NULL, 100, 2, 5),
(6, 'American-Armenian businessman and philanthropist  is best known for investing millions into turning the crumbling Cascade site in Yerevan into a much loved park. Who is he?<br>Ամերիկահայ գործարարն ու բարերարն առավել հայտնի է նրանով, որ միլիոններ է ներդրել Երևանի փլուզվող Կասկադի տարածքը շատ սիրելի զբոսայգու վերածելու համար: Ով է նա?', NULL, NULL, 100, 3, 6),
(7, 'Which singer became the oldest woman to have a number one song at the of 52?<br>Որ երգչուհին դարձավ 52 տարեկանում թիվ մեկ երգ ունեցող ամենատարեց կինը:', NULL, NULL, 100, 1, 7),
(8, 'Which Armenian-American inventor is considered the author of the automatic transmission of cars, the first American helicopter engine transmission?<br>Ամերիկահայ ո՞ր գյուտարարն է համարվում  մեքենաների ավտոմատ փոխանցմանտուփի, ամերիկյան առաջին ուղղաթիռհ շարժիչի փոխանցման տուփի հեղինակը:', NULL, NULL, 100, -1, 8),
(9, 'Who is best known as a armenian nuclear physicist. Who is a researcher of superheavy chemical elements?<br>Ով առավել հայտնի է որպես հայ միջուկային ֆիզիկոս։ Ո՞վ է գերծանր քիմիական տարրերի հետազոտողը:', NULL, NULL, 100, 2, 9),
(10, 'Who played the main role in the following films: “A Village scandal”, “The Ringtailed Rhinoceros”, “The Red Widow”, “A perfect 36”.<br>Ո՞վ է գլխավոր դերը խաղացել թվարկված ֆիլմերում՝ «Գյուղական սկանդալ», «Օղակավոր ռնգեղջյուր», «Կարմիր այրին», «Կատարյալ 36»։', NULL, NULL, 100, 3, 10),
(11, 'Which hard rock group with only Armenian members became successful in America?<br>Որ ծանր ռոք խումբն է հայտնի դարձել Ամերիկայում,որի բոլոր անդամները հայեր են։', NULL, NULL, 100, 1, 11),
(12, '1894 Who patented the invention of the kerosene lamp in Germany?<br>1894թ. ին ո՞վ է գերմանիայում արտոնագրել կերոսինային լամպի գյուտը:', NULL, NULL, 100, -1, 12),
(13, 'Who has created the green color of the American dollar?<br>Ո՞վ է ստեղծել ամերիկյան դոլարի կանաչ գույնը:', NULL, NULL, 100, 2, 13),
(14, 'Which Armenian star dictates her trends in the field of modern fashion?<br>Ազգությամբ հայ որ աստղն է ժամանակակից նորաձևության ոլորտում թելադրում իր միտումները:', NULL, NULL, 100, 3, 14),
(15, 'The producer who became Damon\'s thanks to the movie \\\"Star Wars\\\".<br>Պրոդյուսերը,որը հայտնի դարձավ \\\"Աստղային Պատերազմներ\\\" ֆիլմի շնորհիվ։', NULL, NULL, 100, 1, 15),
(16, 'Who is the diaspora-Armenian rich man who manages to occupy the second place in the list of the richest people in Argentina at the age of 81?<br>Ո՞վ է այն սփյուռքահայ մեծահարուստը, որին հաջողվում է 81 տարեկանում զբաղեցնել Արգենտինայի ամենահարուստ մարդկանց ցուցակում երկրորդ հորիզոնականը:', NULL, NULL, 100, -1, 16),
(17, 'Who perfected today\'s ATM system?<br>Ո՞վ է կատարելագործել այսօրվա բանկոմատային համակարգը։', NULL, NULL, 100, 2, 17),
(18, 'Who was the first to use complex soundtrack combined with a moving camera, which was revolutionary for that time? He is also famous as “the Godfather” of Hollywood.<br>Ո՞վ է առաջինն օգտագործել բարդ սաունդթրեքը՝ զուգակցված շարժվող տեսախցիկի հետ, որն այն ժամանակ հեղափոխական էր։ Նա հայտնի է նաև Հոլիվուդի «կնքահայր» անունով։', NULL, NULL, 100, 3, 18),
(19, 'who said this words \\\"I want to create a word of endless melody which would be healthy to spirit\\\".<br>Ո՞վ է ասել հետևյալ խոսքերը \\\"Ես ուզում եմ ստեղծել անվերջ մեղեդու մի խոսք, որը առողջարար կլինի ոգու համար\\\"։', NULL, NULL, 100, 1, 19),
(20, 'Which Armenian inventor created the hair straightener and dryer?<br>Ո՞ր հայ գյուտարարի կողմից ստեղծվեց մազերը հարդարող և չորացնող սարքը:', NULL, NULL, 100, -1, 20),
(21, 'The world is really colorfull, it is not black and white, but the history of my people has always been two-colored, we need a lot of colors, so where else should the color television project be launched if not in Yerevan.<br>Աշխարհն իսկապես գունեղ է, սև ու սպիտակ չէ, բայց իմ ժողովրդի պատմությունը միշտ եղել է երկգույն, մեզ շատ գույներ են պետք, ուրեմն էլ որտե՞ղ պետք է սկսել գունավոր հեռուստատեսության նախագիծը, եթե ոչ Երևանում։', NULL, NULL, 100, 2, 21),
(22, 'During his career, the Armenian professional tennis player was recognized as the number one player in the world. Won 8 Grand Tournament games and Olympic Games. Who is it about?<br>Իր կարերայի ընթացքում հայ պրոֆեսիոնալ թենիսիստը ճանաչվել է աշխարհի առաջին ռակետ: Հաղթանակ է տարել Մեծ մրցաշարի 8 խաղերում և օլիմպիական խաղերում: Ո՞ւմ մասին է խոսքը:', NULL, NULL, 100, -1, 22),
(23, 'Which prominent Armenian poetess of the 20th century was arrested in 1937, sentenced to be shot, and then acquitted?<br>20րդ դարի ո՞ր հայ ականավոր բանաստեղծուհին է 1937թ ին ձերբակալվել, դատապարտվել գնդակահարության, այնուհետև արդարացվել:', NULL, NULL, 100, -1, 23),
(24, 'He was one of the innovative revolutionaries in the field of plastic surgery. Fleeing the Hamidian pogroms, he entered Harvard University and became the first professor of plastic surgery. It was called a \\\"magician\\\" by Sigmund Freud. Who is it about?<br>Նա եղել է պլաստիկ վիրաբուժության ասպարեզի նորարար հեղափոխականներից: Փախչելով համիդյան ջարդերից՝ ընդունվել է Հարվարդի համալսարան և դարձել պլաստիկ վիրաբուժության գծով առաջի  պրոֆեսորը: Զիգմունդ Ֆրոյդի կաղմից կոչվել է «հրաշագործ»: Ո՞ւմ մասին է խոսքը:', NULL, NULL, 100, -1, 24);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
