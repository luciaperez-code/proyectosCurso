#base image
FROM amazoncorretto:17

#create directory
WORKDIR /app

#copy jar into image
COPY MicroservicePet-1.0.0.jar .

#document port
EXPOSE 9091

#init application
ENTRYPOINT ["java"]
CMD ["-jar", "MicroservicePet-1.0.0.jar"]