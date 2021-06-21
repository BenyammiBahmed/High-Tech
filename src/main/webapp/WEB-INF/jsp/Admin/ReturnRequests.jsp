<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Return Requests | HT-Store</title>
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
    	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
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
                            <a href="/listeCommandeDashboard"><img src="img/logo.png" alt="" /></a>
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <div class="shop-menu pull-right">
                            <ul class="nav navbar-nav">
                            	<li><a href="/ReturnRequestsPage" class="active"><i class="fas fa-undo-alt"></i> Return Requests</a></li>
                                <li><a href="/CustomerRequestsPage"><i class="fa fa-hourglass-start"></i> Customer Requests</a></li>
                                <li class="dropdown"><a href="/ArticlesPage"><i class="fa fa-gear"></i> Articles<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="/AddProductPage"><i class="fa fa-plus"></i>  Add a new product</a></li>
                                        <li><a href="/ArticlesPage"><i class="fa fa-gear"></i>  Update a product</a></li>
                                        <li><a href="/ArticlesPage"><i class="fa fa-times"></i>  Delete a product</a></li>

                                    </ul>
                                <li><a href="/AccountAdminPage"><i class="fa fa-user"></i> Account</a></li>
                                <li><a href="home.html" ><i class="fa fa-unlock"></i> Logout</a></li>
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
								<li><a href="/listeCommandeDashboard"><i class="fa fa-home"></i> Home</a></li>
							</ul>
						</div>
					</div>
				</div>
				<hr>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
                <ol class="breadcrumb">
                  <li><a href="/listeCommandeDashboard">Home</a></li>
                  <li class="active">Return Requests</li>
                </ol>
            </div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="email">Email</td>
							<td class="itemId">Item ID</td>
							<td class="name">Name</td>
							<td class="type">Type</td>
							<td class="quantity">Quantity</td>
							<td class="">More Informations</td>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="returnRequest" items="${}">
							<form>
								<tr>
									<td class="email">
										<p><input name="email" value="ahmed555@gmail.com" style="border: none;" readonly></p>
									</td>
									<td class="id_item">
										<p><input name="id_item" value="1089772" style="border: none;" readonly></p>
									</td>
									<td class="request_name">
										<p class=""><input type="text" name="name" value="AMD r9 3265" style="border: none;" readonly></p>
									</td>
									<td class="request_type">
										<p class=""><input type="text" name="type" value="GPU" style="border: none;" readonly></p>
									</td>
									<td class="request_qte">
										<p class=""><input type="number" name="quantity" value="4" style="border: none;" readonly></p>
									</td>
									<td class="moreInformation">
										<a href="/ReturnRequestInfPage/codeModele"><i class="fa fa-chevron-right fa-2x"></i></a>
									</td>
								</tr>	
							</form>	
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section> <!--/#cart_items-->


	<footer>		
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
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>