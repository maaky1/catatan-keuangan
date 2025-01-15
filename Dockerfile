# Gunakan image Java sebagai base image
FROM openjdk:8-jdk-alpine

# Set working directory di container
WORKDIR /app

# Salin file jar ke container
COPY target/catatan-keuangan-0.0.1-SNAPSHOT.jar catatan-keuangan.jar

# Tentukan port aplikasi
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "catatan-keuangan.jar"]
