#base image
FROM amazoncorretto:17

#create directory
WORKDIR /app

#copy jar into image
COPY MicroserviceAdoptionRequest-1.0.0.jar .

#document port
EXPOSE 9093

#init application
ENTRYPOINT ["java"]
CMD ["-jar", "MicroserviceAdoptionRequest-1.0.0.jar"]