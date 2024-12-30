FROM maven:3.8.4-openjdk-8-slim AS build
WORKDIR /workdir
COPY . .
RUN mvn clean package -DskipTests

FROM tomcat:9-jdk8
COPY --from=build /workdir/target/Blog-Hub-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
RUN rm -rf /usr/local/tomcat/webapps/ROOT
EXPOSE 8080
CMD ["catalina.sh", "run"]