# Usa un'immagine base di OpenJDK
FROM openjdk:25-jdk-slim

# Imposta la directory di lavoro
WORKDIR /app

# Copia il file JAR generato nel container
COPY target/Mazeing-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta su cui l'applicazione sarà in ascolto
EXPOSE 8080

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]