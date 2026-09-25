#base image
FROM amazoncorretto:17

#create directory
WORKDIR /app

#copy jar into image
COPY MicroserviceAdopter-1.0.0.jar .

#document port
EXPOSE 9092

#init application
ENTRYPOINT ["java"]
CMD ["-jar", "MicroserviceAdopter-1.0.0.jar"]