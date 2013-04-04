<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets">
<head>
<meta charset="UTF-8">
<title>Bowling</title>
<link rel="stylesheet" type="text/css" href="resources/style.css" />
<link rel="stylesheet" type="text/css" href="resources/tablestyle.css" />
<script src="resources/html5shiv.js"></script>
<script src="resources/jquery-1.9.1.js"></script>
<script>
	$(document).ready(
			function() {
				$("#btnExport").click(
						function(e) {
							window.open('data:application/vnd.ms-excel,'
									+ $('#dvData').html());
							e.preventDefault();
						});
			});
</script>
</head>
<body>
	<div id="cover">
		<header>
			<div id="page">
				<div id="divA">
					<img src="resources/bowling.jpg" />
				</div>
				<div id="divB">
					<h3>Bowling Game</h3>
				</div>
			</div>
		</header>

		<div id="content">
			<nav>
				<ul>
					<s:form>
						<li><s:submit action="ShowGames" value="Home" /></li>
						<s:submit action="createGame" value="Start Game" />
					</s:form>
				</ul>
			</nav>
			<br/>
			<section>
				<article>
					<s:form action="addUser">
						<s:iterator value="session" status="stat">
							<s:iterator>
								<s:property value="key" />
								<br/>
							</s:iterator>
						</s:iterator>
						<br />
						<s:textfield name="playerName"  theme="xhtml" />
						<s:submit  value="Add" />
					</s:form>
				</article>
			</section>

			<div id="clear"></div>
		</div>

		<footer>
			<p>&copy; Copyright 2013</p>
		</footer>
	</div>
</body>
</html>