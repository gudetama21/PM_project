-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主機： localhost
-- 產生時間： 2021-01-11 14:39:15
-- 伺服器版本： 10.4.17-MariaDB
-- PHP 版本： 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `costco`
--

-- --------------------------------------------------------

--
-- 資料表結構 `buy`
--

CREATE TABLE `buy` (
  `transaction_number` int(10) NOT NULL,
  `time` date NOT NULL,
  `quantity` int(10) NOT NULL,
  `customer_ID` int(10) NOT NULL,
  `product_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `buy`
--

INSERT INTO `buy` (`transaction_number`, `time`, `quantity`, `customer_ID`, `product_ID`) VALUES
(1, '2020-01-01', 2, 1, 2),
(1, '2020-01-01', 3, 1, 1),
(1, '2020-01-01', 1, 1, 4),
(1, '2020-01-01', 1, 1, 5),
(1, '2020-01-01', 3, 1, 3),
(1, '2020-01-01', 1, 1, 6),
(2, '2020-01-02', 2, 2, 3),
(2, '2020-01-02', 3, 2, 5),
(3, '2020-01-03', 4, 3, 1),
(3, '2020-01-03', 2, 3, 6),
(3, '2020-01-03', 3, 3, 5),
(3, '2020-01-03', 2, 3, 2),
(4, '2020-01-04', 1, 4, 2),
(4, '2020-01-04', 2, 4, 6),
(4, '2020-01-04', 2, 4, 5),
(4, '2020-01-04', 1, 4, 3),
(5, '2020-01-04', 5, 5, 4),
(5, '2020-01-04', 2, 5, 1),
(5, '2020-01-04', 1, 5, 3),
(5, '2020-01-04', 2, 5, 2),
(6, '2020-01-05', 1, 6, 6),
(6, '2020-01-05', 2, 6, 5),
(6, '2020-01-05', 3, 2, 4),
(7, '2020-01-06', 3, 1, 4),
(7, '2020-01-06', 2, 1, 5),
(7, '2020-01-06', 1, 1, 3),
(7, '2020-01-06', 2, 1, 2),
(8, '2020-01-07', 1, 3, 2),
(8, '2020-01-07', 2, 3, 6),
(8, '2020-01-07', 1, 3, 5),
(8, '2020-01-07', 1, 3, 3),
(9, '2020-01-07', 2, 2, 3),
(9, '2020-01-07', 3, 2, 5),
(10, '2020-01-08', 5, 4, 1),
(10, '2020-01-08', 2, 4, 4),
(10, '2020-01-08', 3, 4, 2),
(10, '2020-01-08', 1, 4, 6),
(11, '2020-01-10', 3, 5, 4),
(11, '2020-01-10', 1, 5, 1),
(11, '2020-01-10', 3, 5, 2),
(11, '2020-01-10', 1, 5, 6),
(12, '2020-01-13', 2, 1, 6),
(12, '2020-01-13', 1, 1, 1),
(13, '2020-01-20', 2, 1, 2),
(13, '2020-01-20', 4, 1, 3),
(13, '2020-01-20', 1, 1, 5),
(14, '2020-01-30', 3, 1, 4),
(14, '2020-01-30', 1, 1, 2),
(14, '2020-01-30', 3, 1, 6),
(15, '2020-01-14', 2, 2, 2),
(15, '2020-01-14', 1, 2, 6),
(15, '2020-01-14', 6, 2, 1),
(16, '2020-01-24', 2, 2, 4),
(16, '2020-01-24', 3, 2, 5),
(16, '2020-01-24', 4, 2, 2),
(17, '2020-01-29', 1, 2, 3),
(17, '2020-01-29', 1, 2, 6),
(17, '2020-01-29', 1, 2, 4),
(18, '2020-01-21', 2, 3, 4),
(18, '2020-01-21', 2, 3, 5),
(18, '2020-01-21', 2, 3, 2),
(19, '2020-01-28', 1, 3, 1),
(19, '2020-01-28', 4, 3, 2),
(19, '2020-01-28', 2, 3, 3),
(19, '2020-01-28', 6, 3, 4),
(20, '2020-01-18', 1, 4, 6),
(20, '2020-01-18', 5, 4, 5),
(21, '2020-01-27', 3, 4, 1),
(21, '2020-01-27', 1, 4, 6),
(21, '2020-01-27', 4, 4, 4),
(22, '2020-01-16', 1, 5, 1),
(22, '2020-01-16', 1, 5, 2),
(22, '2020-01-16', 1, 5, 3),
(23, '2020-01-25', 2, 5, 4),
(23, '2020-01-25', 2, 5, 5),
(23, '2020-01-25', 2, 5, 6),
(24, '2020-01-11', 1, 6, 2),
(24, '2020-01-11', 2, 6, 1),
(24, '2020-01-11', 3, 6, 4),
(25, '2020-01-21', 2, 6, 2),
(25, '2020-01-21', 1, 6, 6),
(25, '2020-01-21', 4, 6, 5),
(25, '2020-01-21', 2, 6, 1);

-- --------------------------------------------------------

--
-- 資料表結構 `customer`
--

CREATE TABLE `customer` (
  `customer_ID` int(10) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birth_day` date NOT NULL,
  `address` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `customer`
--

INSERT INTO `customer` (`customer_ID`, `sex`, `birth_day`, `address`, `name`) VALUES
(1, 'female', '2000-01-01', '台北市信義區市府路1號', 'Amy'),
(2, 'male', '1990-02-02', '桃園市桃園區縣府路1號', 'Bob'),
(3, 'female', '1950-03-03', '新北市板橋區中山路一段161號', 'Joy'),
(4, 'male', '1963-04-04', '台中市西屯區台灣大道三段99號', 'John'),
(5, 'male', '1972-05-05', '台南市安平區永華路二段6號', 'Johnson'),
(6, 'female', '1983-06-06', '台北市文山區指南路二段64號', 'Kevin');

-- --------------------------------------------------------

--
-- 資料表結構 `product`
--

CREATE TABLE `product` (
  `product_ID` int(10) NOT NULL,
  `product_price` int(10) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `product`
--

INSERT INTO `product` (`product_ID`, `product_price`, `product_name`, `quantity`) VALUES
(1, 100, '餅乾', 69),
(2, 50, '可樂', 415),
(3, 500, '豬肉', 481),
(4, 600, '維他命', 310),
(5, 250, '衛生紙', 166),
(6, 150, '果汁', 278);

-- --------------------------------------------------------

--
-- 資料表結構 `returns`
--

CREATE TABLE `returns` (
  `transaction_number` int(10) NOT NULL,
  `time` date NOT NULL,
  `quantity` int(10) NOT NULL,
  `customer_ID` int(10) NOT NULL,
  `product_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `returns`
--

INSERT INTO `returns` (`transaction_number`, `time`, `quantity`, `customer_ID`, `product_ID`) VALUES
(1, '2020-01-02', 1, 1, 2),
(2, '2020-01-04', 2, 4, 5),
(2, '2020-01-04', 1, 4, 2),
(3, '2020-01-04', 1, 3, 6),
(4, '2020-01-28', 3, 4, 1),
(4, '2020-01-28', 1, 4, 6),
(4, '2020-01-28', 4, 4, 4),
(5, '2020-01-17', 1, 5, 3),
(6, '2020-01-22', 2, 3, 5),
(6, '2020-01-22', 2, 3, 2),
(7, '2020-01-26', 2, 5, 5),
(8, '2021-01-30', 5, 4, 1),
(8, '2021-01-30', 3, 4, 2),
(8, '2021-01-30', 2, 4, 4),
(8, '2021-01-30', 4, 4, 5);

-- --------------------------------------------------------

--
-- 資料表結構 `store`
--

CREATE TABLE `store` (
  `store_ID` int(10) NOT NULL,
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `store`
--

INSERT INTO `store` (`store_ID`, `location`) VALUES
(1, '內湖'),
(2, '中和'),
(3, '北投'),
(4, '新莊'),
(5, '汐止'),
(6, '中壢'),
(7, '南崁');

-- --------------------------------------------------------

--
-- 資料表結構 `supplier`
--

CREATE TABLE `supplier` (
  `supplier_ID` int(10) NOT NULL,
  `supplier_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `supplier`
--

INSERT INTO `supplier` (`supplier_ID`, `supplier_name`) VALUES
(1, '品臻國際'),
(2, '可口可樂'),
(3, '芬達'),
(4, '科克蘭'),
(5, '善存'),
(6, '卜蜂');

-- --------------------------------------------------------

--
-- 資料表結構 `supply`
--

CREATE TABLE `supply` (
  `transaction_number` int(10) NOT NULL,
  `time` date NOT NULL,
  `quantity` int(10) NOT NULL,
  `supplier_ID` int(10) NOT NULL,
  `product_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `supply`
--

INSERT INTO `supply` (`transaction_number`, `time`, `quantity`, `supplier_ID`, `product_ID`) VALUES
(1, '2020-12-15', 100, 1, 1),
(2, '2020-12-15', 450, 2, 2),
(3, '2020-12-16', 500, 6, 3),
(4, '2020-12-17', 350, 5, 4),
(5, '2020-12-18', 200, 4, 5),
(6, '2020-12-19', 300, 3, 6);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `buy`
--
ALTER TABLE `buy`
  ADD KEY `customer_ID` (`customer_ID`,`product_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- 資料表索引 `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_ID`);

--
-- 資料表索引 `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_ID`);

--
-- 資料表索引 `returns`
--
ALTER TABLE `returns`
  ADD KEY `customer_ID` (`customer_ID`,`product_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- 資料表索引 `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`store_ID`);

--
-- 資料表索引 `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplier_ID`);

--
-- 資料表索引 `supply`
--
ALTER TABLE `supply`
  ADD PRIMARY KEY (`transaction_number`),
  ADD KEY `supplier_ID` (`supplier_ID`,`product_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `product`
--
ALTER TABLE `product`
  MODIFY `product_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `store`
--
ALTER TABLE `store`
  MODIFY `store_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplier_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `supply`
--
ALTER TABLE `supply`
  MODIFY `transaction_number` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `buy`
--
ALTER TABLE `buy`
  ADD CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`customer_ID`) REFERENCES `customer` (`customer_ID`),
  ADD CONSTRAINT `buy_ibfk_2` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`);

--
-- 資料表的限制式 `returns`
--
ALTER TABLE `returns`
  ADD CONSTRAINT `returns_ibfk_1` FOREIGN KEY (`customer_ID`) REFERENCES `customer` (`customer_ID`),
  ADD CONSTRAINT `returns_ibfk_2` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`);

--
-- 資料表的限制式 `supply`
--
ALTER TABLE `supply`
  ADD CONSTRAINT `supply_ibfk_1` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`),
  ADD CONSTRAINT `supply_ibfk_2` FOREIGN KEY (`supplier_ID`) REFERENCES `supplier` (`supplier_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
