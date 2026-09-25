#base image
FROM amazoncorretto:17

#create directory
WORKDIR /app

#copy jar into image
COPY MicroserviceGateway-1.0.0.jar .

#document port
EXPOSE 8888

#init application
ENTRYPOINT ["java"]
CMD ["-jar", "MicroserviceGateway-1.0.0.jar"]