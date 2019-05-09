-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 09, 2019 at 09:53 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `AndroidProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `buy_now`
--

CREATE TABLE `buy_now` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `message` text NOT NULL,
  `card` int(11) NOT NULL,
  `month` text NOT NULL,
  `year` text NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buy_now`
--

INSERT INTO `buy_now` (`id`, `id_user`, `id_item`, `message`, `card`, `month`, `year`, `price`) VALUES
(3, 34, 1, 'ret', 456, '12', '19', 150);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `feedback` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `feedback`) VALUES
(1, 'Ù„Ø¹Ø¤Ù‰');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id_item` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id_item`, `id_user`, `id_product`) VALUES
(99, 35, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Product`
--

CREATE TABLE `Product` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `rating` double NOT NULL,
  `price` double NOT NULL,
  `imageURL` text CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`id`, `title`, `description`, `rating`, `price`, `imageURL`) VALUES
(1, 'New Spring winter', 'New Spring winter Round Neck Sleeveless Ruffles Pleated Irregular Cut Loose Big White Dress Women Fashion JG310', 4.5, 150, 'https://f.top4top.net/p_1186zk0xx1.jpg'),
(2, '2019 New Spring Winter', '2019 New Spring Winter Stand Collar Bandage Bow Long Sleeve Leopard Printed Perspective Dress Women Fashion Tide JL488', 5, 180, 'https://f.top4top.net/p_1186f1uwu1.jpg'),
(3, 'Depeithy v-neck', 'Depeithy v-neck beads bodice open back a line long evening dress party elegant vestido de festa fast shipping prom gowns', 4.9, 210, 'https://d.top4top.net/p_1186duld91.jpg'),
(4, 'BEFORW Fashion 2019', 'BEFORW Fashion 2019 Women Sexy Off The Shoulder Dress White Black Dresses Casual Elegant Vintage Office Wear Elasticity Dress\r\n', 3.5, 100, 'https://f.top4top.net/p_118690h5a1.jpg'),
(5, 'KANCOOLD dress Women', 'KANCOOLD dress Women Boho Back Lace Mini A-Line Sundress Sleeveless Evening Party Summer Beach dress women 2018jul20\r\n', 4.9, 250, 'https://f.top4top.net/p_1189cjlvi1.jpg'),
(8, 'Women Lace', 'Women Lace Prom Floral Formal Evening Clubwear Party Sexy Ball Gown Dress Girls vestidos\r\n', 5, 120, 'https://a.top4top.net/p_118957nsk1.jpg'),
(9, 'New Sexy Women Sleeveless', 'New Sexy Women Sleeveless V-neck A-Line Dresses Slim Party Evening Summer Beach Short Mini Dress Black M\r\n', 4.7, 150, 'https://d.top4top.net/p_1189myoqx1.jpg'),
(10, '2019 Autumn Winter New Fashion Women \r\n', '2019 Autumn Winter New Fashion Women Vintage O-Neck Sashes Dress Lace Patchwork Mesh A-Line Elegant Mini Dresses Female Vestidos\r\n', 4.8, 150, 'https://d.top4top.net/p_11892q7yb1.jpg'),
(11, 'Fashion Women Summer', 'Fashion Women Summer Casual Solid Lace Sleeveless Party Evening Sleeveless Sexy Short Mini Dress\r\n', 4.5, 150, 'https://b.top4top.net/p_1189up3wj1.jpg'),
(12, 'Sexy Women Summer\r\n', 'Sexy Women Summer Casual Sleeveless Clubwear Party Beach Dress Vestidos Boho Harajuku Mini Dress\r\n', 4.8, 200, 'https://e.top4top.net/p_1189b1voz1.jpg'),
(13, 'lace White Ivory Short Cap ', 'ZJ9085 2018 lace White Ivory Short Cap Sleeve Wedding Dresses for bride bridal gown Vintage plus size maxi Customer made ', 4.9, 1500, 'https://c.top4top.net/p_1192fhu041.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users_table`
--

CREATE TABLE `users_table` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text,
  `photo` text NOT NULL,
  `phone` text NOT NULL,
  `address` text NOT NULL,
  `street` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_table`
--

INSERT INTO `users_table` (`id`, `name`, `email`, `password`, `photo`, `phone`, `address`, `street`) VALUES
(34, 'Ibrahem zablah', 'ibrahemzablah1@gmail.com', '$2y$10$uB8s4C2U63QPk6l4pP1SSuOdII8sAqVM3HylUT.GQXUeBzXjoWRGi', 'http://192.168.1.13/profile_image/34.*', '+972568526042', 'Palestine', 'nablus'),
(35, 'muhammad', 'muhammad@gmail.com', '$2y$10$x/jvUSvsmXPbRJmzFdnf/uT9uzVi6vIpg73pWdG3QuhBvoh.rvYQS', 'http://192.168.1.13/profile_image/35.*', '+972568526042', 'palestine', 'nablus'),
(36, 'mutasem', 'mutasem@gmail.com', '$2y$10$ILRwW1Yil6Bc9fdI0zPVluGSXZHogUWRrOI7mqugxc.iq13U29bNO', '', '57394678', 'nablus', 'nablus');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buy_now`
--
ALTER TABLE `buy_now`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_order` (`id_product`);

--
-- Indexes for table `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_table`
--
ALTER TABLE `users_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `id` (`id`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buy_now`
--
ALTER TABLE `buy_now`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `Product`
--
ALTER TABLE `Product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users_table`
--
ALTER TABLE `users_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users_table` (`id`),
  ADD CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `Product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
