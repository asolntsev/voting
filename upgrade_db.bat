set CP="lib/dbdeploy-cli-3.0M3.jar;lib/postgresql-9.0-801.jdbc4.jar"

java -cp %CP% com.dbdeploy.CommandLineTarget --driver "org.postgresql.Driver" --url "jdbc:postgresql:voting" --userid "postgres" --password "passwd" --scriptdirectory app/data/changelog
pause