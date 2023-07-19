# Base 이미지 설정
FROM openjdk:17-jdk

# 애플리케이션 파일을 Docker 이미지로 복사
COPY build/libs/vue-backend-0.0.1-SNAPSHOT.jar /app.jar

# 컨테이너에서 실행될 명령어 설정
CMD ["java", "-jar", "app.jar"]