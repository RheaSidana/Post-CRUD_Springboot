# Post-CRUD_Springboot

<h1>Installation</h1>

<h3>1. Clone the Repository</h3>

```
git clone https://github.com/RheaSidana/Post-CRUD_Springboot.git
```

```
cd Post-CRUD_Springboot
```

<h3>2. Build the project</h3>

```
./gradlew clean build
```

<h3>3. PostgreSQL </h3>
<h4>Create the db</h4>

```
create database post;
```

<h4>Connect the db</h4>

```
\c post;
```

<h3>4. Migrate Tables</h3>

```
./gradlew flywayMigrate
```

<h4>Postgres: check the tables are migrated using: </h4>

```
\dt
```

<h3>5. Run the application: </h3>

```
./gradlew bootRun
```

<h3>6. API's </h3>
