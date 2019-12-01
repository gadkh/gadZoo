<b>Zoo roject</b>
Gad Zoo Project, this project is actually a zoo server.
At the zoo you can do things like: input, update and delete animals, as well as actions on animals such as cleaning, feeding, sleeping and playing every action the animal performs gives it a score.

<b>Getting Started</b>
Import the project to your maven project and run it, I worked on eclipse IDE, and the server run on localhost port 8080.
If you want run the test you need first run the server and then run the tests.

<b>Libs</b>
In this project I used the Spring development environment I used the following directories:
<ul>
<li>h2 - as my database</li>
<li> jdbc - The database link</li>
<li>crudReposetory - Saving, deleting and updating data in the database</li>
<li>RestAPI technology - for creating the required APIs.</li>
<li> jUnit - for the tests</li>
</ul>
  
<b>Urls</b>
Installing
<li>http://localhost:8080/postAnimal - create new animal, need to pass in the body AnimalTO object (post method)</li>
<li>http://localhost:8080/zoo/updateAnimal/{type}/{name}- update animal details, need to pass in the body AnimalTO object and in the url need to pass type and name of animal (put method)</li>
<li>http://localhost:8080/zoo/deleteAnimal/{type}/{name}- delete animal by name need to pass type and name of animal (delete method)</li>
<li>http://localhost:8080/zoo/deleteAll delete all animals in the DB (delete method)</li>
<li>http://localhost:8080/zoo/getAnimal/{type}/{name}- get animal by type and name, need to pass type and name of animal (get method)</li>
<li>http://localhost:8080/zoo/getAllAnimals- get all animals with pagination can pass page and size in the url (get method)</li>
<li>http://localhost:8080/zoo/activities/{type}/{name}- create new activity on animal need to pass in the body ActivityTO object and in the url need to pass type and name of animal (post method)</li>

<b>Improves</b>
If I had more time I would probably sit on the client side and really try to create a full-stack app, plus I would try to build the project reactively.
Running the tests
Explain how to run the automated tests for this system

<b>Authors</b>
Gad Hamdadash
