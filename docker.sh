mvn package
docker build -t openai/prompt .
docker run -p 8080:8080 openai/prompt