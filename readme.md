
<br />
<p align="center">
  <a href="https://hibernate.org">
    <img src="./readmeResources/hibernate-logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Hibernate Basic Practice</h3>


  <!-- TABLE OF CONTENTS -->
  <details open="open">
    <summary>Table of Contents</summary>
    <ol>
      <li>
        <a href="#about-the-project">About The Project</a>
      </li>
      <li><a href="#getting-started">Getting started</a></li>
      <li><a href="#important-annotations">Important annotations</a></li>
      <li><a href="#adding-image-to-database">Adding image to database</a></li>
      <li><a href="#fetching-data-from-database">Fetching data from database</a></li>
    </ol>
  </details>

  ## About the Project
  The project is about storing a object in mysql database using hibernate orm. This is the simple form of the project.

  ## Getting started

  1. First step is to start a maven project for simplicity of injecting dependencies. For archetype we have selected `maven-archetype-quickstart`.

  Then we inject dependencies for hibernate and MYSQL in pom.xml file by googling "hibernate maven" and "mysql maven" and get the xml code for the dependencies from [mvnrepository.com](mvnrepository.com).

  Then update the project to start maven magic (right-click on the project->maven->update project)

  2. Now we need to configure hibernate. We will do it by xml file (it can be done with java class files too).
  Now we create a `hibernate.cfg.xml` file in the /java folder.

  ![file-directory][hibernate-cfg-dir]

  Now google "hibernate cfg for mysql" and paste it in your file and now edit the username & password & database name etc parts (according to your mysql database information)

  3. Now we create a student object and save it in mysql with following snippets of code. But first we need to add the student class in `hibernate.cfg.xml` file by adding `<mapping class="com.tut.LifeSucks.Student" />`

  We have to add `@Entity` annotation on the student class and `@Id` annotation on primary key variable.

  Student class would look something like this:

  ```java
  @Entity //(name="change-name-entity") #this will change whole referenace name
  //@Table(name="another-name") #just the table name is changed here
public class Student {
	@Id //(name="change-name") #a different name if you like
	private int id;
	private String name;
	private String city;
.... .... ....
  ```

<details>
    <summary>Code snippets to save the object</summary>


  ```java
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
            //Building a SessionFactory derived object by the Configuration file
            //if the Configuration file name differs from default name (hibernate.cfg.xml)
            //or the directory is different then we needed to provide it as parameter in .configure()

      	System.out.println(factory.isClosed());

      	Student st = new Student();
      	st.setId(12);
      	st.setName("dsfr");
      	st.setCity("afsdka");

      	Session session = factory.openSession();

      	session.beginTransaction();
            //we need to begin transaction first
      	session.save(st);
            //saving it in database
            //but it may hold it in buffer for more change so we need to commit

      	session.getTransaction().commit();
          //flashing out the change

      	session.close();
  ```

  </details>

  4. Now we see the file is added on mysql database.

  ```bash
  mysql> use test;
Database changed
mysql> show tables;
+----------------+
| Tables_in_test |
+----------------+
| student        |
+----------------+
1 row in set (0.08 sec)
mysql> select * from student;
+----+--------+--------+
| id | city   | name   |
+----+--------+--------+
| 11 | DHaka  | Nashir |
| 12 | afsdka | dsfr   |
+----+--------+--------+
2 rows in set (0.28 sec)

  ```

## Important annotations

Now we learn about some important annotations.
* `@Entity` : Tells java to treat the class as a table
* `@Table` : Use it change the name of the table in database and other details
* `@Id` : Tells java to use the variable as primary key
* `@GeneratedValue` : Automated incensement for primary id
* `@Column` : To change the column name and details
* `@Transient` : To tell java not to use a variable as column
* `@Temporal` : To format date
* `@Lob` : To identity a large object files

some more example of annotations:  `@OneToOne`, `@OneToMany`, `@JoinColumn` etc

## Adding image to database

1. We start by adding new entity Address.
2. Then we add the annotations and save the object.

```bash
+-----------------+-------------+------+-----+---------+----------------+
| Field           | Type        | Null | Key | Default | Extra          |
+-----------------+-------------+------+-----+---------+----------------+
| student_address | int         | NO   | PRI | NULL    | auto_increment |
| date            | date        | YES  |     | NULL    |                |
| student_city    | varchar(50) | YES  |     | NULL    |                |
| image           | longblob    | YES  |     | NULL    |                |
| isOpen          | bit(1)      | NO   |     | NULL    |                |
+-----------------+-------------+------+-----+---------+----------------+

```

so we mysql is following the annotations and now we save a object (image is not added here)

```bash
+-----------------+------------+--------------+--------------+----------------+
| student_address | date       | student_city | image        | isOpen         |
+-----------------+------------+--------------+--------------+----------------+
|               1 | 2021-04-07 | Dhaka        | NULL         | 0x01           |
+-----------------+------------+--------------+--------------+----------------+
```

Now we add the image with following snippet of code

```java
FileInputStream fis = new FileInputStream("src/test/java/meme.jpg");
byte[] data = new byte[fis.available()];
fis.read(data);
address.setImage(data);
```

we can see the image is added by using sql-yog (as we cant see it in cmd).

![sql yog screenshot][sql-yog-image-save-screenshot]

## Fetching data from database

So now we will try to fetch data from database. two methods are used:
* `get()` :
            1. returns null if data does not exist(uses cache to search first)
            2. fetches immediately all data
* `load()` :
            1. throws excpetions if data does not exist
            2. does lazy loading for datas

We create a new class FetchDemo and use the following snippet to fetch data.

  ```java
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession(); // not idea , using singletone is ideal

		Student student = (Student)session.get(Student.class, 12);//Takes the classname and primary key parameter
			//Explict typecast because it returns an object class
		System.out.println(student);


		session.close();
		factory.close();
  ```

we could use `session.load(Student.class, 12);` but the sql-query would have only ran when we try to print it, until then a proxy would have been used.

## Embedding annotation

When we create a separate class for some more data but we want to add it's members to our table we use embedding annotation

So we create a new class Certificate and embed this class into Student Class. To be noted, we did not need to edit hibernate xml file to map this class as column as this 'Certificate' class was to be embedded not created separately (if we did ask to create separate column for Certificate we would get error since then hibernate could not have resolved how to enter Certificate object into student)

We could not run the EmDemo Class for sometime due to declaring main method private, silly typo but we should watch out for it.

## OneToOne Mapping

So we are gonna try one-to-one mapping. We can use embedding for this but for unidirectional , bidirectional relation details and granular control on fetching data we use one-to-one mapping.

So we create a Question class and Answer Class and add answer class to question class as a foreign key. This is uni-directional question and answer one-to-one mapping.

Later on we add Question class as foreign key to Answer Class making it a bi-directional one-to-one mapping.

Then we use mappedBy keyword in OneToOne annotation to keep the joined column in answer table hidden. But its still there and we can get question from any answer object.


## OneToMany Mapping

Here we add many answers to one question. We keep the relation unidirectional at first. We see if we do not add mappedBy keyword hibernate creates a separate table to handle the queries.

##ManyToOne Mapping

Now we made the relation between question and answer bi-directional. we can get one with the other and we used ManyToOne annotation for this. We can see a join column is added in answer table and a separate table is still there in database for handling multiple answers for one question.

## OneToMany relation without a separate table (Bi-directional)

Since now the relation is bidirectional we do not need a separate table to handle the one-to-many relation. We can handle it using the joined-column in answer and to do that we use mappedBy keyword.

So, to be noted that mappedBy can only be used in bidirectional relation (Which one should always maintain as good Practice)

## ManyToMany mapping

We create a many-to-many relationship between projects and employees. The relation is bidirectional (note that, unidirectional many-to-many makes no sense as that can be hadnled as one-to-many relationship)

If do not use mappedBy hibernate creates two separate table(redundancy).

Then we use  mappedBy to remove redundancy and use JoinTable annotation to change the table name and column names.

## Fetching Type

There are two types of fetch method.
1. Lazy
2. Eager

By Default fetching is Lazy. But we can change it by using `fetch = FetchType.EAGER` in the relation annotation (`@OnetoMany`, `@ManyToMany` etc). When lazy fetch is utilized the data associated with foreign key is not load until it is needed (a `get()` method call or `size()` call on the foreign key data is applied). In other words, join query is not initiated by default. But when we use eager fetch, join query is initiated by default and the data associated with foreign key is instantly loaded.

So lazy loading is more optimized and is set by default.

## Hibernate LifeCycle

When hibernate works, behind the scene the data goes through 4 lifecycles or states

1. `Transient`: When we create a object and set its properties.
        Neither associated with session object nor with database
2. `Persistent`: When we call `session.save(object)` in `Transient` state
        Associated with BOTH session object and with database
        Any change of object properties is reflected both on database and session object
3. `Detached`: When we call `session.close()` / `session.clear()`  in `Persistent` state[ we can send it back to `Persistent` state afterwards by calling `session.update()`]
        Not associated with session object but associated with database
        Any change of object properties is NOT reflected on database
4. `Removed`: When we call `session.remove(object)` in `Persistent` state
        Not associated with database but associated with session object
        Any change of object properties is NOT reflected on database




  [hibernate-cfg-dir]: ./readmeResources/hibernate-cfg-directory.png
  [sql-yog-image-save-screenshot]: ./readmeResources/sql-yog-image-save-screenshot.png
