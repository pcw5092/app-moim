<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>M.O.I.M</title>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
</head>
<body>
	<div class="root">
		<c:choose>
			<c:when test="${sessionScope.logon }">
				<div
					style="display: flex; justify-content: space-between; width: 100%;">
					<div
						style="padding: 10px 20px; display: flex; align-items: center; gap: 10px">
						<a href="/">홈</a> <a href="/friend/manage">친구관리</a> <a
							href="/moim/create">모임개설</a><a href="/user/login">로그아웃</a>
					</div>
					<div
						style="padding: 10px 20px; display: flex; align-items: center; gap: 10px">
						<a href="/private/mypage"> <img
							src="${sessionScope.logonUser.avatarURL }" style="width: 36px" />
						</a> <b>${logonUser.name }</b>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div style="display: flex; justify-content: flex-end; width: 100%;">
					<div style="padding: 10px 20px;">
						<a href="/user/login">로그인</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		
		<div style="flex: 1">
			<div>${today }</div>
			<img src="https://www.friendscube.com/img/mobile/3973263-1@2x.png" />
		</div>
		<%-- 최신 모임 정보 띄우는 영역 --%>
		<div class="block" style="width: 80%;">
			<b>곧 만남이 일어날 모임에 대한 정보들입니다. <a href="/moim/search">좀 더 많은 모임 정보가 필요한가요?</a></b>
			<div class="block-row" style="min-height: 120px;">
				<%-- 반복문 시작 --%>
			  <c:forEach var="moim" items="${latest }">
				<div class="moim-brief-card block" onclick="location.href='/moim/detail?id=${moim.id}'" >
					<div>
						<span style="color: bisque">[${moim.cate }]</span>
							<c:choose>
								<c:when test="${fn:length(moim.event) gt 16}">
									${fn:substring(moim.event, 0,16) }...
								</c:when>
								<c:otherwise>
									${moim.event }
								</c:otherwise>
							</c:choose>
							(${moim.currentPerson }/${moim.maxPerson } )
					</div>
					<div style="text-align: left">
						# 일정 : <span>
						<fmt:formatDate value="${moim.beginDate }" pattern="yyyy.MM.dd HH:mm"/>
						</span> ~ <span><fmt:formatDate value="${moim.endDate }" pattern="HH:mm"/></span>
					</div>
					<div style="text-align: left">
						# 회비 : <span>
							<fmt:formatNumber value="${moim.finalCost }" pattern="#,###"/>
						</span>
					</div>
				</div>
			  </c:forEach>
			  <%-- 반복문 끝 --%>
			</div>
		</div>
	</div>
</body>
</html>