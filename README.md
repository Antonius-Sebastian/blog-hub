# Blog-Hub

A web application for creating, managing, and interacting with blog posts.

## Description

Blog-Hub is a feature-rich web application that provides users with a comprehensive blogging platform. The application allows registered users to create, edit, and delete blog posts, while providing visitors the ability to view posts and comments.

## Getting Started

### Dependencies

* Maven (3.9.9)
* Java 8
* Netbeans 8.2
* Glassfish 4.1.1
* MySQL Database

### Database Setup

1. Locate the `blog_hub.sql` file in the repository
2. Import the SQL script to create the database schema
3. Update database connection settings in:
   * `src/main/resources/hibernate.cfg.xml`
   * `src/test/resources/hibernate.cfg.xml`

Modify the following configuration parameters:
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/your_database_name</property>
<property name="hibernate.connection.username">your_username</property>
<property name="hibernate.connection.password">your_password</property>
```

### Technologies Used

* JSF (Java Server Faces)
* Hibernate ORM
* Tailwind CSS
* TinyMCE
* PBKDF2

### Installing

* Clone the repository
* Open the project in Netbeans IDE
* Build and run the application
* Access the web application through your browser

## Key Features

* Blog post creation, editing, and deletion
* Rich text formatting with TinyMCE
* Blog post categorization with tags
* Secure user registration and login
* Responsive design with dark mode
* User authentication and access control

## License

No specific license applied.

## Acknowledgments

* [HyperUI](https://www.hyperui.dev/) - Tailwind CSS Template
