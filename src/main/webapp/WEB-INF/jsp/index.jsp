<!DOCTYPE html>
<html lang="en">
<head>
<?import Modele.Article;>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | HT-Store</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href=""><i class="fa fa-phone"></i> +213 541 84 57 98</a></li>
								<li><a href=""><i class="fa fa-envelope"></i> HT.Store.RB@gmail.com</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->

		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="/"><img src="/img/logo.png" alt="" /></a>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<!--
								<li><a href="/PurchaseHistoryPage"><i class="fas fa-history"></i> Purchase History</a></li>
								<li><a href="/PendingArticlesList"><i class="fa fa-hourglass-start"></i> Pending Articles List </a></li>
								<li><a href="/CartPage"><i class="fa fa-crosshairs"></i> Checkout</a></li>
								<li><a href="/CartPage"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<li><a href="/AccountClientPage"><i class="fa fa-user"></i> Account</a></li>
								<li><a href="login.html"><i class="fa fa-unlock"></i> Logout</a></li>
							-->
								<li><a href="/LoginPage"><i class="fa fa-lock"></i> Login</a></li>
								<li><a href="/RegistratePage"><i class="fa fa-plus-square"></i> Register</a></li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->

		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="/" class="active"><i class="fa fa-home"></i> Home</a></li>
								<li class="dropdown"><a href="/PCConfigPage"><i class="fa fa-cogs"></i> PC configuration </a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
				<hr>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->

	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>

						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1><span>HT</span>-Store</h1>
									<h2>High-Tech Store</h2>
									<p>You search, We find! </p>
									<button type="button" class="btn btn-default get"><a href="#fromContinue" class="btn-continue-class">Continue!</a></button>
								</div>
								<div class="col-sm-6">
									<img src="/img/imgIndex1.jpg" class="girl img-responsive" alt="" />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>HT</span>-Store</h1>
									<h2>High-Tech Store</h2>
									<p>You search, We find! </p>
									<button type="button" class="btn btn-default get"><a href="#fromContinue" class="btn-continue-class">Continue!</a></button>
								</div>
								<div class="col-sm-6">
									<img src="/img/imgIndex2.jpg" class="girl img-responsive" alt="" />
								</div>
							</div>

							<div class="item">
								<div class="col-sm-6">
									<h1><span>HT</span>-Store</h1>
									<h2>High-Tech Store</h2>
									<p>You search, We find! </p>
									<button type="button" class="btn btn-default get"><a href="#fromContinue" class="btn-continue-class">Continue!</a></button>
								</div>
								<div class="col-sm-6">
									<img src="/img/imgIndex3.jpg" class="girl img-responsive" alt="" />
								</div>
							</div>

						</div>

						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>

				</div>
			</div>
		</div>
	</section><!--/slider-->

	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a class="categ" href="/CPU">CPU</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/GPU">GPU</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/RAM">RAM</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/MOTHERBOARD">Motherboard</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/DISKDUR">Hard Disk</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/POWER">PC Power</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/BOX">PC Box</a></h4>
								</div>
							</div>
						</div><!--/category-products-->

						<div class="shipping text-center"><!--shipping-->
						</div><!--/shipping-->

					</div>
				</div>

				<div class="col-sm-9 padding-right">
					<div class="features_items" id="fromContinue"><!--features_items-->
						<h2 class="title text-center">Features Items</h2>
							<c:forEach var="article" items="${result}">
								<div class="col-sm-4">
									<div class="product-image-wrapper">
										<div class="single-products">
												<div class="productinfo text-center">
													<img src="/getPhoto/${article.getPhoto();}" alt="" />
													<!--  <h2>$prix</h2> -->
													<h2>${article.price} DA</h2>
													<!-- <p>$name</p>-->
													<p>${article.name}</p>
													<a href="/Article/${article.codeModele}" class="btn btn-default add-to-cart"><i class="fa fa-bars"></i> More details</a>
												</div>
												<div class="product-overlay">
													<div class="overlay-content">
														<!--  <h2>$prix</h2> -->
														<h2>${article.price} DA</h2>
														<!-- <p>$name</p>-->
														<p>${article.name}</p>
														<a href="/Article/${article.codeModele}" class="btn btn-default add-to-cart"><i class="fa fa-bars"></i> More details</a>
													</div>
												</div>
												<c:choose>
													<c:when test=${article.isNew()}>
														<img src="/img/new.png" class="new" alt="" />
													</c:when>
													<c:when test=${article.isEmpty()}>
														<img src="/img/unavailable.jpg" class="new" alt="" />
													</c:when>
												</c:choose>
										</div>
									</div>
								</div>
							</c:forEach>
					</div><!--features_items-->
				</div>
			</div>
		</div>
	</section>

	<footer id="footer"><!--Footer-->
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2021 HT-Store Inc. All rights reserved.</p>
					<p class="pull-right">Designed by <span><a target="_blank" href="https://github.com/BenyammiBahmed/High-Tech">Bahmed Benyammi & Ahmed Rekibi</a></span></p>
				</div>
			</div>
		</div>

	</footer><!--/Footer-->



    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>