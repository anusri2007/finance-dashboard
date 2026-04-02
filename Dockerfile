FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java","-jar","target/finance-dashboard-0.0.1-SNAPSHOT.jar"]