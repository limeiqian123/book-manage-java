# basic image
FROM openjdk:8-jdk-alpine
# copy app files to image
COPY target/myapp.jar /app/myapp.jar
# create a logs file and set the permissions
RUN mkdir /app/logs && chmod 755 /app/logs
# port
EXPOSE 8080
# work space
WORKDIR /app
# run the app
CMD ["java", "-jar", "/app/myapp.jar"]
