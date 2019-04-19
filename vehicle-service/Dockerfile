FROM java:8-jre
MAINTAINER Urfan Hamzayev <urfan.hamzayev@gmail.com>

ADD ./target/vehicle-service-0.0.1-SNAPSHOT.jar /app/vehicle-service-0.0.1-SNAPSHOT.jar

EXPOSE 8301

CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8301 -Dservice.instance.name=vehicle-service /app/vehicle-service-0.0.1-SNAPSHOT.jar