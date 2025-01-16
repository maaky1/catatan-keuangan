# Gunakan image Java sebagai base image
FROM openjdk:8-jdk-slim

# Set working directory di container
WORKDIR /app

# Salin file jar ke container
COPY target/catatan-keuangan*.jar /app/catatan-keuangan.jar

# Salin values-dev.yml ke dalam container
COPY values-dev.yml /app/config/values-dev.yml

# Tentukan port aplikasi
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "catatan-keuangan.jar"]
