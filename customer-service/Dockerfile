FROM java:8-jre
MAINTAINER Urfan Hamzayev <urfan.hamzayev@gmail.com>

ADD ./target/customer-service-0.0.1-SNAPSHOT.jar /app/customer-service-0.0.1-SNAPSHOT.jar

EXPOSE 8300

CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8300 -Dservice.instance.name=customer-service /app/customer-service-0.0.1-SNAPSHOT.jar