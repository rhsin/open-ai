mvn package
docker build -t ryan3369/open-ai-promt .
docker push ryan3369/open-ai-promt:latest
# docker run -p 8080:8080 ryan3369/open-ai-promt:latest