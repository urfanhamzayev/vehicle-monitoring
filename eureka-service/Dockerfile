FROM java:8-jre
MAINTAINER Urfan Hamzayev <urfan.hamzayev@gmail.com>

ADD ./target/eureka-service-0.0.1-SNAPSHOT.jar /app/eureka-service-0.0.1-SNAPSHOT.jar

EXPOSE 8302

CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8302 -Dservice.instance.name=eureka-service /app/eureka-service-0.0.1-SNAPSHOT.jar