#base image
FROM amazoncorretto:17

#create directory
WORKDIR /app

#copy jar into image
COPY MicroserviceEureka-1.0.0.jar .

#document port
EXPOSE 9999

#init application
ENTRYPOINT ["java"]
CMD ["-jar", "MicroserviceEureka-1.0.0.jar"]