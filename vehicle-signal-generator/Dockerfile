FROM java:8-jre
MAINTAINER Urfan Hamzayev <urfan.hamzayev@gmail.com>

ADD ./target/vehicle-signal-generator-0.0.1-SNAPSHOT.jar /app/vehicle-signal-generator-0.0.1-SNAPSHOT.jar

CMD java -jar -Xmx200m -Dspring.profiles.active=docker  -Dservice.instance.name=vehicle-signal-generator /app/vehicle-signal-generator-0.0.1-SNAPSHOT.jar