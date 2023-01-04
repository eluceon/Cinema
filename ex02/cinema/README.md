## 1. Program requirements:
- #### maven
- #### java 8
- #### postgres (see [properties](src/main/resources/hibernate.properties))

---

## 2. Create a database schema:
- see [schema.sql](src/main/resources//sql/schema.sql)

---

## 3. Run program:
```
mvn clean package cargo:run
```
---

## 4. Access:
Website can be accessed through [localhost:8090](http://localhost:8090)
### Mappings
- #### /admin/panel/halls
    The page for working with movie halls contains a list of all movie halls created by an
  administrator. The administrator can create a movie hall with a certain configuration.
- #### /admin/panel/films
    A movie page contains a list of all movies created by an administrator. An administrator
  can add a movie. For each movie, the title, year of release, age restrictions, and a
  description are specified. It is also possible to upload a poster for a movie.
- #### /admin/panel/sessions
    A page for working with movie shows. An administrator can create a session for a certain
movie in a certain movie hall at a required time. An administrator should be able to
indicate a ticket cost.
- #### /sessions
    Live search by name of the film.
- #### /sessions/{id}
    Full description of the movie, along with information about the movie hall assigned to this show.
- #### /films
    List of all films.
- #### /films/{id}
    Movie show page.
- #### /films/{id}/chat
    Multiuser chat room for each movie with a movie discussion in real time.
- #### /admin/signin
    Administrator login page.
- #### /admin/signup
  Administrator registration page.
