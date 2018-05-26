# FIIPractic
Proiect realizat in cadrul FII Practic, aria Java.

In cadrul proiectului am realizat o aplicatie web care ofera informatii despre filme si are in spate o baza de date.
Arhitectura aplicatiei este construita dupa modelul MVC. Comunicarea cu baza de date a fost realizata initial cu JDBC, apoi folosind JPA cu ORM-ul de la EclipseLink. Partea de front-end a fost construita cu ajutorul JSP-urilor, care sunt gestionate de servleturi HTTP.

De mentionat: unele componente ni s-au dat de catre traineri pentru a servi drept model de lucru: scheletul aplicatiei cu o parte din definitiile de clase, implementarea claselor MovieDao, Movie, MovieService(doar metoda getMovies si maparile de clase) StartServlet si paginile movies.jsp,login.jsp, movie-edit.jsp.
Eu am generat clasa GenreDao, implementat restul de metode din MovieServices, paginile movie-insert, movie-delete, details cu servletii aferenti. Gasiti in directorul prts Print Screen-uri cu interfata aplicatiei.

