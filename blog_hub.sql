-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2024 at 09:46 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blog_hub`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `content`, `created_at`, `post_id`, `user_id`) VALUES
(1, 'So informative! Thank you Tommy', '2024-09-27 02:11:26', 8, 1),
(2, 'test', '2024-09-27 04:01:38', 8, 4);

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `title`, `content`, `created_at`, `user_id`) VALUES
(1, 'Pellentesque a consectetur velit, ac molestie ipsum. Donec sodales, massa et auctor.', '<p class=\"pb-6\">Advantage old had otherwise sincerity dependent additions. It in adapted natural hastily is justice. Six draw you him full not mean evil. Prepare garrets it expense windows shewing do an. She projection advantages resolution son indulgence. Part sure on no long life am at ever. In songs above he as drawn to. Gay was outlived peculiar rendered led six.</p>\r\n<p class=\"pb-6\">Difficulty on insensible reasonable in. From as went he they. Preference themselves me as thoroughly partiality considered on in estimating. Middletons acceptance discovered projecting so is so or. In or attachment inquietude remarkably comparison at an. Is surrounded prosperous stimulated am me discretion expression. But truth being state can she china widow. Occasional preference fat remarkably now projecting uncommonly dissimilar. Sentiments projection particular companions interested do at my delightful. Listening newspaper in advantage frankness to concluded unwilling.</p>\r\n<p class=\"pb-6\">Adieus except say barton put feebly favour him. Entreaties unpleasant sufficient few pianoforte discovered uncommonly ask. Morning cousins amongst in mr weather do neither. Warmth object matter course active law spring six. Pursuit showing tedious unknown winding see had man add. And park eyes too more him. Simple excuse active had son wholly coming number add. Though all excuse ladies rather regard assure yet. If feelings so prospect no as raptures quitting.</p>\r\n<div class=\"border-l-4 border-gray-500 pl-4 mb-6 italic rounded\">Sportsman do offending supported extremity breakfast by listening. Decisively advantages nor expression unpleasing she led met. Estate was tended ten boy nearer seemed. As so seeing latter he should thirty whence. Steepest speaking up attended it as. Made neat an on be gave show snug tore.</div>\r\n<p class=\"pb-6\">Exquisite cordially mr happiness of neglected distrusts. Boisterous impossible unaffected he me everything. Is fine loud deal an rent open give. Find upon and sent spot song son eyes. Do endeavor he differed carriage is learning my graceful. Feel plan know is he like on pure. See burst found sir met think hopes are marry among. Delightful remarkably new assistance saw literature mrs favourable.</p>\r\n<h2 class=\"text-2xl text-gray-800 dark:text-white font-semibold mb-4 mt-4\">Uneasy barton seeing remark happen his has</h2>\r\n<p class=\"pb-6\">Guest it he tears aware as. Make my no cold of need. He been past in by my hard. Warmly thrown oh he common future. Otherwise concealed favourite frankness on be at dashwoods defective at. Sympathize interested simplicity at do projecting increasing terminated. As edward settle limits at in.</p>\r\n<p class=\"pb-6\">Dashwood contempt on mr unlocked resolved provided of of. Stanhill wondered it it welcomed oh. Hundred no prudent he however smiling at an offence. If earnestly extremity he he propriety something admitting convinced ye. Pleasant in to although as if differed horrible. Mirth his quick its set front enjoy hoped had there. Who connection imprudence middletons too but increasing celebrated principles joy. Herself too improve gay winding ask expense are compact. New all paid few hard pure she.</p>\r\n<p class=\"pb-6\">Breakfast agreeable incommode departure it an. By ignorant at on wondered relation. Enough at tastes really so cousin am of. Extensive therefore supported by extremity of contented. Is pursuit compact demesne invited elderly be. View him she roof tell her case has sigh. Moreover is possible he admitted sociable concerns. By in cold no less been sent hard hill.</p>\r\n<p class=\"pb-6\">Detract yet delight written farther his general. If in so bred at dare rose lose good. Feel and make two real miss use easy. Celebrated delightful an especially increasing instrument am. Indulgence contrasted sufficient to unpleasant in in insensible favourable. Latter remark hunted enough vulgar say man. Sitting hearted on it without me.</p>', '2024-09-25 11:58:10', 1),
(2, 'Gourmet Eats on a Budget: How to Create Restaurant-Worthy Meals at Home', '<p>Eating out at fancy restaurants can be a real treat, but for those of us who enjoy cooking, creating gourmet meals at home is just as satisfying&mdash;without breaking the bank! In this post, I&rsquo;ll guide you through my favorite budget-friendly ingredients and techniques to elevate your home-cooked dishes to restaurant quality. From the art of perfecting sauces to mastering simple plating techniques that will wow your guests, this guide is all about enjoying delicious, beautifully presented food without spending a fortune.</p>\r\n<p>One of the keys to creating gourmet dishes at home is understanding which ingredients provide the best flavor and presentation without being expensive. For example, using fresh herbs, quality olive oil, and seasonal vegetables can transform a basic dish into something extraordinary. I\'ll walk you through simple but impressive recipes, such as a creamy garlic shrimp pasta that costs less than $10 to make, and a quick but decadent chocolate mousse that rivals anything you&rsquo;d find in a restaurant. You don&rsquo;t need fancy equipment or exotic ingredients to create meals that leave a lasting impression.</p>\r\n<p>Another important aspect of creating gourmet meals is presentation. With a few plating tricks, like adding a garnish or drizzling sauces artistically, even the simplest dishes can look professionally crafted. I&rsquo;ll also share some tips for hosting dinner parties on a budget, including how to create multi-course meals that are both cost-effective and elegant. Cooking at home not only saves money but also allows for creativity and personalization that you can&rsquo;t always get at a restaurant.</p>', '2024-09-25 14:36:17', 1),
(5, 'From Canvas to Code: The Intersection of Art and Technology', '<p>Art and technology have always been intertwined, but the digital age has brought them together in ways we couldn&rsquo;t have imagined a few decades ago. This blog post takes a deep dive into how contemporary artists are leveraging technology&mdash;from artificial intelligence to augmented reality&mdash;to create works that challenge traditional notions of art. We explore several pioneering artists who are blending the world of programming with visual art, designing installations that respond to viewers&rsquo; movements or using data as a medium.</p>\r\n<p>One exciting area of innovation is generative art, where algorithms are used to create unique, often unpredictable, pieces. Artists like Refik Anadol are using machine learning to analyze vast datasets and produce mesmerizing visualizations that push the boundaries of human creativity. At the same time, augmented reality (AR) is opening new doors for interactive art experiences, allowing viewers to immerse themselves in digital environments that blend seamlessly with the physical world. These advancements raise thought-provoking questions about the role of the artist, the creative process, and the relationship between technology and human expression.</p>\r\n<p>As these technologies evolve, the line between creator and viewer is becoming increasingly blurred. Digital platforms like NFTs are also revolutionizing the way art is bought, sold, and experienced, enabling artists to reach a global audience without the need for traditional galleries. This post invites you to consider how the fusion of art and technology is shaping the future of creative expression and what it means to be an artist in the 21st century.</p>', '2024-09-26 16:17:31', 2),
(6, '10 Life Lessons I Learned from Traveling Solo Around the World', '<p><em>Traveling solo is one of the most liberating experiences you can have. It forces you to rely on yourself, trust your instincts, and embrace the unknown. Over the past two years, I&rsquo;ve traveled to 15 different countries on my own, and each journey has taught me invaluable life lessons. In this post, I reflect on the highs and lows of solo travel&mdash;from learning to navigate foreign cultures to finding peace in solitude.</em></p>\r\n<p><em>One of the first things I learned was the importance of flexibility. Traveling alone means you&rsquo;re in complete control of your itinerary, but it also means being adaptable when things don&rsquo;t go as planned. From missed flights to unexpected weather changes, solo travel has taught me how to stay calm and make the best of any situation. I&rsquo;ve also learned to appreciate the beauty of solitude. Whether it&rsquo;s enjoying a quiet meal at a local caf&eacute; or watching the sunset over a remote beach, solo travel allows for moments of deep reflection and personal growth.</em></p>\r\n<p><em>Another valuable lesson I&rsquo;ve gained is the power of human connection. While traveling solo, I&rsquo;ve met people from all walks of life&mdash;locals, fellow travelers, and even complete strangers who&rsquo;ve left a lasting impact on me. These encounters have broadened my perspective and taught me the importance of kindness and open-mindedness. Traveling alone has given me a deeper understanding of myself and the world around me, and I encourage everyone to try it at least once in their lives.</em></p>', '2024-09-26 16:18:41', 2),
(7, 'Financial Independence: How to Start Investing as a Beginner', '<p>Achieving financial independence may seem like a distant dream, but with the right strategies and mindset, anyone can begin their journey towards wealth. In this comprehensive guide, I&rsquo;ll break down the basics of investing, from understanding the stock market to selecting the right assets for long-term growth. You&rsquo;ll learn about the power of compound interest, the importance of diversifying your portfolio, and how to set realistic financial goals.</p>\r\n<p>For beginners, the world of investing can be overwhelming, but it doesn&rsquo;t have to be. Start by educating yourself on the different types of investment vehicles available, such as stocks, bonds, and mutual funds. I&rsquo;ll explain the pros and cons of each, and how to choose the ones that align with your financial goals and risk tolerance. A key principle of investing is diversification&mdash;by spreading your investments across different asset classes, you reduce the risk of significant loss if one market performs poorly. I&rsquo;ll also share tips on how to create a budget for investing, even if you&rsquo;re starting with a small amount of capital.</p>\r\n<p>One of the most powerful tools in investing is compound interest. By reinvesting your earnings, you can grow your wealth exponentially over time. I\'ll also introduce you to the concept of passive investing through index funds, which is ideal for those who want to build wealth without actively managing their portfolio. With discipline, patience, and a well-thought-out plan, anyone can achieve financial independence and enjoy the benefits of smart investing.</p>', '2024-09-26 16:19:19', 2),
(8, 'Exploring the Impact of 5G Technology on Global Connectivity', '<p><em>The digital world is evolving at an extraordinary pace, and at the forefront of this revolution is 5G technology. With its promise of lightning-fast data speeds, ultra-low latency, and the ability to connect billions of devices, 5G is poised to transform industries ranging from healthcare to entertainment. In this post, we delve deep into the technological advancements behind 5G and explore how it is set to redefine global communication. From the rise of autonomous vehicles to the development of smart cities, 5G is not just about faster internet&mdash;it&rsquo;s about creating a world where everything and everyone is connected.</em></p>\r\n<p><em>Beyond the obvious benefits, 5G also brings unique challenges. The infrastructure required to support 5G networks is vast and costly, with telecom companies and governments investing billions into upgrading existing systems. In addition, the widespread adoption of 5G raises concerns about cybersecurity, data privacy, and the ethical implications of ubiquitous connectivity. While 5G promises to boost industries like telemedicine, which relies on real-time data transmission, it also highlights the importance of addressing these potential risks as we transition into a hyper-connected future.</em></p>\r\n<p><em>However, the potential for 5G to improve economies, healthcare, and education is immense. In rural areas where access to fast internet is limited, 5G could bridge the digital divide, enabling more people to access online services and digital infrastructure. As 5G continues to roll out, we can expect rapid innovation across various sectors, with new applications and services emerging that take full advantage of its capabilities. The full potential of 5G may take years to realize, but its impact on our everyday lives is undeniable.</em></p>', '2024-09-26 16:21:24', 3);

-- --------------------------------------------------------

--
-- Table structure for table `posts_tags`
--

CREATE TABLE `posts_tags` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posts_tags`
--

INSERT INTO `posts_tags` (`post_id`, `tag_id`) VALUES
(1, 7),
(2, 3),
(2, 4),
(2, 9),
(5, 1),
(5, 5),
(5, 9),
(6, 2),
(6, 4),
(6, 6),
(7, 1),
(7, 5),
(7, 7),
(8, 1),
(8, 6),
(8, 7);

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id`, `name`, `description`) VALUES
(1, 'Technology', 'Posts related to the latest tech innovations and gadgets'),
(2, 'Travel', 'Stories and tips about traveling the world'),
(3, 'Food', 'Posts about recipes, restaurants, and culinary experiences'),
(4, 'Lifestyle', 'Guides and tips on lifestyle and personal development'),
(5, 'Education', 'Educational content and learning resources'),
(6, 'Health', 'Posts about health, fitness, and well-being'),
(7, 'Finance', 'Personal finance, investing, and financial tips'),
(8, 'Photography', 'Photography tips, gear reviews, and visual storytelling'),
(9, 'Art', 'Explorations of visual and performing arts'),
(10, 'Music', 'Posts about music, artists, and album reviews');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `created_at`) VALUES
(1, 'Antonius Sebastian', 'antoniussebastian101@gmail.com', 'dlYMtSIV46M29egoaPm7cg==:Oc8YNLHnBXwKP74JB2qc6A==', '2024-09-25 04:18:45'),
(2, 'jul', 'jul@gmail.com', 'R55SFDKQBeZ+KNfmukbwHw==:1CqxjfAb2vLV/BNehfvRZA==', '2024-09-25 05:24:56'),
(3, 'Tommy', 'tommy@yahoo.com', '6lfEto7B3vqoyIXLFb9ZYw==:renzJTVsJJS/LqMdWLHMlg==', '2024-09-26 16:20:43'),
(4, 'test', 'test@gmail.com', 'ZfttdSf11Vg/Fwo4YZ/FNA==:j1LrRy9LEfzPOXcA5Vvmdg==', '2024-09-27 04:00:36');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `post_id` (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `posts_tags`
--
ALTER TABLE `posts_tags`
  ADD PRIMARY KEY (`post_id`,`tag_id`),
  ADD KEY `tag_id` (`tag_id`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `posts_tags`
--
ALTER TABLE `posts_tags`
  ADD CONSTRAINT `posts_tags_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `posts_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
