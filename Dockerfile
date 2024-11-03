# Usando uma imagem base do OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo JAR da aplicação para o container
COPY ./target/chestplace-bff-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta em que a aplicação irá rodar (ajuste a porta se sua aplicação usar outra)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]