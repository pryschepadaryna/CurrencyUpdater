# Currency Updater Backend

This project contains backend API for the CRUD operations with currency rate with External API. Currency rates is
updated by Quartz Job every 5 minutes.

# How to run backend locally

Required tools:

    git

1. Move to console and prepare the environment. Clone the project:

       $ cd to_yor_local_directory
       $ git clone

2. Change in the application.yml DB and flyway connection properties. DB should use public and quartz schemas or
   change `db/migration/V1__init_tables.sql` and `db/migration/V2__quartz_added.sql` files to use custom one.

flyway:

       enabled: true;
       schemas: public
       url: jdbc:postgresql://localhost:5432/test
       password: 1
       driver-class-name: org.postgresql.Driver
       user: postgres

datasource:

        url: jdbc:postgresql://localhost:5432/test
        username: postgres
        password: 1
        driver-class-name: org.postgresql.Driver
        hikari:
         jdbc-url: jdbc:postgresql://localhost:5432/test

## Format code

- execute `mvn fmt:format` command in console
- Install Idea `google-java-format` plugin. The plugin will be disabled by default. To enable it in the current project,
  go to `File→Settings...→google-java-format Settings` (or `IntelliJ
  IDEA→Preferences...→Other Settings→google-java-format Settings` on macOS) and uncheck the Enable `google-java-format`
  checkbox. (A notification will be presented when you first open a project
  offering to do this for you.). When enabled, it will replace the normal Reformat Code action, which can be triggered
  from the Code menu or with the Ctrl-Alt-L (by default) keyboard shortcut.

