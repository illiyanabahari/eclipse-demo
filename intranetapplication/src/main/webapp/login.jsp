<%
    String inputUser = request.getParameter("username");
    String inputPass = request.getParameter("password");

    String sessionUser = (String) session.getAttribute("username");
    String sessionPass = (String) session.getAttribute("password");

    if(inputUser != null && inputPass != null &&
       inputUser.equals(sessionUser) &&
       inputPass.equals(sessionPass)) {

        response.sendRedirect("welcome.jsp?user=" + inputUser); // GET
    } else {
%>
        <h3>Invalid Login</h3>
        <a href="login.html">Try Again</a>
<%
    }
%>
