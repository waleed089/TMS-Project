
FROM maven:3.8.1-jdk-11 AS maven_build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
#RUN echo $(pwd)
RUN mvn package -e -Dmaven.test.skip=true

FROM openjdk:11-slim as build
LABEL maintainer="CodeChallenge"
COPY --from=maven_build /tmp/target/*.jar app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM openjdk:11-slim
VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.demo.tms.TmsApplication"]