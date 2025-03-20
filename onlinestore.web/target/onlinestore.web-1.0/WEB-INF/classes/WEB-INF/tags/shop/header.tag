<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Retrieve the locale from the session -->
<c:set var="userLocale" value="${sessionScope.userLocale}" />
<c:if test="${empty userLocale}">
	<c:set var="userLocale" value="${pageContext.request.locale}" />
</c:if>

<c:set var="languageTag" value="${userLocale.language}" />

<!-- Set the ResourceBundle using the user's locale -->
<fmt:setLocale value="${userLocale}" />
<fmt:setBundle basename="MyLabels" var="resourceBundle" />

<header id="header" class="top-head">
	<!-- Static navbar -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-sm-12 left-rs">
					<div class="navbar-header">
						<button type="button" id="top-menu"
							class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#navbar" aria-expanded="false">
							<span class="sr-only"></span> <span class="icon-bar"></span>
							<fmt:message key="toggle.navigation" bundle="${resourceBundle}" />
							<span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<a href="homepage" class="navbar-brand"><img
							src="images/logo.png" alt="" /></a>
					</div>
					<form action="./products" method="GET"
						class="navbar-form navbar-left web-sh">
						<div class="form">
							<input type="text" class="form-control" name="product_name"
								placeholder="<fmt:message key='search.products.placeholder' bundle='${resourceBundle}' />">

						</div>
					</form>
				</div>
				<div class="col-md-8 col-sm-12">
					<div class="right-nav">
						<div class="login-sr">
							<div class="login-signup">
								<ul>
									<c:choose>
										<c:when test="${user != null}">
											<li><a href="./profile"> <fmt:message
														key="welcome.user" bundle="${resourceBundle}">
														<fmt:param value="${user.firstName}" />
													</fmt:message>
											</a></li>
											<li><a class="custom-b" href="./signout"><fmt:message
														key="sign.out" bundle="${resourceBundle}" /></a></li>
										</c:when>
										<c:otherwise>
											<li><a href="./login"><fmt:message key="login"
														bundle="${resourceBundle}" /></a></li>
											<li><a class="custom-b" href="./signup"><fmt:message
														key="sign.up" bundle="${resourceBundle}" /></a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
						<div class="help-r hidden-xs">
							<div class="help-box">
								<ul>
									<li><a data-toggle="modal" data-target="#myModal" href="#">
											<span><fmt:message key="change"
													bundle="${resourceBundle}" /></span> <img style="width: 30px;"
											src="images/${languageTag}.png" alt="" />
									</a></li>
									<li><a href="#"><img class="h-i"
											src="images/help-icon.png" alt="" /> <fmt:message key="help"
												bundle="${resourceBundle}" /></a></li>
								</ul>
							</div>
						</div>
						<div class="nav-b hidden-xs">
							<div class="nav-box">
								<ul>
									<li><a href="howitworks"><fmt:message
												key="how.works" bundle="${resourceBundle}" /></a></li>
									<li><a href="about-us"><fmt:message
												key="chamb.business" bundle="${resourceBundle}" /></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/.container-fluid -->
	</nav>
</header>
<!-- Modal -->
<div class="modal fade lug" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="change" bundle="${resourceBundle}" />
				</h4>
			</div>
			<div class="modal-body">
				<ul>
					<li><a href="./change_language?language=en"> <img
							src="images/unitedstates.png" alt="" /> <fmt:message
								key="united.states" bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=fr"> <img
							src="images/france.png" alt="" /> <fmt:message key="france"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=es"> <img
							src="images/spain.png" alt="" /> <fmt:message key="spain"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=de"> <img
							src="images/germany.png" alt="" /> <fmt:message key="germany"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=it"> <img
							src="images/italy.png" alt="" /> <fmt:message key="italy"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=zh"> <img
							src="images/china.png" alt="" /> <fmt:message key="china"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=ja"> <img
							src="images/japan.png" alt="" /> <fmt:message key="japan"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=ko"> <img
							src="images/south-korea.png" alt="" /> <fmt:message key="korea"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=nl"> <img
							src="images/netherlands.png" alt="" /> <fmt:message
								key="netherlands" bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=pl"> <img
							src="images/poland.png" alt="" /> <fmt:message key="poland"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=pt"> <img
							src="images/portugal.png" alt="" /> <fmt:message key="portugal"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=ru"> <img
							src="images/russia.png" alt="" /> <fmt:message key="russia"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=sv"> <img
							src="images/sweden.png" alt="" /> <fmt:message key="sweden"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=tr"> <img
							src="images/turkey.png" alt="" /> <fmt:message key="turkey"
								bundle="${resourceBundle}" />
					</a></li>
					<li><a href="./change_language?language=hi"> <img
							src="images/india.png" alt="" /> <fmt:message key="india"
								bundle="${resourceBundle}" />
					</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="sidebar" class="top-nav">
	<ul id="sidebar-nav" class="sidebar-nav">
		<li><a href="#"><fmt:message key="help"
					bundle="${resourceBundle}" /></a></li>
		<li><a href="#"><fmt:message key="how.works"
					bundle="${resourceBundle}" /></a></li>
		<li><a href="#"><fmt:message key="chamb.business"
					bundle="${resourceBundle}" /></a></li>
	</ul>
</div>
