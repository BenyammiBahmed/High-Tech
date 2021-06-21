<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login | HT-Store</title>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
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
                            	<li><a href="#" class="active"><i class="fa fa-lock"></i> Login</a></li>
                                <li><a href="/RegistratePage" ><i class="fa fa-plus-square"></i> Register</a></li>
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
								<li><a href="/"><i class="fa fa-home"></i> Home</a></li>
								<li class="dropdown"><a href="/PCConfigPage"><i class="fa fa-cogs"></i> PC configuration </a></li>
							</ul>
						</div>
					</div>
				</div>
                <hr>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->

	<section id="form"><!--form-->
		<div class="container">
			<div class="breadcrumbs">
                <ol class="breadcrumb">
                  <li><a href="/">Home</a></li>
                  <li class="active">Login</li>
                </ol>
            </div>
			<div class="row">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form action="/Login" method="get">
							<input type="text" name="email" placeholder="Email" required />
							<input type="password" name="password" placeholder="Password" required />
							<span>
								<input type="checkbox" class="checkbox">
								Remember me
							</span>
							<button type="submit" id="loginForWidth" class="btn btn-default btnForWidth" >Login</button>

							<hr>
							<h4 style="text-align: center;">You don't have an account !</h4>
							<button class="btn btn-default btnForWidth"  id="loginForWidth"><a href="/RegistratePage" class="btn-continue-class"><i class="fa fa-plus-square"></i> Register</a></button>
					</div><!--/login form-->
					</form>
			</div>
		</div>
	</section><!--/form-->

	<footer>
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2021 HT-Store Inc. All rights reserved.</p>
					<p class="pull-right">Designed by <span><a target="_blank" href="https://github.com/BenyammiBahmed/High-Tech">Ahmed Rekibi & Bahmed Benyammi</a></span></p>
				</div>
			</div>
		</div>
	</footer><!--/Footer-->



    <script src="js/jquery.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>