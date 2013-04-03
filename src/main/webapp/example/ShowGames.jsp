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
<link rel="stylesheet" type="text/css" href="resources/style.css">
<link rel="stylesheet" type="text/css" href="resources/tablestyle.css">
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
				<ul><s:form>
					<li><s:submit action="ShowGames" value="Home"/></li>
					<li><s:submit action="startGame" value="New Game"/></li>
					<li><s:submit id="btnExport" value=" Export Results into Excel "/></li>
					</s:form>
				</ul>
			</nav>

			<section>
			<div id="dvData">
				<s:iterator var="parent" value="gamelist">
					<article>
						<table>
							<caption>
								Game No
								<s:property value="gameId" />
							</caption>
							<tr>
								<th>Player</th>
								<c:forEach var="i" begin="1" end="10">
									<th width="8%"><c:out value="${i}" /></th>
								</c:forEach>
								<th>Total</th>
							</tr>
							<s:iterator var="child" value="#parent.scores">
								<tr>
									<td><s:property value="#child.key.playerName" /></td>
									<s:iterator var="childValues" value="#child.value">
										<s:iterator var="frame" value="#childValues.frames">
											<td><s:property value="#frame" /></td>
										</s:iterator>
										<td><s:property value="#childValues.total" /></td>
									</s:iterator>
								</tr>
							</s:iterator>
						</table>
					</article>
				</s:iterator>
				</div>
			</section>
            
			<div id="clear"></div>
			
		</div>

		<footer>
			<p>&copy; Copyright 2013</p>
		</footer>
	</div>
<body>
</html>